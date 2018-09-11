package com.menighin.luwak.core.models;

import com.menighin.luwak.core.annotations.MapModel;
import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakFilter;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.interfaces.ILuwakRepository;
import com.menighin.luwak.core.dtos.LuwakDataTableMetadataDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

@NoArgsConstructor
public abstract class AbstractLuwakDataTable<T extends ILuwakModel, E extends ILuwakDto, F extends ILuwakFilter> {

	private Class<T> classT;
	private Class<E> classE;

    @Getter @Setter
    private ILuwakRepository<T, F> repository;

    // Instance initialization
    {
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		classT = (Class<T>) parameterized.getActualTypeArguments()[0];
		classE = (Class<E>) parameterized.getActualTypeArguments()[1];
	}

    public AbstractLuwakDataTable(ILuwakRepository<T, F> repository) {
    	this.repository = repository;
	}

    public LuwakDataTableMetadataDto getMetadata() {
    	return new LuwakDataTableMetadataDto(classE);
	}


	public ArrayList<E> getData(F filter) {
    	ArrayList<T> models = repository.getAll(filter);
		ArrayList<E> dtos = new ArrayList<>();

    	Field[] dtoFields = classE.getDeclaredFields();

    	for (T m : models) {
			try {
				E dto = classE.newInstance();

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
