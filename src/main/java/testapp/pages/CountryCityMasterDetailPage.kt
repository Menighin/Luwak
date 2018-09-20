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
import javax.annotation.PostConstruct

@Component
class CountryCityMasterDetailPage : AbstractLuwakMasterDetailPage<Country, City, CountryCityPageFilter>() {

	@Autowired
	var countryCityDatasource: CountryCityDatasource? = null

	@PostConstruct
	fun postConstruct() {
		datasource = countryCityDatasource
		table = CountryDatatable()
		detailTable = CityDataTable()
	}

}