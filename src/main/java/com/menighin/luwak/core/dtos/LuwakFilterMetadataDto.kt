package com.menighin.luwak.core.dtos

import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.enums.FilterTypeEnum

class LuwakFilterMetadataDto(var id: String, var label: String, var type: FilterTypeEnum) {

	companion object {
		fun getFiltersFrom(filterClass: Class<*>) : ArrayList<LuwakFilterMetadataDto> {
			val result = ArrayList<LuwakFilterMetadataDto>()

			val fields = filterClass.declaredFields

			for (f in fields) {
				if (!f.isAnnotationPresent(Label::class.java)) continue

				val label = f.getAnnotation(Label::class.java).value

				result.add(LuwakFilterMetadataDto(f.name, label, FilterTypeEnum.TEXT))

			}
			return result
		}
	}

}