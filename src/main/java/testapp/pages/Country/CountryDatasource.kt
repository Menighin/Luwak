package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import testapp.models.Country
import java.util.ArrayList

class CountryDatasource : ILuwakDatasource<Country, CountryPageFilter> {
	override fun getById(id: Int): Country {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getAll(page: Int?, filter: CountryPageFilter?): ArrayList<Country> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun create(luwakDto: ILuwakDto?): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun update(id: Int, luwakDto: ILuwakDto?): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun delete(id: Int, luwakDto: ILuwakDto?): Boolean {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun count(): Int {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}