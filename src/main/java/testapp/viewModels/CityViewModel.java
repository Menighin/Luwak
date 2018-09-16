package testapp.viewModels;

import com.menighin.luwak.core.annotations.ColumnType;
import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakTable;
import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.enums.ColumnTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import lombok.Getter;
import lombok.Setter;
import testapp.models.City;
import testapp.models.Country;

@LuwakTable(canCreate = true, canDelete = true, canEdit = true)
public class CityViewModel implements ILuwakDto<City> {

    @Label("City")
    @MapModel(City.NAME)
	@ColumnType(ColumnTypeEnum.Text)
    private String cityName;

    @Label("Country")
	@MapModel(City.COUNTRY + "." + Country.CODE)
	@ColumnType(ColumnTypeEnum.Select)
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
