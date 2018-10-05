package testapp.dataModels

import com.menighin.luwak.core.annotations.LuwakTable
import com.menighin.luwak.core.models.AbstractLuwakDataTable
import org.springframework.stereotype.Component
import testapp.filters.CountryCityPageFilter
import testapp.models.Country
import testapp.viewModels.CountryViewModel

@Component
@LuwakTable(title = "Country", canCreate = true, canDelete = true, canEdit = true)
class CountryDatatable : AbstractLuwakDataTable<Country, CountryViewModel>()