package com.menighin.luwak.core.interfaces;

import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {
	ArrayList<M> getAll(int page, F filter);

	boolean update(int id, ILuwakDto<M> luwakDto);

	boolean create(ILuwakDto<M> luwakDto);

	boolean delete(int id, ILuwakDto<M> luwakDto);
}
