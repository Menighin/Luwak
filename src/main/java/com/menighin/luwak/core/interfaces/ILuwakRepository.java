package com.menighin.luwak.core.interfaces;

import java.util.ArrayList;

public interface ILuwakRepository<T extends ILuwakModel, E extends ILuwakFilter> {

    ArrayList<T> getAll(E filter);

}
