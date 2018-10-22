package com.menighin.luwak.core.dtos

import com.menighin.luwak.AbstractLuwakApplication
import com.menighin.luwak.core.annotations.FieldType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.enums.FieldTypeEnum
import org.springframework.context.MessageSource
import java.util.*

class LuwakFilterMetadataDto(var id: String, var label: String, var type: FieldTypeEnum) {

	companion object {
		fun getFiltersFrom(luwakApplication: AbstractLuwakApplication, filterClass: Class<*>, messageSource: MessageSource) : ArrayList<LuwakFilterMetadataDto> {
			val result = ArrayList<LuwakFilterMetadataDto>()

			val fields = filterClass.declaredFields

			for (f in fields) {
				if (!f.isAnnotationPresent(Label::class.java)) continue

				val label = messageSource.getMessage(f.getAnnotation(Label::class.java).value, null, luwakApplication.getLocale())
				val type = if(f.isAnnotationPresent(FieldType::class.java)) f.getAnnotation(FieldType::class.java).value else FieldTypeEnum.TEXT

				result.add(LuwakFilterMetadataDto(f.name, label, type))

			}
			return result
		}
	}

}