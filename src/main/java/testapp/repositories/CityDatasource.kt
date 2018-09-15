package testapp.repositories

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import testapp.filters.CityPageFilter
import testapp.models.City
import testapp.models.Country
import java.util.ArrayList

class CityDatasource: ILuwakDatasource<City, CityPageFilter> {

	override fun getAll(page: Int, filter: CityPageFilter?): ArrayList<City> {

		val brazil = Country(0, "Brazil")
		val usa = Country(1, "United States of America")
		val portugal = Country(2, "Portugal")
		val cities = object: ArrayList<City>() {
			init {
				add(City(0, "Belo Horizonte", brazil))
				add(City(1, "Sao Paulo", brazil))
				add(City(2, "Denver", usa))
				add(City(3, "San Francisco", usa))
				add(City(4, "Porto", portugal))
				add(City(5, "Lisboa", portugal))
			}
		}

		val filteredCities =
			cities.filter {
				city ->
					(filter?.id == null || filter.id == city.id) &&
					(filter?.name == null  || filter.name == city.name)
			}

		return filteredCities as ArrayList<City>
	}
}