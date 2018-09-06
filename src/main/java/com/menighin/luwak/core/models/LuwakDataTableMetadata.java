package com.menighin.luwak.core.models;

import com.menighin.luwak.core.annotations.Label;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class LuwakDataTableMetadata {

	@Getter @Setter
	private String title;

	@Getter @Setter
	private boolean canCreate;

	@Getter @Setter
	private boolean canEdit;

	@Getter @Setter
	private boolean canDelete;

	@Getter @Setter
	private ArrayList<ColumnMetadata> columns;


	public LuwakDataTableMetadata(Class clazz) {

		columns = new ArrayList<>();

		Field[] fields = clazz.getDeclaredFields();

		for (Field f : fields) {
			if (!f.isAnnotationPresent(Label.class)) continue;

			ColumnMetadata column = new ColumnMetadata();
			Label label = f.getAnnotation(Label.class);
			column.setLabel(label.value());

			columns.add(column);
		}
	}

}
