package com.menighin.luwak.core.dtos;

import com.menighin.luwak.core.enums.ColumnTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ColumnMetadataDto {

	@Getter @Setter
	private String id;

	@Getter @Setter
	private String label;

	@Getter @Setter
	private ColumnTypeEnum columnType;

}
