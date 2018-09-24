package com.menighin.luwak.core.models;

import com.menighin.luwak.core.dtos.CrudResponse;
import com.menighin.luwak.core.dtos.LuwakFilterMetadataDto;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import com.menighin.luwak.core.enums.ResponseStatusEnum;
import com.menighin.luwak.core.interfaces.ILuwakDatasource;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

@Component
public abstract class AbstractLuwakPage<M extends ILuwakModel, F extends ILuwakFilter> {

	private Class<? extends ILuwakModel> masterClass;
	private Class<? extends ILuwakFilter> filterClass;

	private AbstractLuwakDataTable<M, ?> table;

	private ILuwakDatasource<M, F> datasource;

	public AbstractLuwakDataTable getTable() {
		return table;
	}

	public void setFilterClass(Class<? extends ILuwakFilter> filterClass) {
		this.filterClass = filterClass;
	}

	public void setTable(AbstractLuwakDataTable table) {
		this.table = table;
	}

	public ILuwakDatasource<M, F> getDatasource() {
		return datasource;
	}

	public void setDatasource(ILuwakDatasource<M, F> datasource) {
		this.datasource = datasource;
	}

	public Class<? extends ILuwakModel> getModelClass() {
		return masterClass;
	}

	public Class<? extends ILuwakFilter> getFilterClass() {
		return filterClass;
	}

	// Instance initialization
	{
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		masterClass = (Class<? extends ILuwakModel>) parameterized.getActualTypeArguments()[0];
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

	public CrudResponse<ArrayList<? extends ILuwakDto>> getAll(int page, F filter) {
		CrudResponse<ArrayList<M>> crudResponse = datasource.getAll(page, filter);
		if (crudResponse != null && crudResponse.getStatus() == ResponseStatusEnum.SUCCESS)
			return new CrudResponse<ArrayList<? extends ILuwakDto>>(ResponseStatusEnum.SUCCESS,
					table.getTableData(crudResponse.getData()),
					crudResponse.getValidations(),
					crudResponse.getMsg());
		return new CrudResponse<>(ResponseStatusEnum.ERROR, null, crudResponse.getValidations(), crudResponse.getMsg());
	}

	public CrudResponse<Integer> count() {
		return datasource.count();
	}

	public CrudResponse create(Map<String, Object> dtoMap) {
		try {
			ILuwakDto dto = convertMapToDto(dtoMap);
			return datasource.create(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return new CrudResponse(ResponseStatusEnum.ERROR, null, null, e.getMessage());
		}
	}

	public CrudResponse editModel(int id, Map<String, Object> dtoMap) {
		try {
			ILuwakDto dto = convertMapToDto(dtoMap);
			return datasource.update(id, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return new CrudResponse(ResponseStatusEnum.ERROR, null, null, e.getMessage());
		}
	}

	public CrudResponse deleteModel(int id, Map<String, Object> dtoMap) {
		try {
			ILuwakDto dto = convertMapToDto(dtoMap);
			return datasource.delete(id, dto);
		} catch (Exception e) {
			e.printStackTrace();
			return new CrudResponse(ResponseStatusEnum.ERROR, null, null, e.getMessage());
		}
	}

	private ILuwakDto convertMapToDto(Map<String, Object> model) throws IllegalAccessException, InstantiationException {
		// Generating DTO model
		Class classDto = table.getClassDto();
		ILuwakDto dto = (ILuwakDto) classDto.newInstance();
		Field[] fields = classDto.getDeclaredFields();

		for(Field f : fields) {

			if (model.containsKey(f.getName())) {
				f.setAccessible(true);
				f.set(dto, model.get(f.getName()));
				f.setAccessible(false);
			}
		}

		return dto;
	}



}
