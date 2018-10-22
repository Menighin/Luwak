package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.LuwakDataTableMetadataDto
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.interfaces.*
import com.menighin.luwak.exceptions.CrudException
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

abstract class AbstractLuwakMasterDetailPage<F : ILuwakFilter> : AbstractLuwakPage<F>() {

	lateinit var detailClass: Class<out ILuwakModel>

	abstract val detailTables: List<AbstractLuwakDataTable<ILuwakModel, ILuwakDto, F>>

	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		filterClass = parameterized.actualTypeArguments[0] as Class<out ILuwakFilter>
	}

	override val pageMetadata: LuwakPageMetadataDto
		get() {
			val metadataDto = super.pageMetadata

			if (detailTables.any()) {
				val detailMetadataDto = ArrayList<LuwakDataTableMetadataDto>()
				for (table in detailTables) {
					detailMetadataDto.add(table.getMetadata(luwakApplication, messageSource))
				}
				metadataDto.detailTables = detailMetadataDto
			}

			return metadataDto
		}

	@Throws(CrudException::class)
	fun getDetailAll(tableId: String, masterId: Long, page: Int, filter: F?, sort: HashMap<String, String>?): List<ILuwakDto> {
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		return table.getAll(masterId, page, filter, sort)
	}

	@Throws(CrudException::class)
	fun createDetail(tableId: String, masterId: Long, dtoMap: Map<String, Any>): Boolean {
		val dto = convertMapToDto(dtoMap)
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		return table.create(masterId, dto)
	}

	@Throws(CrudException::class)
	fun updateDetail(tableId: String, masterId: Long, id: Long, dtoMap: Map<String, Any>): Boolean {
		val dto = convertMapToDto(dtoMap)
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		return table.update(masterId, id, dto)
	}

	@Throws(CrudException::class)
	fun deleteDetail(tableId: String, masterId: Long, id: Long): Boolean {
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		return table.delete(masterId, id)
	}

	@Throws(CrudException::class)
	fun countDetail(tableId: String, masterId: Long, filter: F?): Int {
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		return table.count(masterId, filter)
	}

	fun getExcelDetailFile(tableId: String): XSSFWorkbook {
		val table = detailTables.find { it::class.java.simpleName == tableId } ?: throw ClassNotFoundException("Table $tableId does not exist")
		val models = table.datasource.getAll(null, null, null, null)
		return table.toExcel(models)
	}

}