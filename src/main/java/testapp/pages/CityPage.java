package testapp.pages;

import com.menighin.luwak.core.LuwakPage;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityFilter;

public class CityPage extends LuwakPage<CityFilter> {

	public CityPage() {
		setMasterTable(new CityDataTable());
	}


}
