package testapp.repositories

import com.menighin.luwak.core.dtos.CrudResponse
import com.menighin.luwak.core.enums.ResponseStatusEnum
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakMasterDetailDatasource
import org.springframework.stereotype.Repository
import testapp.filters.CityPageFilter
import testapp.filters.CountryCityPageFilter
import testapp.models.City
import testapp.models.Country
import testapp.viewModels.CityViewModel
import testapp.viewModels.CountryViewModel

@Repository
open class CountryCityDatasource : ILuwakMasterDetailDatasource<Country, City, CountryCityPageFilter> {

	private var countries: MutableList<Country> = java.util.ArrayList()
	private var cities: MutableList<City> = java.util.ArrayList()

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

	override fun getAll(page: Int, filter: CountryCityPageFilter?): CrudResponse<ArrayList<Country>> {
		val filteredCountries =
				countries.filter {
					country ->
					(filter?.id == null || filter.id == country.id) &&
							(filter?.city == null  || filter.city == country.code)
				}

		return CrudResponse(ResponseStatusEnum.SUCCESS, filteredCountries as ArrayList<Country>, null, null)
	}

	override fun getAllDetail(masterId: Int, page: Int, filter: CountryCityPageFilter?): CrudResponse<ArrayList<City>> {
		val filteredCities =
				cities.filter {
					city ->
					(city.country.id == masterId) &&
					(filter?.id == null || filter.id == city.id) &&
					(filter?.country == null  || filter.country == city.name)
				}

		return CrudResponse<ArrayList<City>>(ResponseStatusEnum.SUCCESS, filteredCities as java.util.ArrayList<City>)
	}

	override fun count(): CrudResponse<Int> {
		return CrudResponse(ResponseStatusEnum.SUCCESS, countries.count())
	}

	override fun create(luwakDto: ILuwakDto<Country>?): CrudResponse<Void> {
		val dto = luwakDto as CountryViewModel
		var lastId = countries.sortedBy { it.id }.last().id
		val newCountry = Country(++lastId, dto.name)

		countries.add(newCountry)

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun update(id: Int, luwakDto: ILuwakDto<Country>?): CrudResponse<Void> {
		val dto = luwakDto as CountryViewModel

		val model = countries.find { it.id == id } ?: return CrudResponse(ResponseStatusEnum.ERROR)
		model.code = dto.name

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}


	override fun delete(id: Int, luwakDto: ILuwakDto<Country>?): CrudResponse<Void> {
		countries = countries.filter {it.id != id}.toMutableList()
		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun updateDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<Country>): CrudResponse<Void> {
		val dto = luwakDto as CityViewModel

		val model = cities.find { it.id == id && it.country.id == masterId } ?: return CrudResponse(ResponseStatusEnum.ERROR)
		model.name = dto.cityName

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun createDetail(masterId: Int, luwakDto: ILuwakDto<Country>): CrudResponse<Void> {
		val dto = luwakDto as CityViewModel
		var lastId = countries.sortedBy { it.id }.last().id
		val country = countries.find { it.id == masterId }
		val newCity = City(++lastId, dto.cityName, country)

		cities.add(newCity)

		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun deleteDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<Country>): CrudResponse<Void> {
		cities = cities.filter {it.id != id}.toMutableList()
		return CrudResponse(ResponseStatusEnum.SUCCESS)
	}

	override fun countDetail(masterId: Int): CrudResponse<Int> {
		return CrudResponse(ResponseStatusEnum.SUCCESS, cities.find{it.country.id == masterId}.count())
	}

}
