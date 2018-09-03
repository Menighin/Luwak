package com.menighin.luwak.core;

import com.menighin.luwak.core.interfaces.ILuwakDto;
import com.menighin.luwak.core.interfaces.ILuwakModel;
import com.menighin.luwak.core.interfaces.ILuwakRepository;
import lombok.Getter;
import lombok.Setter;

public abstract class LuwakDataModel<T extends ILuwakModel, E extends ILuwakDto> {

    @Getter @Setter
    private ILuwakRepository<T> repository;

    @Getter @Setter
    private LuwakDataModel slave;

    public LuwakDataModel() {}

    public LuwakDataModel(ILuwakRepository<T> repository) {
        this.repository = repository;
    }

}
