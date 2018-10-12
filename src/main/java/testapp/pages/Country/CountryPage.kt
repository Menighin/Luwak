package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.interfaces.ILuwakFilter
import com.menighin.luwak.core.interfaces.ILuwakModel
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import com.menighin.luwak.core.models.AbstractLuwakMasterDetailPage
import testapp.models.Country
import java.util.ArrayList

class CountryPage : AbstractLuwakMasterDetailPage<Country, CountryPageFilter>() {

	override val detailTables: List<AbstractLuwakDataTable<ILuwakModel, ILuwakDto, CountryPageFilter>>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

	override fun getMasterFields(): ArrayList<String> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override val table: AbstractLuwakDataTable<Country, ILuwakDto, CountryPageFilter>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.




}

class CountryPageFilter(val country: String, val city: String) : ILuwakFilter