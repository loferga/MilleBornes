package fr.loferga.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class Utils {
	
	private static Random rng = new Random();
	
	public static <T> T extraire(List<T> l) {
		int randomIndex = rng.nextInt(l.size());
		return l.remove(randomIndex);
	}
	
	public static <T> List<T> melanger(List<T> l) {
		List<T> melange = new LinkedList<>();
		while (!l.isEmpty())
			melange.add(extraire(l));
		return melange;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		List<T> alreadyChecked = new LinkedList<>();
		for (T t : l1) {
			if (!alreadyChecked.contains(t))
				if (Collections.frequency(l1, t) != Collections.frequency(l1, t)) {
					return false;
				} else alreadyChecked.add(t);
		}
		return true;
	}
	
	private static <T> List<T> buildFromMap(Map<T, Integer> map) {
		List<T> res = new LinkedList<>();
		for (Entry<T, Integer> entry : map.entrySet())
			for (int i = 0; i<entry.getValue(); i++)
				res.add(entry.getKey());
		return res;
	}
	
	public static <T> List<T> rassembler(List<T> l) {
		SortedMap<T, Integer> valueSlots = new TreeMap<>(); 
		for (T t : l) {
			if (valueSlots.containsKey(t)) {
				valueSlots.replace(t, valueSlots.get(t) + 1);
			} else {
				valueSlots.put(t, 1);
			}
		}
		return buildFromMap(valueSlots);
	}
	
	public static <T> boolean verifierRassemblement(List<T> l) {
		List<T> parcourus = new LinkedList<>();
		Iterator<T> it = l.iterator();
		if (!it.hasNext()) return true;
		
		T dernier = it.next();
		while (it.hasNext()) {
			T next = it.next();
			
			if (parcourus.contains(next)) return false;
			
			if (!next.equals(dernier)) {
				parcourus.add(dernier);
				dernier = next;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		List<Integer> l = new LinkedList<>();
		l.add(1);
		l.add(2);
		l.add(1);
		l.add(5);
		System.out.println(rassembler(l));
		System.out.println(verifierRassemblement(l));
		System.out.println(verifierRassemblement(rassembler(l)));
	}
	
}