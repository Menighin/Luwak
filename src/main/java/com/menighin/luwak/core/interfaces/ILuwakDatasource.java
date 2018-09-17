package com.menighin.luwak.core.interfaces;

import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {
	ArrayList<M> getAll(int page, F filter);

	boolean editModel(int id, ILuwakDto<M> luwakDto);

	boolean deleteModel(int id, ILuwakDto<M> luwakDto);
}
