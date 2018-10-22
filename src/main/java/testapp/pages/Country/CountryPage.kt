package testapp.pages.Country

import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakModel
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import com.menighin.luwak.core.models.AbstractLuwakMasterDetailPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.models.Country
import java.util.ArrayList

@Component
class CountryPage : AbstractLuwakMasterDetailPage<CountryPageFilter>() {

	@Autowired
	lateinit var countryTable: CountryTable

	@Autowired
	lateinit var cityTable: CityTable

	@Autowired
	lateinit var countryDataTable: CountryDataTable


	override val detailTables: List<AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>>
		get() = listOf(
				cityTable as AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>,
				countryDataTable as AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>
		)

	override val table: AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>
		get() = countryTable as AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>

}

class CountryPageFilter : ILuwakFilter {

	@Label("luwak.country.filter.country")
	var country: String? = null

	@Label("luwak.country.filter.city")
	var city: String? = null
}