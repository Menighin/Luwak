package com.menighin.luwak.core.interfaces;

import com.menighin.luwak.core.dtos.CrudResponse;

import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {

	M getById(int id);

	ArrayList<M> getAll(Integer page, F filter);

	boolean create(ILuwakDto<M> luwakDto);

	boolean update(int id, ILuwakDto<M> luwakDto);

	boolean delete(int id, ILuwakDto<M> luwakDto);

	Integer count();
}
