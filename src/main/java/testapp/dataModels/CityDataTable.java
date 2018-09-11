package testapp.dataModels;

import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import testapp.models.City;
import testapp.repositories.CityRepository;
import testapp.viewModels.CityViewModel;

public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel> {

	public CityDataTable() {
		super(new CityRepository());
	}
}
