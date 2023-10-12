package fr.loferga.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilsTest {
	
	private List<Integer> l1 = new LinkedList<>();
	private List<Integer> l2 = new LinkedList<>();
	private List<Integer> l3 = new LinkedList<>();
	private List<Integer> l4 = new LinkedList<>();

	@BeforeEach
	void setUpBeforeEach() throws Exception {
		l2.add(1); l2.add(1); l2.add(2); l2.add(1); l2.add(3);
		l3.add(1); l3.add(4); l3.add(3); l3.add(2);
		l4.add(1); l4.add(1); l4.add(2); l4.add(3); l4.add(1);
	}
	
	private <T> boolean melangeValide(List<T> l1, List<T> l2) {
		// foreach.x in l1, count(l1, x) == count(l2, x)
		for (T t : l1)
			if (Collections.frequency(l1, t) != Collections.frequency(l2, t))
				return false;
		return true;
	}

	@Test
	void test_melanger() {
		assertEquals(Utils.verifierMelange(l1, Utils.melanger(l1)), melangeValide(l1, Utils.melanger(l1)));
		assertEquals(Utils.verifierMelange(l2, Utils.melanger(l2)), melangeValide(l2, Utils.melanger(l2)));
		assertEquals(Utils.verifierMelange(l3, Utils.melanger(l3)), melangeValide(l3, Utils.melanger(l3)));
		assertEquals(Utils.verifierMelange(l4, Utils.melanger(l4)), melangeValide(l4, Utils.melanger(l4)));
	}
	
	@Test
	void test_rassembler() {
		assertEquals(l1, Utils.rassembler(l1));
		List<Integer> l2r = List.of(1, 1, 1, 2, 3);
		assertEquals(l2r, Utils.rassembler(l2));
		List<Integer> l3r = List.of(1, 2, 3, 4);
		assertEquals(l3r, Utils.rassembler(l3));
		List<Integer> l4r = List.of(1, 1, 1, 2, 3);
		assertEquals(l4r, Utils.rassembler(l4));
	}

}
