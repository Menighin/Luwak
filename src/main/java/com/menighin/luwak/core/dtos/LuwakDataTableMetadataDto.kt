package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.annotations.ColumnType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.models.AbstractLuwakPage
import java.util.ArrayList

class LuwakDataTableMetadataDto {

	var id: String = ""
	var title: String = ""
	var canCreate: Boolean = true
	var canEdit: Boolean = true
	var canDelete: Boolean = true
	var columns: ArrayList<ColumnMetadataDto> = ArrayList()

	constructor(clazz: Class<*>) {
		// If class is not annotated with @LuwakTable, return
		if (!clazz.isAnnotationPresent(LuwakTable::class.java)) return

		val luwakTableAnnotation = clazz.annotations.find { it is LuwakTable} as LuwakTable

		id = clazz.simpleName
		title = luwakTableAnnotation.title
		canCreate = luwakTableAnnotation.canCreate
		canDelete = luwakTableAnnotation.canDelete
		canEdit = luwakTableAnnotation.canEdit

		val fields = clazz.declaredFields

		// Iterate through fields generating the metadata
		for (f in fields) {
			if (!f.isAnnotationPresent(Label::class.java)) continue

			val label = f.getAnnotation(Label::class.java)
			val columnType = f.getAnnotation(ColumnType::class.java)
			val columnTypeEnum = columnType?.value ?: ColumnTypeEnum.Text

			columns.add(ColumnMetadataDto(f.name, label.value, columnTypeEnum))
		}
	}

}