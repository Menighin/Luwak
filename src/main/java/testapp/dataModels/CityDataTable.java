package testapp.dataModels;

import com.menighin.luwak.core.annotations.ColumnType;
import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakTable;
import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.enums.ColumnTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.models.AbstractLuwakDataTable;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import testapp.models.City;
import testapp.viewModels.CityViewModel;

@Component
@LuwakTable(title = "City", canCreate = true, canDelete = true, canEdit = true)
public class CityDataTable extends AbstractLuwakDataTable<City, CityViewModel> {

	@Nullable
	@Override
	public Class<?> getModalClass() {
		return ModalDto.class;
	}

	public class ModalDto implements ILuwakDto {
		@Label("luwak.city.name")
		@MapModel(City.NAME)
		@ColumnType(ColumnTypeEnum.TEXT)
		private String cityName;
	}
}
