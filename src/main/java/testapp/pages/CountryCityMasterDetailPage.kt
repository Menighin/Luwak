package testapp.pages

import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakMasterDetailDatasource
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import com.menighin.luwak.core.models.AbstractLuwakMasterDetailPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.dataModels.CityDataTable
import testapp.dataModels.CountryDatatable
import testapp.filters.CountryCityPageFilter
import testapp.models.City
import testapp.models.Country
import testapp.repositories.CountryCityDatasource
import testapp.viewModels.CountryViewModel
import java.util.ArrayList
import javax.annotation.PostConstruct

@Component
class CountryCityMasterDetailPage : AbstractLuwakMasterDetailPage<Country, City, CountryCityPageFilter>() {

	@Autowired
	var countryCityDatasource: CountryCityDatasource? = null

	@Autowired
	var cityDataTable: CityDataTable? = null

	@Autowired
	var countryDatatable: CountryDatatable? = null

	override fun getMasterFields(): ArrayList<String> {
		return arrayListOf(CountryViewModel::name.name)
	}

	override val detailTable: AbstractLuwakDataTable<City, *>
		get() = cityDataTable!!

	override val datasource: ILuwakMasterDetailDatasource<Country, City, CountryCityPageFilter>
		get() = countryCityDatasource!!

	override val table: AbstractLuwakDataTable<Country, out ILuwakDto>?
		get() = countryDatatable!!

}