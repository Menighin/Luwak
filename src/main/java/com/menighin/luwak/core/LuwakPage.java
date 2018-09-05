package com.menighin.luwak.core;

import com.menighin.luwak.core.models.LuwakPageMetadata;
import lombok.Setter;

public abstract class LuwakPage {

	@Setter
	private LuwakDataTable masterTable;

	@Setter
	private LuwakDataTable slaveTable;

	@Setter
	private String slaveMasterField;


	public LuwakPageMetadata getPageMetadata() {
		LuwakPageMetadata pageMetadata = new LuwakPageMetadata();

		if (masterTable != null)
			pageMetadata.setMasterTable(masterTable.getMetadata());

		if (slaveTable != null)
			pageMetadata.setSlaveTable(slaveTable.getMetadata());

		return pageMetadata;
	}

}
