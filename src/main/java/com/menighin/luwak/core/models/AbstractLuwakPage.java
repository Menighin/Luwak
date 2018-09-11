package com.menighin.luwak.core.models;

import com.menighin.luwak.core.dtos.LuwakFilterMetadataDto;
import com.menighin.luwak.core.dtos.LuwakPageMetadataDto;
import com.menighin.luwak.core.enums.FilterTypeEnum;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class AbstractLuwakPage<T extends ILuwakFilter> {

	@Getter
	private Class<?> filterClass;

	@Getter	@Setter
	private AbstractLuwakDataTable masterTable;

	@Getter @Setter
	private AbstractLuwakDataTable slaveTable;

	@Setter
	private String slaveMasterField;

	// Instance initialization
	{
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		filterClass = (Class<?>) parameterized.getActualTypeArguments()[0];
	}

	/**
	 * Gets the page metadata in order to built it.
	 * @return The metadata object
	 */
	public LuwakPageMetadataDto getPageMetadata() {
		LuwakPageMetadataDto pageMetadata = new LuwakPageMetadataDto();

		if (masterTable != null)
			pageMetadata.setMasterTable(masterTable.getMetadata());

		if (slaveTable != null)
			pageMetadata.setSlaveTable(slaveTable.getMetadata());

		pageMetadata.setFilters(LuwakFilterMetadataDto.Companion.getFiltersFrom(filterClass));

		return pageMetadata;
	}

	public ArrayList<ILuwakDto> getMasterTableData(ILuwakFilter filter) {
		return masterTable.getData(filter);
	}

}
