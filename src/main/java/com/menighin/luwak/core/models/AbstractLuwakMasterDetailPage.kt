package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.interfaces.*
import com.menighin.luwak.exceptions.CrudException
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.lang.reflect.ParameterizedType
import java.util.ArrayList

abstract class AbstractLuwakMasterDetailPage<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : AbstractLuwakPage<M, F>() {

	var detailClass: Class<out ILuwakModel>? = null

	abstract val datasource: ILuwakMasterDetailDatasource<M, D, F>

	abstract val detailTable: AbstractLuwakDataTable<D, *, F>

	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		detailClass = parameterized.actualTypeArguments[1] as Class<out ILuwakModel>
		filterClass = parameterized.actualTypeArguments[2] as Class<out ILuwakFilter>
	}

	abstract fun getMasterFields() : ArrayList<String>

	override val pageMetadata: LuwakPageMetadataDto
		get() {
			val metadataDto = super.pageMetadata

			metadataDto.slaveTable = detailTable.getMetadata(messageSource!!)

			return metadataDto
		}

	@Throws(CrudException::class)
	fun getDetailAll(masterId: Int, page: Int, filter: F): List<ILuwakDto> {
		return detailTable.getAll(page, filter)
	}

	@Throws(CrudException::class)
	fun countDetail(masterId: Int): Int {
		return datasource.countDetail(masterId)
	}

	fun getExcelDetailFile(masterId: Int): XSSFWorkbook {
		val models = datasource.getAllDetail(masterId, null, null)
		val masterModel = datasource.getById(masterId)

		return detailTable.toExcelDetail(models, masterModel!!, this)
	}

}