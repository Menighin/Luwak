package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import testapp.models.City
import testapp.models.Country
import testapp.viewModels.CityViewModel
import java.util.ArrayList

@Component("HUEHUE")
class CityDatasource : ILuwakDatasource<City, CountryPageFilter> {

	private var countries: MutableList<Country> = mutableListOf(Country(0, "Brazil"), Country(1, "United States of America"), Country(2, "Portugal"))
	private var cities: MutableList<City> = mutableListOf(
			City(0, "Belo Horizonte", countries[0]),
			City(1, "Sao Paulo", countries[0]),
			City(2, "Denver", countries[1]),
			City(3, "San Francisco", countries[1]),
			City(4, "Porto", countries[2]),
			City(5, "Lisboa", countries[2])
	)

	override fun getById(id: Int): City? {
		return cities.find { it.id == id }
	}

	override fun getAll(masterId: Int?, page: Int?, filter: CountryPageFilter?): ArrayList<City> {
		val filteredCities =
				cities.filter {
					city ->
					(city.country.id == masterId) &&
					(filter?.city == null || filter.city == city.name) &&
							(filter?.city == null  || filter.city == city.name)
				}

		return filteredCities as ArrayList<City>
	}

	override fun create(masterId: Int?, luwakDto: ILuwakDto): Boolean {
		val dto = luwakDto as CityViewModel
		val countryId = dto.countryName.toInt()
		val country = countries.find { it.id == countryId }
		var lastId = cities.sortedBy { it.id }.last().id
		val newCity = City(++lastId, dto.cityName, country)

		cities.add(newCity)

		return true
	}

	override fun update(masterId: Int?, id: Int, luwakDto: ILuwakDto?): Boolean {
		val dto = luwakDto as CityViewModel

		val model = cities.find { it.id == id } ?: return false
		model.name = dto.cityName

		return true
	}

	override fun delete(masterId: Int?, id: Int): Boolean {
		cities = cities.filter {it.id != id}.toMutableList()

		return true
	}

	override fun deleteMany(masterId: Int?, ids: IntArray): Boolean {
		cities = cities.filter { ids.contains(it.id) }.toMutableList()

		return true
	}

	override fun count(masterId: Int?): Int {
		return cities.count()
	}
}