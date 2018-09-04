package com.menighin.luwak.core;

import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.interfaces.ILuwakRepository;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class LuwakDataModel<T extends ILuwakModel, E extends ILuwakDto> {

	private Class<?> classT;
	private Class<?> classE;

    @Getter @Setter
    private ILuwakRepository<T> repository;

    @Getter @Setter
    private LuwakDataModel slave;

    private void initClassTypes() {
		Type superclass = getClass().getGenericSuperclass();
		ParameterizedType parameterized = (ParameterizedType) superclass;
		classT = (Class<?>) parameterized.getActualTypeArguments()[0];
		classE = (Class<?>) parameterized.getActualTypeArguments()[1];
	}

    public LuwakDataModel() {
		initClassTypes();
	}

    public LuwakDataModel(ILuwakRepository<T> repository) {
    	this.repository = repository;
		initClassTypes();
	}

    public Object getMetadata() {
    	int i = 0;
    	return null;
	}

}
