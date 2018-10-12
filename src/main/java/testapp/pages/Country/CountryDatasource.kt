package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import org.springframework.stereotype.Component
import testapp.models.Country
import java.util.ArrayList

@Component
class CountryDatasource : ILuwakDatasource<Country, CountryPageFilter> {

	private var countries: MutableList<Country> = mutableListOf(Country(0, "Brazil"), Country(1, "United States of America"), Country(2, "Portugal"))

	override fun getById(id: Int): Country? {
		return countries.find{it.id == id}
	}

	override fun getAll(page: Int?, filter: CountryPageFilter?): ArrayList<Country> {
		val filteredCountries =
				countries.filter {
					country ->
					(filter?.country == null || filter.country == country.code) &&
							(filter?.country == null  || filter.country == country.code)
				}

		return filteredCountries as ArrayList<Country>
	}

	override fun create(luwakDto: ILuwakDto?): Boolean {
		return true
	}

	override fun update(id: Int, luwakDto: ILuwakDto?): Boolean {
		return true
	}

	override fun delete(id: Int, luwakDto: ILuwakDto?): Boolean {
		return true
	}

	override fun count(): Int {
		return countries.count()
	}
}