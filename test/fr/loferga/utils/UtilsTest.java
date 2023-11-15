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
		// test sur un jeu de carte
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> cartesJeu = jeu.getCartes();
		List<Carte> cartesMelangees = new ArrayList<>();
		cartesMelangees.addAll(cartesJeu); // copie des Cartes dans cartesMelangees
		assertTrue(Utils.verifierMelange(cartesJeu, Utils.melanger(cartesMelangees)));
		// test sur des listes d'entiers construites
		assertTrue(Utils.verifierMelange(l1, Utils.melanger(l1)));
		
		List<Integer> l2c = new LinkedList<>(); l2c.addAll(l2); // copie de l2 dans l2c
		List<Integer> ml2 = Utils.melanger(l2); // ml2 accueil l2 mélangée
		assertTrue(Utils.verifierMelange(l2c, ml2));
		
		List<Integer> l3c = new LinkedList<>(); l3c.addAll(l3); // copie de l3 dans l3c
		List<Integer> ml3 = Utils.melanger(l3); // ml3 accueil l3 mélangée
		assertTrue(Utils.verifierMelange(l3c, ml3));
		
		List<Integer> l4c = new LinkedList<>(); l4c.addAll(l4); // copie de l4 dans l4c
		List<Integer> ml4 = Utils.melanger(l4); // ml4 accueil l4 mélangée
		assertTrue(Utils.verifierMelange(l4c, ml4));
	}
	
	@Test
	void test_rassembler() {
		// test l1
		assertEquals(l1, Utils.rassembler(l1));
		// test l2
		List<Integer> l2r = List.of(1, 1, 1, 2, 3);
		assertEquals(l2r, Utils.rassembler(l2));
		// test l3
		List<Integer> l3r = List.of(1, 2, 3, 4);
		assertEquals(l3r, Utils.rassembler(l3));
		// test l4
		List<Integer> l4r = List.of(1, 1, 1, 2, 3);
		assertEquals(l4r, Utils.rassembler(l4));
	}

}
