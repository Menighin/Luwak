package testapp.pages

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

	override fun getDatasource(): ILuwakMasterDetailDatasource<Country, City, CountryCityPageFilter> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getDetailTable(): AbstractLuwakDataTable<City, *> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getTable(): AbstractLuwakDataTable<Country, *> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getMasterFields(): ArrayList<String> {
		return arrayListOf(CountryViewModel::name.name)
	}

}