package com.menighin.luwak.core;

import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.interfaces.ILuwakRepository;

public abstract class LuwakDataModel<T extends ILuwakModel, E extends ILuwakDto> {

    private ILuwakRepository<T> repository;

    public LuwakDataModel() {}

    public LuwakDataModel(ILuwakRepository<T> repository) {
        this.repository = repository;
    }

}
