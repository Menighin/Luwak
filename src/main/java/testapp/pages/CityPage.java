package testapp.pages;

import com.menighin.luwak.core.interfaces.ILuwakDatasource;
import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import com.menighin.luwak.core.models.AbstractLuwakPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityDatasource;

@Component
public class CityPage extends AbstractLuwakPage<City, CityPageFilter> {

	@Autowired
	private CityDatasource cityDatasource;

	@Autowired
	private CityDataTable cityDataTable;

	@Override
	public AbstractLuwakDataTable<City, ?> getTable() {
		return cityDataTable;
	}

	@Override
	public ILuwakDatasource<City, CityPageFilter> getDatasource() {
		return cityDatasource;
	}
}
