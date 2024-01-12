package fr.loferga.mille_bornes.java.utils;

import java.util.Collections;
import java.util.HashMap;
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
	
	public static <T> T extraireIterator(List<T> list) {
		int randomIndex = rng.nextInt(list.size());
		ListIterator<T> it = list.listIterator(randomIndex);
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
		if (l1.size() != l2.size()) return false;
		
		for (T e : l1)
			if (Collections.frequency(l1, e) != Collections.frequency(l2, e))
				return false;
		return true;
	}
	
	public static <T> boolean verifierMelangeEfficace(List<T> l1, List<T> l2) {
		if (l1.size() != l2.size()) return false;
		
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
		if (l.isEmpty()) return true;
		
		T dernier = l.get(0);
		ListIterator<T> it = l.listIterator(1);
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
	
	public static <E, T extends Iterable<E>> boolean contains(T iterable, E elementCherche) {
		for (E element : iterable)
			if (element.equals(elementCherche))
				return true;
		return false;
	}
	
}