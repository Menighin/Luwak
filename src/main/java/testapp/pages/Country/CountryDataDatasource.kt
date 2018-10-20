package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import org.springframework.stereotype.Component
import testapp.models.Country
import testapp.models.CountryData
import java.util.ArrayList

@Component
class CountryDataDatasource : ILuwakDatasource<CountryData, CountryPageFilter> {

	private var countries: MutableList<Country> = mutableListOf(
			Country(0, "Brazil"),
			Country(1, "United States of America"),
			Country(2, "Portugal")
	)

	private var countryDatas: MutableList<CountryData> = mutableListOf(
			CountryData(0, countries[0], "Population", 2000000),
			CountryData(1, countries[0], "GDP", 293299232),
			CountryData(2, countries[1], "Population", 1800000),
			CountryData(3, countries[0], "GDP", 1262693346)
	)

	override fun getById(id: Long): CountryData {
		return countryDatas.find{it.id == id}!!
	}

	override fun getAll(masterId: Long?, page: Int?, filter: CountryPageFilter?, sort: HashMap<String, String>?): ArrayList<CountryData> {
		val filteredCountries =
				countryDatas.filter { countryData ->
					(countryData.country.id == masterId) &&
							(filter?.country == null || filter.country == countryData.country.code) &&
							(filter?.country == null || filter.country == countryData.country.code)
				}

		return filteredCountries as ArrayList<CountryData>
	}

	override fun create(masterId: Long?, luwakDto: ILuwakDto): Boolean {
		return true
	}

	override fun update(masterId: Long?, id: Long, luwakDto: ILuwakDto): Boolean {
		return true
	}

	override fun delete(masterId: Long?, id: Long): Boolean {
		return true
	}

	override fun deleteMany(masterId: Long?, ids: LongArray): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun count(masterId: Long?, filter: CountryPageFilter?): Int {
		return countryDatas.count()
	}
}