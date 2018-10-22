package testapp.pages.Country

import com.menighin.luwak.core.annotations.FieldType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.annotations.MapModel
import com.menighin.luwak.core.enums.FieldTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.models.Country

@Component
@LuwakTable(title = "Country", canCreate = true, canDelete = true, canEdit = true)
class CountryTable : AbstractLuwakDataTable<Country, CountryDto, CountryPageFilter>() {

	@Autowired
	lateinit var countryDatasource: ILuwakDatasource<Country, CountryPageFilter>

	override val datasource: ILuwakDatasource<Country, CountryPageFilter>
		get() = countryDatasource

}

class CountryDto : ILuwakDto {

	@Label("ID")
	@MapModel(Country.ID)
	@FieldType(FieldTypeEnum.HIDDEN)
	var id: Long = 0

	@Label("luwak.country.name")
	@MapModel(Country.CODE)
	@FieldType(FieldTypeEnum.TEXT)
	lateinit var name: String

}