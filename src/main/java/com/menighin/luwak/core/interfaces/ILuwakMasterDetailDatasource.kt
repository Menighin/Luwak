package com.menighin.luwak.core.interfaces

import com.menighin.luwak.core.dtos.CrudResponse

interface ILuwakMasterDetailDatasource<M : ILuwakModel, D : ILuwakModel, F : ILuwakFilter> : ILuwakDatasource<M, F> {

	fun getAllDetail(masterId: Int, page: Int?, filter: F?) : ArrayList<D>

	fun createDetail(masterId: Int, luwakDto: ILuwakDto): Boolean

	fun updateDetail(id: Int, masterId: Int, luwakDto: ILuwakDto): Boolean

	fun deleteDetail(id: Int, masterId: Int, luwakDto: ILuwakDto): Boolean

	fun countDetail(masterId: Int): Int

}