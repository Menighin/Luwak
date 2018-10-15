package com.menighin.luwak.core.models

import com.menighin.luwak.core.dtos.CrudResponse
import com.menighin.luwak.core.dtos.LuwakFilterMetadataDto
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto
import com.menighin.luwak.core.enums.ResponseStatusEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakModel
import com.menighin.luwak.exceptions.CrudException
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.ArrayList

@Component
abstract class AbstractLuwakPage<M : ILuwakModel, F : ILuwakFilter> {

	@Autowired
	protected var messageSource: MessageSource? = null

	var modelClass: Class<out ILuwakModel>? = null
		private set
	var filterClass: Class<out ILuwakFilter>? = null

	abstract val table: AbstractLuwakDataTable<M, ILuwakDto, F>

	/**
	 * Gets the page metadata in order to built it.
	 *
	 * @return The metadata object
	 */
	open val pageMetadata: LuwakPageMetadataDto
		get() {
			val pageMetadata = LuwakPageMetadataDto()

			if (table != null)
				pageMetadata.masterTable = table!!.getMetadata(messageSource!!)

			pageMetadata.filters = LuwakFilterMetadataDto.getFiltersFrom(filterClass!!)

			return pageMetadata
		}

	val excelFile: XSSFWorkbook
		get() {
			return XSSFWorkbook()
//			val models = datasource.getAll(null, null)
//			return this.table!!.toExcel(models)
		}

	// Instance initialization
	init {
		val superclass = javaClass.genericSuperclass
		val parameterized = superclass as ParameterizedType
		modelClass = parameterized.actualTypeArguments[0] as Class<out ILuwakModel>
		filterClass = parameterized.actualTypeArguments[1] as Class<out ILuwakFilter>
	}

	/**
	 * Responsible for generating the list of options for a given field.
	 * Must be overridden by pages having auto-complete fields
	 * @param field Name of the field
	 * @param input The value the user has input in order to filter the options
	 * @return the list of options for a given field
	 */
	@Throws(CrudException::class)
	fun getFilterValues(field: String, input: String): List<Pair<Int, String>> {
		return ArrayList()
	}

	@Throws(CrudException::class)
	fun getAll(masterId: Int?, page: Int, filter: F?): List<ILuwakDto> {
		return table.getAll(masterId, page, filter)
	}

	@Throws(CrudException::class)
	fun count(): Int? {
		return table.count()
	}

	@Throws(CrudException::class, Exception::class)
	fun create(dtoMap: Map<String, Any>): Boolean {
		val dto = convertMapToDto(dtoMap)
		return table.create(dto)
	}

	@Throws(CrudException::class, Exception::class)
	fun update(id: Int, dtoMap: Map<String, Any>): Boolean {
		val dto = convertMapToDto(dtoMap)
		return table.update(id, dto)
	}

	@Throws(CrudException::class, Exception::class)
	fun delete(id: Int): Boolean {
		return table.delete(id)
	}

	@Throws(IllegalAccessException::class, InstantiationException::class)
	private fun convertMapToDto(model: Map<String, Any>): ILuwakDto {
		// Generating DTO model
		val classDto = table!!.classDto
		val dto = classDto!!.newInstance() as ILuwakDto
		val fields = classDto.declaredFields

		for (f in fields) {

			if (model.containsKey(f.name)) {
				f.isAccessible = true
				f.set(dto, model[f.name])
				f.isAccessible = false
			}
		}

		return dto
	}

}
