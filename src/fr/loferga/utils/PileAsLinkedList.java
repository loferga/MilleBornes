package fr.loferga.utils;

import java.util.LinkedList;

public class PileAsLinkedList<T> extends LinkedList<T> implements Pile<T> {
	
	private static final long serialVersionUID = 1L;

	public T depiler() {
		return super.get(0);
	}
	
	public void empiler(T e) {
		super.add(0, e);
	}
	
}
