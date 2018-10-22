package testapp.viewModels

import com.menighin.luwak.core.annotations.FieldType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.MapModel
import com.menighin.luwak.core.enums.FieldTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDto
import testapp.models.Country

class CountryViewModel : ILuwakDto {

	@Label("luwak.country.name")
	@MapModel(Country.CODE)
	@FieldType(FieldTypeEnum.TEXT)
	var name: String? = null

}