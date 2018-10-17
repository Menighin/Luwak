package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.annotations.ColumnType
import com.menighin.luwak.core.annotations.ExcelOnly
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.context.MessageSource
import java.util.*

class LuwakDataTableMetadataDto {

	var id: String = ""
	var title: String = ""
	var canCreate: Boolean = true
	var canEdit: Boolean = true
	var canDelete: Boolean = true
	var columns: ArrayList<FieldMetadataDto> = ArrayList()
	var modalFields: ArrayList<FieldMetadataDto> = ArrayList()

	constructor(table: AbstractLuwakDataTable<*, *, *>, messageSource: MessageSource) {

		val dtoClass = table.classDto
		val tableClass = table.javaClass

		// If class is not annotated with @LuwakTable, return
		if (!tableClass.isAnnotationPresent(LuwakTable::class.java)) return

		val luwakTableAnnotation = tableClass.annotations.find { it is LuwakTable} as LuwakTable

		id = tableClass.simpleName
		title = luwakTableAnnotation.title
		canCreate = luwakTableAnnotation.canCreate
		canDelete = luwakTableAnnotation.canDelete
		canEdit = luwakTableAnnotation.canEdit

		val columnFields = dtoClass.declaredFields.filter{ !it.isAnnotationPresent(ExcelOnly::class.java) }

		// Iterate through fields generating the metadata
		for (f in columnFields) {
			if (!f.isAnnotationPresent(Label::class.java)) continue

			val label = messageSource.getMessage(f.getAnnotation(Label::class.java).value, null, Locale("pt"))
			val columnType = f.getAnnotation(ColumnType::class.java)
			val columnTypeEnum = columnType?.value ?: ColumnTypeEnum.TEXT

			this.columns.add(FieldMetadataDto(f.name, label, columnTypeEnum))
		}

		val modalFields = table.getModalClass()!!.declaredFields.filter{ !it.isAnnotationPresent(ExcelOnly::class.java) }
		for (f in modalFields) {
			if (!f.isAnnotationPresent(Label::class.java)) continue

			val label = messageSource.getMessage(f.getAnnotation(Label::class.java).value, null, Locale("pt"))
			val columnType = f.getAnnotation(ColumnType::class.java)
			val columnTypeEnum = columnType?.value ?: ColumnTypeEnum.TEXT

			this.modalFields.add(FieldMetadataDto(f.name, label, columnTypeEnum))
		}
	}

}