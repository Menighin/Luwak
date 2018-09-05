package com.menighin.luwak.core.models;

import com.menighin.luwak.core.enums.ColumnTypeEnum;
import lombok.Getter;
import lombok.Setter;

public class ColumnMetadata {

	@Getter @Setter
	public String label;

	@Getter @Setter
	public ColumnTypeEnum columnType;

}
