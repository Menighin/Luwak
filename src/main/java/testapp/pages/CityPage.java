package testapp.pages;

import com.menighin.luwak.core.models.AbstractLuwakPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityDatasource;
import testapp.repositories.TestDatasource;

import javax.annotation.PostConstruct;

@Component
public class CityPage extends AbstractLuwakPage<City, CityPageFilter> {

	@Autowired
	private CityDatasource cityDatasource;

	public CityPage() {
	}

	@PostConstruct
	private void what() {
		setTable(new CityDataTable());
		setDatasource(cityDatasource);
	}


}
