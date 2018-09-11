package testapp.viewModels;

import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakTable;
import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import lombok.Getter;
import lombok.Setter;
import testapp.models.City;
import testapp.models.Country;

@LuwakTable(canCreate = true, canDelete = true, canEdit = true)
public class CityViewModel implements ILuwakDto<City> {

    @Label("Morenuda1")
    @MapModel(City.NAME)
	@Getter @Setter
    private String cityName;

    @Label("Morenuda2")
	@MapModel(City.COUNTRY + "." + Country.CODE)
	@Getter @Setter
    private String countryName;

}
