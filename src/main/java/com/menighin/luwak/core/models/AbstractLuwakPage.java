package com.menighin.luwak.core.models;

import com.menighin.luwak.core.dtos.CrudResponse;
import com.menighin.luwak.core.dtos.LuwakFilterMetadataDto;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import com.menighin.luwak.core.enums.ResponseStatusEnum;
import com.menighin.luwak.core.interfaces.ILuwakDatasource;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.exceptions.CrudException;
import kotlin.Pair;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public abstract class AbstractLuwakPage<M extends ILuwakModel, F extends ILuwakFilter> {

	@Autowired
	protected MessageSource messageSource;

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
			pageMetadata.setMasterTable(table.getMetadata(messageSource));

		pageMetadata.setFilters(LuwakFilterMetadataDto.Companion.getFiltersFrom(filterClass));

		return pageMetadata;
	}

	/**
	 * Responsible for generating the list of options for a given field.
	 * Must be overridden by pages having auto-complete fields
	 * @param field Name of the field
	 * @param input The value the user has input in order to filter the options
	 * @return the list of options for a given field
	 */
	public List<Pair<Integer, String>> getFilterValues(String field, String input) throws CrudException {
		return new ArrayList<>();
	}

	public ArrayList<? extends ILuwakDto> getAll(int page, F filter) throws CrudException{
		ArrayList<M> models = datasource.getAll(page, filter);
		return table.getTableData(models);
	}

	public Integer count() throws CrudException {
		return datasource.count();
	}

	public boolean create(Map<String, Object> dtoMap) throws CrudException, Exception {
		ILuwakDto dto = convertMapToDto(dtoMap);
		return datasource.create(dto);
	}

	public boolean editModel(int id, Map<String, Object> dtoMap) throws CrudException, Exception {
		ILuwakDto dto = convertMapToDto(dtoMap);
		return datasource.update(id, dto);
	}

	public boolean deleteModel(int id, Map<String, Object> dtoMap) throws CrudException, Exception {
		ILuwakDto dto = convertMapToDto(dtoMap);
		return datasource.delete(id, dto);
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

	public XSSFWorkbook getExcelFile() {
		ArrayList<M> models = datasource.getAll(null, null);
		return this.table.toExcel(models);
	}

}
