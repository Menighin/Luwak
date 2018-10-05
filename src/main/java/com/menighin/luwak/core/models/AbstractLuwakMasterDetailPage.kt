package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.CrudResponse
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.enums.ResponseStatusEnum
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakMasterDetailDatasource
import com.menighin.luwak.core.interfaces.ILuwakModel
import com.menighin.luwak.exceptions.CrudException
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

abstract class AbstractLuwakMasterDetailPage<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : AbstractLuwakPage<M, F>() {

	var detailClass: Class<out ILuwakModel>? = null

	abstract override fun getDatasource() : ILuwakMasterDetailDatasource<M, D, F>
	abstract fun getDetailTable() : AbstractLuwakDataTable<D, *>

	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		detailClass = parameterized.actualTypeArguments[1] as Class<out ILuwakModel>
		filterClass = parameterized.actualTypeArguments[2] as Class<out ILuwakFilter>
	}

	abstract fun getMasterFields() : ArrayList<String>

	override fun getPageMetadata(): LuwakPageMetadataDto {
		val metadataDto = super.getPageMetadata()

		metadataDto.slaveTable = getDetailTable().getMetadata(messageSource)

		return metadataDto
	}

	@Throws(CrudException::class)
	fun getDetailAll(masterId: Int, page: Int, filter: F?): ArrayList<out ILuwakDto> {
		val models = datasource.getAllDetail(masterId, page, filter)

		return getDetailTable().getTableData(models)
	}

	@Throws(CrudException::class)
	fun countDetail(masterId: Int): Int {
		return datasource.countDetail(masterId)
	}

	fun getExcelDetailFile(masterId: Int): XSSFWorkbook {
		val models = datasource.getAllDetail(masterId, null, null)
		val masterModel = datasource.getById(masterId)

		return getDetailTable().toExcelDetail(models, masterModel!!, this)
	}

}