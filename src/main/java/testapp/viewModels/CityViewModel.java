package testapp.viewModels;

import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import testapp.models.City;

@LuwakDto
public class CityViewModel implements ILuwakDto<City> {

    @Label("Morenuda1")
    private String cityName;

    @Label("Morenuda2")
    private String countryName;

}
