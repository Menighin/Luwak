package testapp.viewModels;

import com.menighin.luwak.core.annotations.ColumnType;
import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.enums.ColumnTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import testapp.models.City;
import testapp.models.Country;

public class CityViewModel implements ILuwakDto {

    @Label("luwak.city.name")
    @MapModel(City.NAME)
	@ColumnType(ColumnTypeEnum.TEXT)
    private String cityName;

    @Label("luwak.city.country")
	@MapModel(City.COUNTRY + "." + Country.CODE)
	@ColumnType(ColumnTypeEnum.SELECT)
    private String countryName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
