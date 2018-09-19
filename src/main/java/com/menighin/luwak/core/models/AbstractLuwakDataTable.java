package com.menighin.luwak.core.models;

import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.dtos.LuwakDataTableMetadataDto;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

@NoArgsConstructor
public abstract class AbstractLuwakDataTable<M extends ILuwakModel, D extends ILuwakDto, F extends ILuwakFilter> {

	private Class<M> classModel;
	private Class<D> classDto;

	public Class<M> getClassModel() {
		return classModel;
	}

	public Class<D> getClassDto() {
		return classDto;
	}

	// Instance initialization
    {
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		classModel = (Class<M>) parameterized.getActualTypeArguments()[0];
		classDto = (Class<D>) parameterized.getActualTypeArguments()[1];
	}

    public LuwakDataTableMetadataDto getMetadata() {
    	return new LuwakDataTableMetadataDto(this);
	}

	public ArrayList<D> getTableData(ArrayList<M> models) {
		ArrayList<D> dtos = new ArrayList<>();

    	Field[] dtoFields = classDto.getDeclaredFields();

    	for (M m : models) {
			try {
				D dto = classDto.newInstance();

				// Mapping fields to Dto
				for (Field f : dtoFields) {
					if (!f.isAnnotationPresent(MapModel.class)) continue;

					String[] mapModel = f.getAnnotation(MapModel.class).value().split("\\.");

					// Access all objects until last level
					Object leaf = m;
					for (String map : mapModel) {
						String fieldName = map.substring(0, 1).toUpperCase() + map.substring(1);
						leaf = leaf.getClass().getMethod("get" + fieldName).invoke(leaf);
					}

					f.setAccessible(true);
					f.set(dto, leaf);
					f.setAccessible(false);
				}

				dtos.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

}
