package com.menighin.luwak.core.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class LuwakPageMetadataDto {

	@Getter @Setter
	private String title;

	@Getter @Setter
	private LuwakDataTableMetadataDto masterTable;

	@Getter @Setter
	private LuwakDataTableMetadataDto slaveTable;

	@Getter @Setter
	private ArrayList<LuwakFilterMetadataDto> filters;

	public LuwakPageMetadataDto() {
		filters = new ArrayList<>();
	}

}
