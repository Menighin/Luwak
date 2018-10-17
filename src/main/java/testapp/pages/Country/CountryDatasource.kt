package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import org.springframework.stereotype.Component
import testapp.models.Country
import java.util.ArrayList

@Component
class CountryDatasource : ILuwakDatasource<Country, CountryPageFilter> {

	private var countries: MutableList<Country> = mutableListOf(Country(0, "Brazil"), Country(1, "United States of America"), Country(2, "Portugal"))

	override fun getById(id: Long): Country {
		return countries.find{it.id == id}!!
	}

	override fun getAll(masterId: Long?, page: Int?, filter: CountryPageFilter?): ArrayList<Country> {
		val filteredCountries =
				countries.filter {
					country ->
					(filter?.country == null || filter.country == country.code) &&
							(filter?.country == null  || filter.country == country.code)
				}

		return filteredCountries as ArrayList<Country>
	}

	override fun create(masterId: Long?, luwakDto: ILuwakDto): Boolean {
		val countryDto = luwakDto as CountryDto
		val lastId = countries.last().id
		countries.add(Country(lastId + 1, countryDto.name))

		return true
	}

	override fun update(masterId: Long?, id: Long, luwakDto: ILuwakDto): Boolean {
		var country = countries.find { it.id == id }!!
		val countryDto = luwakDto as CountryDto
		country.code = countryDto.name
		return true
	}

	override fun delete(masterId: Long?, id: Long): Boolean {
		countries.removeAll { it.id == id }
		return true
	}

	override fun deleteMany(masterId: Long?, ids: LongArray): Boolean {
		countries.removeAll { ids.contains(it.id) }
		return true
	}

	override fun count(masterId: Long?): Int {
		return countries.count()
	}
}