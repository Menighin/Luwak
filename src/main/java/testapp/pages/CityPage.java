package testapp.pages;

import com.menighin.luwak.core.LuwakPage;
import testapp.dataModels.CityDataTable;

public class CityPage extends LuwakPage {

	public CityPage() {
		setMasterTable(new CityDataTable());
	}


}
