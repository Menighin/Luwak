package testapp.pages;

import com.menighin.luwak.core.models.AbstractLuwakPage;
import testapp.dataModels.CityDataTable;
import testapp.filters.CityPageFilter;

public class CityPage extends AbstractLuwakPage<CityPageFilter> {

	public CityPage() {
		setMasterTable(new CityDataTable());
	}


}
