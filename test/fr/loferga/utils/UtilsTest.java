package fr.loferga.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.carte.Carte;
import fr.loferga.carte.JeuDeCartes;

class UtilsTest {
	
	private List<Integer> l1 = new LinkedList<>();
	private List<Integer> l2 = new LinkedList<>();
	private List<Integer> l3 = new LinkedList<>();
	private List<Integer> l4 = new LinkedList<>();
	
	@BeforeEach
	void setUpBeforeEach() throws Exception {
		Collections.addAll(l2, 1, 1, 2, 1, 3);
		Collections.addAll(l3, 1, 4, 3, 2);
		Collections.addAll(l4, 1, 1, 2, 3, 1);
	}
	
	@Test
	void test_melanger() {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> defCartes = jeu.getCartes();
		List<Carte> melangerCartes = new ArrayList<>();
		melangerCartes.addAll(defCartes);
		assertTrue(Utils.verifierMelange(defCartes, Utils.melanger(melangerCartes)));
		
		assertTrue(Utils.verifierMelange(l1, Utils.melanger(l1)));
		assertTrue(Utils.verifierMelange(l2, Utils.melanger(l2)));
		assertTrue(Utils.verifierMelange(l3, Utils.melanger(l3)));
		assertTrue(Utils.verifierMelange(l4, Utils.melanger(l4)));
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
