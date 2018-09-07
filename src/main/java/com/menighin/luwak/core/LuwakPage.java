package com.menighin.luwak.core;

import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.models.LuwakPageMetadata;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class LuwakPage<T extends ILuwakFilter> {

	private Class<?> filterClass;

	@Getter	@Setter
	private LuwakDataTable masterTable;

	@Getter @Setter
	private LuwakDataTable slaveTable;

	@Setter
	private String slaveMasterField;


	// Instance initialization
	{
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		filterClass = (Class<?>) parameterized.getActualTypeArguments()[0];
	}

	public LuwakPageMetadata getPageMetadata() {
		LuwakPageMetadata pageMetadata = new LuwakPageMetadata();

		if (masterTable != null)
			pageMetadata.setMasterTable(masterTable.getMetadata());

		if (slaveTable != null)
			pageMetadata.setSlaveTable(slaveTable.getMetadata());

		return pageMetadata;
	}

}
