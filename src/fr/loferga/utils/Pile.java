package fr.loferga.utils;

public interface Pile<T> {
	
	void empiler(T e);
	
	T sommet();
	
	T depiler();
	
	boolean isEmpty();
	
	int size();

}
