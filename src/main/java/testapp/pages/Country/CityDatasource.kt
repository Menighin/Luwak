package testapp.pages.Country

import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import testapp.models.City
import testapp.models.Country
import java.util.ArrayList

class CityDatasource : ILuwakDatasource<City, CountryPageFilter> {
	override fun getById(id: Int): City {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun getAll(page: Int?, filter: CountryPageFilter?): ArrayList<City> {
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