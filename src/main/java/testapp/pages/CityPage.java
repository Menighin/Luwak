package testapp.pages;

import com.menighin.luwak.core.models.AbstractLuwakPage;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityDatasource;

public class CityPage extends AbstractLuwakPage<City, CityPageFilter> {

	public CityPage() {
		setTable(new CityDataTable());
		setDatasource(new CityDatasource());
	}


}
