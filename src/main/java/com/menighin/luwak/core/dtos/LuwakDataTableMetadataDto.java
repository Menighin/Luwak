package com.menighin.luwak.core.dtos;

import com.menighin.luwak.core.annotations.Label;
import com.menighin.luwak.core.annotations.LuwakTable;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class LuwakDataTableMetadataDto {

	@Getter @Setter
	private String title;

	@Getter @Setter
	private boolean canCreate;

	@Getter @Setter
	private boolean canEdit;

	@Getter @Setter
	private boolean canDelete;

	@Getter @Setter
	private ArrayList<ColumnMetadataDto> columns;

	// Initializer
	{
		columns = new ArrayList<>();
	}

	public LuwakDataTableMetadataDto(Class clazz) {

		// If class is not annotated with @LuwakTable, return
		if (!clazz.isAnnotationPresent(LuwakTable.class)) return;

		// Getting the permissions
		LuwakTable luwakTableAnnotation = (LuwakTable) clazz.getAnnotation(LuwakTable.class);
		canCreate = luwakTableAnnotation.canCreate();
		canDelete = luwakTableAnnotation.canDelete();
		canEdit   = luwakTableAnnotation.canEdit();

		Field[] fields = clazz.getDeclaredFields();

		// Iterate through fields generating the metadata
		for (Field f : fields) {
			if (!f.isAnnotationPresent(Label.class)) continue;

			Label label = f.getAnnotation(Label.class);

			columns.add(new ColumnMetadataDto(f.getName(), label.value(), null));
		}
	}
}
