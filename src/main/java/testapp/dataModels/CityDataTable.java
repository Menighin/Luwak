package testapp.dataModels;

import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityRepository;
import testapp.viewModels.CityViewModel;

public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel, CityPageFilter> {

	public CityDataTable() {
		super(new CityRepository());
	}
}
