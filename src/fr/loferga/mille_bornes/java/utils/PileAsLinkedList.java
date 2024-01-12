package fr.loferga.mille_bornes.java.utils;

import java.util.LinkedList;
import java.util.List;

public class PileAsLinkedList<T> implements Pile<T> {
	
	private List<T> pile = new LinkedList<>();
	
	public void empiler(T e) {
		pile.add(0, e);
	}
	
	public T sommet() {
		return pile.get(0);
	}

	public T depiler() {
		T depiler = pile.get(0);
		pile.remove(0);
		return depiler;
	}
	
	public boolean isEmpty() {
		return pile.isEmpty();
	}
	
	public int size() {
		return pile.size();
	}
	
	@Override
	public String toString() {
		return pile.toString();
	}
	
}
