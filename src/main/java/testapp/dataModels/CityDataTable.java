package testapp.dataModels;

import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityDatasource;
import testapp.viewModels.CityViewModel;

public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel, CityPageFilter> {
	public CityDataTable() {
	}
}
