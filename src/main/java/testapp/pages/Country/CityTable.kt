package testapp.pages.Country

import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.stereotype.Component
import testapp.models.City

@Component
@LuwakTable(title = "City", canCreate = true, canDelete = true, canEdit = true)
class CityTable : AbstractLuwakDataTable<City, CityDto, CountryPageFilter>() {

	override val datasource: ILuwakDatasource<City, CountryPageFilter>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


}

class CityDto(val code: String) : ILuwakDto