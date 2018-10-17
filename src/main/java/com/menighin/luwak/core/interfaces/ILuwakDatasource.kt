package com.menighin.luwak.core.interfaces

import com.menighin.luwak.core.dtos.CrudResponse
import java.util.ArrayList

interface ILuwakDatasource<M : ILuwakModel, F : ILuwakFilter> {

	fun getById(id: Long): M

	fun getAll(masterId: Long?, page: Int?, filter: F?): List<M>

	fun create(masterId: Long?, luwakDto: ILuwakDto): Boolean

	fun update(masterId: Long?, id: Long, luwakDto: ILuwakDto): Boolean

	fun delete(masterId: Long?, id: Long): Boolean

	fun deleteMany(masterId: Long?, ids: LongArray): Boolean

	fun count(masterId: Long?): Int
}
