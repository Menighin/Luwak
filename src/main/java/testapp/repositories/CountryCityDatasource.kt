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

	override fun getById(id: Int): Country? {
		return countries.find { it.id == id }
	}

	override fun getAll(page: Int?, filter: CountryCityPageFilter?): ArrayList<Country> {
		val filteredCountries =
				countries.filter {
					country ->
					(filter?.id == null || filter.id == country.id) &&
							(filter?.city == null  || filter.city == country.code)
				}

		return filteredCountries as ArrayList<Country>
	}

	override fun getAllDetail(masterId: Int, page: Int?, filter: CountryCityPageFilter?): ArrayList<City> {
		val filteredCities =
				cities.filter {
					city ->
					(city.country.id == masterId) &&
					(filter?.id == null || filter.id == city.id) &&
					(filter?.country == null  || filter.country == city.name)
				}

		return filteredCities as ArrayList<City>
	}

	override fun count(): Int {
		return countries.count()
	}

	override fun create(luwakDto: ILuwakDto<Country>?): Boolean {
		val dto = luwakDto as CountryViewModel
		var lastId = countries.sortedBy { it.id }.last().id
		val newCountry = Country(++lastId, dto.name)

		countries.add(newCountry)

		return true
	}

	override fun update(id: Int, luwakDto: ILuwakDto<Country>?): Boolean {
		val dto = luwakDto as CountryViewModel

		val model = countries.find { it.id == id } ?: return false
		model.code = dto.name

		return true
	}


	override fun delete(id: Int, luwakDto: ILuwakDto<Country>?): Boolean {
		countries = countries.filter {it.id != id}.toMutableList()
		return true
	}

	override fun updateDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<Country>): Boolean {
		val dto = luwakDto as CityViewModel

		val model = cities.find { it.id == id && it.country.id == masterId } ?: return false
		model.name = dto.cityName

		return true
	}

	override fun createDetail(masterId: Int, luwakDto: ILuwakDto<Country>): Boolean {
		val dto = luwakDto as CityViewModel
		var lastId = countries.sortedBy { it.id }.last().id
		val country = countries.find { it.id == masterId }
		val newCity = City(++lastId, dto.cityName, country)

		cities.add(newCity)

		return true
	}

	override fun deleteDetail(id: Int, masterId: Int, luwakDto: ILuwakDto<Country>): Boolean {
		cities = cities.filter {it.id != id}.toMutableList()
		return true
	}

	override fun countDetail(masterId: Int): Int {
		return cities.asSequence().filter{it.country.id == masterId}.count()
	}

}
