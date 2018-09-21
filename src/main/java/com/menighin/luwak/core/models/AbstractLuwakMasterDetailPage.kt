package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakMasterDetailDatasource
import com.menighin.luwak.core.interfaces.ILuwakModel
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

abstract class AbstractLuwakMasterDetailPage<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : AbstractLuwakPage<M, F>() {

	var detailClass: Class<out ILuwakModel>? = null
	var detailTable: AbstractLuwakDataTable<D,*,*>? = null
	var datasource: ILuwakMasterDetailDatasource<M, D, F>?
		set(value) {
			super.setDatasource(value)
		}
		get() {
			return super.getDatasource() as ILuwakMasterDetailDatasource<M, D, F>?
		}

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

	fun getDetailAll(masterId: Int, page: Int, filter: F?): ArrayList<out ILuwakDto<D>> {
		val models = datasource?.getAllDetail(masterId, page, filter)
		return detailTable?.getTableData(models) as ArrayList<out ILuwakDto<D>>
	}

}