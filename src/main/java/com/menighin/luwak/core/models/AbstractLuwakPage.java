package com.menighin.luwak.core.models;

import com.menighin.luwak.core.dtos.LuwakFilterMetadataDto;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import com.menighin.luwak.core.enums.FilterTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDatasource;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public abstract class AbstractLuwakPage<M extends ILuwakModel, F extends ILuwakFilter> {

	@Getter
	private Class<? extends ILuwakModel> modelClass;

	@Getter
	private Class<? extends ILuwakFilter> filterClass;

	@Getter
	@Setter
	private AbstractLuwakDataTable table;

	@Getter
	@Setter
	private ILuwakDatasource<M, F> datasource;

	// Instance initialization
	{
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		modelClass = (Class<? extends ILuwakModel>) parameterized.getActualTypeArguments()[0];
		filterClass = (Class<? extends ILuwakFilter>) parameterized.getActualTypeArguments()[1];
	}

	/**
	 * Gets the page metadata in order to built it.
	 *
	 * @return The metadata object
	 */
	public LuwakPageMetadataDto getPageMetadata() {
		LuwakPageMetadataDto pageMetadata = new LuwakPageMetadataDto();

		if (table != null)
			pageMetadata.setMasterTable(table.getMetadata());

		pageMetadata.setFilters(LuwakFilterMetadataDto.Companion.getFiltersFrom(filterClass));

		return pageMetadata;
	}

	public ArrayList<? extends ILuwakDto> getTableData(int page, F filter) {
		ArrayList<M> models = datasource.getAll(page, filter);
		return table.getTableData(models);
	}

	public void editModel(int id, Map<String, Object> dtoMap) {

		try {

			// Generating DTO model
			Class classDto = table.getClassDto();
			ILuwakDto dto = (ILuwakDto) classDto.newInstance();
			Field[] fields = classDto.getDeclaredFields();

			for(Field f : fields) {

				if (dtoMap.containsKey(f.getName())) {
					f.setAccessible(true);
					f.set(dto, dtoMap.get(f.getName()));
					f.setAccessible(false);
				}
			}

			table.editModel(id, dto);

		} catch (Exception e) {
			// Shut up
		}

	}
}
