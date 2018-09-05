package com.menighin.luwak.core.models;

import lombok.Getter;
import lombok.Setter;

public class LuwakPageMetadata {

	@Getter @Setter
	public String title;

	@Getter @Setter
	public LuwakDataTableMetadata masterTable;

	@Getter @Setter
	public LuwakDataTableMetadata slaveTable;

}
