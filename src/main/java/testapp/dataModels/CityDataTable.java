package testapp.dataModels;

import com.menighin.luwak.core.annotations.LuwakTable;
import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.viewModels.CityViewModel;

@LuwakTable(title = "City", canCreate = true, canDelete = true, canEdit = true)
public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel, CityPageFilter> {
	public CityDataTable() {
		super();
	}
}
