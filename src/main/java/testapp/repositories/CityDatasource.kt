package testapp.repositories

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

	var countries: List<Country> = ArrayList()
	var cities: List<City> = ArrayList()

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

	override fun getAll(page: Int, filter: CityPageFilter?): ArrayList<City> {
		val filteredCities =
			cities.filter {
				city ->
					(filter?.id == null || filter.id == city.id) &&
					(filter?.name == null  || filter.name == city.name)
			}

		return filteredCities as ArrayList<City>
	}

	override fun editModel(id: Int, luwakDto: ILuwakDto<City>?): Boolean {
		val dto = luwakDto as CityViewModel

		val model = cities.find { it.id == id } ?: return false
		model.name = dto.cityName

		return true
	}

	override fun deleteModel(id: Int, luwakDto: ILuwakDto<City>?): Boolean {
		cities = cities.filter { it.id != id }

		return true
	}
}
