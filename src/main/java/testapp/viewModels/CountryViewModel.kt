package testapp.viewModels

import com.menighin.luwak.core.annotations.ColumnType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.MapModel
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDto
import testapp.models.Country

class CountryViewModel : ILuwakDto {

	@Label("luwak.country.name")
	@MapModel(Country.CODE)
	@ColumnType(ColumnTypeEnum.TEXT)
	var name: String? = null

}