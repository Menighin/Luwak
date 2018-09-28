package testapp.pages

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
	override fun getMasterFields(): ArrayList<String> {
		return arrayListOf(CountryViewModel::name.name)
	}

	@Autowired
	var countryCityDatasource: CountryCityDatasource? = null

	@PostConstruct
	fun postConstruct() {
		datasource = countryCityDatasource
		table = CountryDatatable()
		detailTable = CityDataTable()
	}

}