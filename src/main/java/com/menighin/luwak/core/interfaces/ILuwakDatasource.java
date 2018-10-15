package com.menighin.luwak.core.interfaces;

import com.menighin.luwak.core.dtos.CrudResponse;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface ILuwakDatasource<M extends ILuwakModel, F extends ILuwakFilter> {

	M getById(int id);

	ArrayList<M> getAll(Integer masterId, Integer page, F filter);

	boolean create(Integer masterId, ILuwakDto luwakDto);

	boolean update(Integer masterId, int id, ILuwakDto luwakDto);

	boolean delete(Integer masterId, int id);

	boolean deleteMany(Integer masterId, @NotNull int[] ids);

	Integer count(Integer masterId);
}
