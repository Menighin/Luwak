package com.menighin.luwak.core.interfaces;

import java.util.ArrayList;

public interface ILuwakRepository<T extends ILuwakModel> {

    ArrayList<T> getAll();

}
