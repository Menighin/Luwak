package testapp.pages

import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakModel
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import com.menighin.luwak.core.models.AbstractLuwakPage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.dataModels.CityDataTable
import testapp.filters.CityPageFilter
import testapp.models.City
import testapp.viewModels.CityViewModel

@Component
class CityPage : AbstractLuwakPage<CityPageFilter>() {

	@Autowired
	private val cityDataTable: CityDataTable? = null

	override val table: AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CityPageFilter>
		get() {
			return cityDataTable as AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CityPageFilter>
		}

}
