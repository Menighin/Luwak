package testapp.repositories

import com.menighin.luwak.core.dtos.CrudResponse
import com.menighin.luwak.core.enums.ResponseStatusEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import org.springframework.stereotype.Repository
import testapp.filters.CityPageFilter
import testapp.models.City
import testapp.models.Country
import testapp.viewModels.CityViewModel
import java.util.ArrayList

@Repository
open class CityDatasource: ILuwakDatasource<City, CityPageFilter> {

	private var countries: MutableList<Country> = ArrayList()
	private var cities: MutableList<City> = ArrayList()

	constructor() {
		countries = mutableListOf(Country(0, "Brazil"), Country(1, "United States of America"), Country(2, "Portugal"))
		cities = mutableListOf(
				City(0, "Belo Horizonte", countries[0]),
				City(1, "Sao Paulo", countries[0]),
				City(2, "Denver", countries[1]),
				City(3, "San Francisco", countries[1]),
				City(4, "Porto", countries[2]),
				City(5, "Lisboa", countries[2])
		)
	}

	override fun getAll(page: Int, filter: CityPageFilter?): CrudResponse<ArrayList<City>> {
		val filteredCities =
			cities.filter {
				city ->
					(filter?.id == null || filter.id == city.id) &&
					(filter?.name == null  || filter.name == city.name)
			}

		return CrudResponse(ResponseStatusEnum.SUCCESS, filteredCities as ArrayList<City>, null, null)
	}

	override fun create(luwakDto: ILuwakDto<City>): CrudResponse<Void> {
		val dto = luwakDto as CityViewModel
		val countryId = dto.countryName.toInt()
		val country = countries.find { it.id == countryId }
		var lastId = cities.sortedBy { it.id }.last().id
		val newCity = City(++lastId, dto.cityName, country)

		cities.add(newCity)

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun update(id: Int, luwakDto: ILuwakDto<City>?): CrudResponse<Void> {
		val dto = luwakDto as CityViewModel

		val model = cities.find { it.id == id } ?: return CrudResponse(ResponseStatusEnum.ERROR)
		model.name = dto.cityName

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun delete(id: Int, luwakDto: ILuwakDto<City>?): CrudResponse<Void> {
		cities = cities.filter {it.id != id}.toMutableList()

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun count(): CrudResponse<Int> {
		return CrudResponse(ResponseStatusEnum.SUCCESS, cities.count())
	}
}
