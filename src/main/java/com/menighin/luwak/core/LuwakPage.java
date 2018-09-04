package com.menighin.luwak.core;

import lombok.Setter;

public abstract class LuwakPage {

	@Setter
	private LuwakDataModel dataModel;

	public Object getMetadata() {
		return dataModel.getMetadata();
	}

}
