package testapp.dataModels;

import com.menighin.luwak.core.LuwakDataTable;
import testapp.models.City;
import testapp.repositories.CityRepository;
import testapp.viewModels.CityViewModel;

public class CityDataTable extends LuwakDataTable<City, CityViewModel> {

	public CityDataTable() {
		super(new CityRepository());
	}
}
