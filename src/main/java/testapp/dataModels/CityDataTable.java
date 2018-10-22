package testapp.dataModels;

import com.menighin.luwak.core.annotations.FieldType;
import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakTable;
import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.enums.FieldTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDatasource;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testapp.filters.CityPageFilter;
import testapp.models.City;
import testapp.repositories.CityDatasource;
import testapp.viewModels.CityViewModel;

@Component
@LuwakTable(title = "City", canCreate = true, canDelete = true, canEdit = true)
public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel, CityPageFilter> {

	@Autowired
	private CityDatasource cityDatasource;

	@Nullable
	@Override
	public Class<?> getModalClass() {
		return ModalDto.class;
	}

	@NotNull
	@Override
	public ILuwakDatasource<City, CityPageFilter> getDatasource() {
		return cityDatasource;
	}

	public class ModalDto implements ILuwakDto {
		@Label("luwak.city.name")
		@MapModel(City.NAME)
		@FieldType(FieldTypeEnum.TEXT)
		private String cityName;
	}
}
