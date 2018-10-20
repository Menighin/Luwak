package com.menighin.luwak.core.interfaces

interface ILuwakDatasource<M : ILuwakModel, F : ILuwakFilter> {

	fun getById(id: Long): M

	fun getAll(masterId: Long?, page: Int?, filter: F?, sort: HashMap<String, String>?): List<M>

	fun create(masterId: Long?, luwakDto: ILuwakDto): Boolean

	fun update(masterId: Long?, id: Long, luwakDto: ILuwakDto): Boolean

	fun delete(masterId: Long?, id: Long): Boolean

	fun deleteMany(masterId: Long?, ids: LongArray): Boolean

	fun count(masterId: Long?, filter: F?): Int
}
