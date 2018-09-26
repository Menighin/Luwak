package com.menighin.luwak.core.interfaces;

import com.menighin.luwak.core.dtos.CrudResponse;

import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {

	CrudResponse<ArrayList<M>> getAll(Integer page, F filter);

	CrudResponse create(ILuwakDto<M> luwakDto);

	CrudResponse update(int id, ILuwakDto<M> luwakDto);

	CrudResponse delete(int id, ILuwakDto<M> luwakDto);

	CrudResponse<Integer> count();
}
