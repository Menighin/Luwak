package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.annotations.ColumnType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import java.util.ArrayList

class LuwakDataTableMetadataDto {

	var id: String = ""
	var title: String = ""
	var canCreate: Boolean = true
	var canEdit: Boolean = true
	var canDelete: Boolean = true
	var columns: ArrayList<ColumnMetadataDto> = ArrayList()

	constructor(table: AbstractLuwakDataTable<*, *, *>) {

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

		val fields = dtoClass.declaredFields

		// Iterate through fields generating the metadata
		for (f in fields) {
			if (!f.isAnnotationPresent(Label::class.java)) continue

			val label = f.getAnnotation(Label::class.java)
			val columnType = f.getAnnotation(ColumnType::class.java)
			val columnTypeEnum = columnType?.value ?: ColumnTypeEnum.TEXT

			columns.add(ColumnMetadataDto(f.name, label.value, columnTypeEnum))
		}
	}

}