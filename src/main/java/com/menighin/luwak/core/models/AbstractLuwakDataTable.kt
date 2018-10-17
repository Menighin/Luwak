package com.menighin.luwak.core.models

import com.menighin.luwak.core.annotations.*
import com.menighin.luwak.core.dtos.LuwakDataTableMetadataDto
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakModel
import org.apache.poi.ss.formula.functions.Index
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.context.MessageSource
import java.lang.reflect.Field

import java.lang.reflect.ParameterizedType

abstract class AbstractLuwakDataTable<M, D, F> where M: ILuwakModel, D: ILuwakDto, F: ILuwakFilter {

	var classModel: Class<M>
		private set
	var classDto: Class<D>
		private set

	abstract val datasource: ILuwakDatasource<M, F>

	fun getMetadata(messageSource: MessageSource): LuwakDataTableMetadataDto {
		return LuwakDataTableMetadataDto(this, messageSource)
	}

	// Instance initialization
	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		classModel = parameterized.actualTypeArguments[0] as Class<M>
		classDto = parameterized.actualTypeArguments[1] as Class<D>
	}

	fun getAll(masterId: Int?, page: Int?, filter: F?): List<D> {
		val models = datasource.getAll(masterId, page, filter)
		val dtos = this.toTableData(models)
		return dtos
	}

	open fun toTableData(models: List<ILuwakModel>): List<D> {
		val dtos = ArrayList<D>()

		// Get fields that are not ExcelOnly
		val dtoFields = classDto.declaredFields.filter { isLuwakField(it) }

		for (m in models) {
			try {
				val dto = classDto.newInstance()

				// Mapping fields to Dto
				for (f in dtoFields) {
					if (!f.isAnnotationPresent(MapModel::class.java)) continue

					val mapModel = f.getAnnotation(MapModel::class.java).value.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

					// Access all objects until last level
					var leaf: Any = m
					for (map in mapModel) {
						val fieldName = map.substring(0, 1).toUpperCase() + map.substring(1)
						leaf = leaf.javaClass.getMethod("get$fieldName").invoke(leaf)
					}

					f.isAccessible = true
					f.set(dto, leaf)
					f.isAccessible = false
				}

				dtos.add(dto)
			} catch (e: Exception) {
				e.printStackTrace()
			}

		}
		return dtos
	}

	fun count(masterId: Int?): Int {
		return datasource.count(masterId)
	}

	fun create(masterId: Int?, dto: D): Boolean {
		return datasource.create(masterId, dto)
	}

	fun update(masterId: Int?, id: Int, dto: D): Boolean {
		return datasource.update(masterId, id, dto)
	}

	fun delete(masterId: Int?, id: Int): Boolean {
		return datasource.delete(masterId, id)
	}

	fun deleteMany(masterId: Int?, ids: IntArray): Boolean {
		return datasource.deleteMany(masterId, ids)
	}

	open fun getModalClass() : Class<*>? {
		return classDto
	}

	/**
	 * Converts this Luwak table to an excel file respecting the DTO model
	 */
	fun toExcel(models: List<M>) : XSSFWorkbook {

		// Check if annotation is correct
		if (!this::class.java.isAnnotationPresent(LuwakTable::class.java))
			throw Exception("Table not annotated with @LuwakTable")

		val dtos = this.toTableData(models)

		val tableTitle = this::class.java.getAnnotation(LuwakTable::class.java).title

		val excelFields = classDto.declaredFields.filter {
			isLuwakField(it) &&
			(!it.isAnnotationPresent(ColumnType::class.java) ||
					it.isAnnotationPresent(ColumnType::class.java) && it.getAnnotation(ColumnType::class.java).value != ColumnTypeEnum.HIDDEN)
		}
		val headerLabels = excelFields.map { f -> f.getAnnotation(Label::class.java).value }

		return createWorkbook(tableTitle, headerLabels, excelFields, dtos)
	}


	private fun createWorkbook(tableTitle: String, headerLabels: List<String>, excelFields: List<Field>, dtos: List<ILuwakDto>) : XSSFWorkbook {

		// Setting up workbook
		val workbook = XSSFWorkbook()
		val createHelper = workbook.creationHelper

		// Creating sheet
		val sheet = workbook.createSheet(tableTitle)

		// Styling header
		val headerFont = workbook.createFont()
		headerFont.bold = true
		headerFont.color = IndexedColors.WHITE.index

		val headerCellStyle = workbook.createCellStyle()
		headerCellStyle.setFont(headerFont)
		headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND)
		headerCellStyle.setFillForegroundColor(XSSFColor(byteArrayOf(111, 44, 163.toByte()), null))

		// Creating and filling header row
		val headerRow = sheet.createRow(0)

		for (i in 0 until headerLabels.size) {
			val cell = headerRow.createCell(i)
			cell.setCellValue(headerLabels[i])
			cell.cellStyle = headerCellStyle
		}

		// Create another helper cellstyles
		val dateCellStyle = workbook.createCellStyle()
		dateCellStyle.dataFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy")

		val dateTimeCellStyle = workbook.createCellStyle()
		dateTimeCellStyle.dataFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss")

		// Filling the worksheet with data
		var rowNum = 1
		for (dto in dtos) {
			val row = sheet.createRow(rowNum++)

			// Filling fields
			for ((i, field) in excelFields.withIndex()) {
				var columnType = ColumnTypeEnum.TEXT
				if (field.isAnnotationPresent(ColumnType::class.java))
					columnType = field.getAnnotation(ColumnType::class.java).value

				field.isAccessible = true
				val value = field.get(dto)
				field.isAccessible = false

				val cell = row.createCell(i)
				cell.setCellValue(value.toString())

				if (columnType == ColumnTypeEnum.DATE)
					cell.cellStyle = dateCellStyle
				else if (columnType == ColumnTypeEnum.DATETIME)
					cell.cellStyle = dateTimeCellStyle
			}
		}

		// Resize fields to fit values
		for (i in 0 until headerLabels.size)
			sheet.autoSizeColumn(i)

		return workbook

	}

	private fun isLuwakField(f: Field) : Boolean {
		return f.isAnnotationPresent(Label::class.java)
	}

}
