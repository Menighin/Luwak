package testapp.pages.Country

import com.fasterxml.jackson.annotation.JsonIgnore
import com.menighin.luwak.core.annotations.*
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import testapp.models.City

@Component
@LuwakTable(title = "City", canCreate = true, canDelete = true, canEdit = true)
class CityTable : AbstractLuwakDataTable<City, CityDto, CountryPageFilter>() {

	@Autowired @Qualifier("HUEHUE")
	lateinit var cityDatasource: ILuwakDatasource<City, CountryPageFilter>

	override val datasource: ILuwakDatasource<City, CountryPageFilter>
		get() = cityDatasource


}

class CityDto : ILuwakDto {

	@Label("Country")
	@MapModel("country.code")
	@ColumnType(ColumnTypeEnum.TEXT)
	@ExcelOnly
	@JsonIgnore
	var countryCode: String? = null

	@Label("ID")
	@MapModel(City.ID)
	@ColumnType(ColumnTypeEnum.HIDDEN)
	var id: Long = 0

	@Label("luwak.city.name")
	@MapModel(City.NAME)
	@ColumnType(ColumnTypeEnum.TEXT)
	lateinit var code: String

}
