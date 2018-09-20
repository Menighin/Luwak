package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakMasterDetailDatasource
import com.menighin.luwak.core.interfaces.ILuwakModel
import testapp.models.City
import testapp.viewModels.CityViewModel
import java.lang.reflect.ParameterizedType

abstract class AbstractLuwakMasterDetailPage<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : AbstractLuwakPage<M, F>() {

	var detailClass: Class<out ILuwakModel>? = null
	var detailTable: AbstractLuwakDataTable<*,*,*>? = null
	var datasource: ILuwakMasterDetailDatasource<M, D, F>? = null

	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		detailClass = parameterized.actualTypeArguments[1] as Class<out ILuwakModel>
		filterClass = parameterized.actualTypeArguments[2] as Class<out ILuwakFilter>
	}

	override fun getPageMetadata(): LuwakPageMetadataDto {
		val metadataDto = super.getPageMetadata()

		metadataDto.slaveTable = detailTable?.metadata

		return metadataDto
	}

}