package com.menighin.luwak.core.interfaces;

import com.menighin.luwak.core.dtos.CrudResponse;

import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {

	CrudResponse<ArrayList<M>> getAll(int page, F filter);

	CrudResponse update(int id, ILuwakDto<M> luwakDto);

	CrudResponse create(ILuwakDto<M> luwakDto);

	CrudResponse delete(int id, ILuwakDto<M> luwakDto);
}
