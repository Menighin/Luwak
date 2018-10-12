package testapp.pages.Country

import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.stereotype.Component
import testapp.models.Country

@Component
@LuwakTable(title = "Country", canCreate = true, canDelete = true, canEdit = true)
class CountryTable : AbstractLuwakDataTable<Country, CountryDto, CountryPageFilter>() {

	override val datasource: ILuwakDatasource<Country, CountryPageFilter>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


}

class CountryDto(val code: String) : ILuwakDto