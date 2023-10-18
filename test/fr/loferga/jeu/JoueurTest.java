package fr.loferga.jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.carte.Borne;

class JoueurTest {
	
	private Joueur joueur;
	
	@BeforeEach
	void beforeEach() {
		joueur = new Joueur("Joueur1");
	}

	@Test
	void test_get_km() {
		Collection<Borne> bornesJoueur = joueur.getBornes();
		Borne b1 = new Borne(1, 25);
		Borne b2 = new Borne(2, 100);
		assertEquals(0, joueur.getKM());
		bornesJoueur.add(b1);
		assertEquals(25, joueur.getKM());
		bornesJoueur.add(b2);
		assertEquals(125, joueur.getKM());
	}

}
