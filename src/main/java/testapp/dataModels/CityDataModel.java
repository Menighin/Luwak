package testapp.dataModels;

import com.menighin.luwak.core.LuwakDataModel;
import testapp.models.City;
import testapp.repositories.CityRepository;
import testapp.viewModels.CityViewModel;

public class CityDataModel extends LuwakDataModel<City, CityViewModel> {

	public CityDataModel() {
		super(new CityRepository());
	}
}
