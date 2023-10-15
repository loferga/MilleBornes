package fr.loferga.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Utils {
	
	private static Random rng = new Random();
	
	public static <T> T extraire(List<T> l) {
		int randomIndex = rng.nextInt(l.size());
		return l.remove(randomIndex);
	}
	
	public static <T> T extraireIterator(List<T> l) {
		int randomIndex = rng.nextInt(l.size());
		ListIterator<T> it = l.listIterator(randomIndex);
		T e = it.next();
		it.remove();
		return e;
	}
	
	public static <T> List<T> melanger(List<T> l) {
		List<T> melange = new LinkedList<>();
		while (!l.isEmpty())
			melange.add(extraire(l));
		return melange;
	}
	
	public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
		// foreach.x in l1, count(l1, x) == count(l2, x)
		for (T e : l1)
			if (Collections.frequency(l1, e) != Collections.frequency(l2, e))
				return false;
		return true;
	}
	
	public static <T> boolean verifierMelangeEfficace(List<T> l1, List<T> l2) {
		List<T> alreadyChecked = new LinkedList<>();
		for (T e : l1) {
			if (!alreadyChecked.contains(e)) {
				if (Collections.frequency(l1, e) != Collections.frequency(l2, e)) {
					return false;
				} else {
					alreadyChecked.add(e);
				}
			}
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> l) {
		return l.stream().sorted().toList();
	}
	
	private static <T> List<T> buildFromMap(Map<T, Integer> map) {
		List<T> res = new LinkedList<>();
		for (Entry<T, Integer> entry : map.entrySet())
			for (int i = 0; i<entry.getValue(); i++)
				res.add(entry.getKey());
		return res;
	}
	
	public static <T> List<T> rassemblerEfficace(List<T> l) {
		Map<T, Integer> occurences = new HashMap<>();
		for (T e : l) {
			if (occurences.containsKey(e)) {
				occurences.replace(e, occurences.get(e) + 1);
			} else {
				occurences.put(e, 1);
			}
		}
		return buildFromMap(occurences);
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
	
}