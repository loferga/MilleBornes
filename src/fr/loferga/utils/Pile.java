package fr.loferga.utils;

import java.util.List;

public interface Pile<T> extends List<T> {
	
	T depiler();
	
	void empiler(T e);

}
