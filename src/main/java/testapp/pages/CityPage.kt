package testapp.pages

import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import com.menighin.luwak.core.models.AbstractLuwakPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.dataModels.CityDataTable
import testapp.filters.CityPageFilter
import testapp.models.City
import testapp.viewModels.CityViewModel

@Component
class CityPage : AbstractLuwakPage<City, CityPageFilter>() {

	@Autowired
	private val cityDataTable: CityDataTable? = null

	override val table: AbstractLuwakDataTable<City, ILuwakDto, CityPageFilter>
		get() {
			return cityDataTable as AbstractLuwakDataTable<City, ILuwakDto, CityPageFilter>
		}

}
