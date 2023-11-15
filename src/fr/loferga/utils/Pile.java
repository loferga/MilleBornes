package fr.loferga.utils;

// TODO envoyer
public interface Pile<T> {
	
	void empiler(T e);
	
	T sommet();
	
	T depiler();
	
	boolean isEmpty();
	
	int size();

}
