package fr.loferga.jeu;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.core.carte.Carte;
import fr.loferga.core.jeu.Jeu;
import fr.loferga.core.jeu.Joueur;

class JeuTest {
	
	private Jeu jeu;
	private static Joueur j1 = new Joueur("Jean");
	private static Joueur j2 = new Joueur("Cristophe");
	private static Joueur j3 = new Joueur("Livre");
	
	@BeforeEach
	void beforeEach() throws Exception {
		jeu = new Jeu();
		jeu.inscrire(j1);
		jeu.inscrire(j2);
		jeu.inscrire(j3);
	}

	@Test
	void test_distribuer_cartes() {
		jeu.distribuerCartes();
		System.out.println("J1 : " + j1.getMain());
		System.out.println("J2 : " + j2.getMain());
		System.out.println("J3 : " + j3.getMain());
		// chaque joueur a 6 cartes
		assertEquals(6, j1.getMain().size());
		assertEquals(6, j2.getMain().size());
		assertEquals(6, j3.getMain().size());
		
		Joueur[] joueurs = {j1, j2, j3};
		List<Carte> cartesDonnees = new ArrayList<>();
		for (int i = 0; i < 3; i++)
			for (Carte c : joueurs[i].getMain())
				cartesDonnees.add(c);
		assertEquals(98, (jeu.getSabot().reste() + cartesDonnees.size()));
	}

}
