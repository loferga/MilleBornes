package fr.loferga.mille_bornes.java.utils;

public interface Pile<T> {
	
	void empiler(T e);
	
	T sommet();
	
	T depiler();
	
	boolean isEmpty();
	
	int size();

}
