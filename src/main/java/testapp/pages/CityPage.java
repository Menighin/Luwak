package testapp.pages;

import com.menighin.luwak.core.LuwakPage;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityPageFilter;

public class CityPage extends LuwakPage<CityPageFilter> {

	public CityPage() {
		setMasterTable(new CityDataTable());
	}


}
