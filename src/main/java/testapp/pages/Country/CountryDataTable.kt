package testapp.pages.Country

import com.menighin.luwak.core.annotations.ColumnType
import com.menighin.luwak.core.annotations.Label
import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.annotations.MapModel
import com.menighin.luwak.core.enums.ColumnTypeEnum
import com.menighin.luwak.core.interfaces.ILuwakDatasource
import com.menighin.luwak.core.interfaces.ILuwakDto
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import testapp.models.Country
import testapp.models.CountryData

@Component
@LuwakTable(title = "Country", canCreate = true, canDelete = true, canEdit = true)
class CountryDataTable : AbstractLuwakDataTable<CountryData, CountryDataDto, CountryPageFilter>() {

	@Autowired
	lateinit var countryDataDatasource: ILuwakDatasource<CountryData, CountryPageFilter>

	override val datasource: ILuwakDatasource<CountryData, CountryPageFilter>
		get() = countryDataDatasource

}

class CountryDataDto : ILuwakDto {

	@Label("ID")
	@MapModel(CountryData.ID)
	@ColumnType(ColumnTypeEnum.HIDDEN)
	var id: Int = 0

	@Label("Key")
	@MapModel(CountryData.KEY)
	@ColumnType(ColumnTypeEnum.TEXT)
	lateinit var name: String

	@Label("Value")
	@MapModel(CountryData.VALUE)
	@ColumnType(ColumnTypeEnum.INTEGER)
	var value: Int = 0
}