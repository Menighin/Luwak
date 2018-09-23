package com.menighin.luwak.core.interfaces

import com.menighin.luwak.core.dtos.CrudResponse

interface ILuwakMasterDetailDatasource<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : ILuwakDatasource<M, F> {

	fun getAllDetail(masterId: Int, page: Int, filter: F?) : CrudResponse<ArrayList<D>>

	fun createDetail(masterId: Int, luwakDto: ILuwakDto<M>): CrudResponse<Void>

	fun updateDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<M>): CrudResponse<Void>

	fun deleteDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<M>): CrudResponse<Void>

	fun countDetail(masterId: Int): CrudResponse<Int>


}