package fr.loferga.carte;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.carte.Probleme.Type;
import fr.loferga.jeu.Joueur;

class AppliquerTest {
	
	private Joueur j;
	
	@BeforeEach
	void before_each() {
		j = new Joueur("Jean");
	}
	
	private static final Carte FEU_VERT = new Parade(1, Type.FEU);
	private static final Carte FEU_ROUGE = new Attaque(1, Type.FEU);
	private static final Carte BORNE_CENT = new Borne(1, 100);
	private static final Carte VEHICULE_PRIORITAIRE = new Botte(1, Type.FEU);
	private static final Carte LIMITE = new DebutLimite(1);
	private static final Carte PANNE = new Attaque(1, Type.ESSENCE);
	private static final Carte REPARATION = new Parade(1, Type.ACCIDENT);
	private static final Carte ESSENCE = new Parade(1, Type.ESSENCE);

	@Test
	void test_appliquer() {
		assertTrue(FEU_VERT.appliquer(j));
		assertFalse(FEU_VERT.appliquer(j));
		assertTrue(FEU_ROUGE.appliquer(j));
		assertFalse(BORNE_CENT.appliquer(j));
		assertTrue(VEHICULE_PRIORITAIRE.appliquer(j));
		assertTrue(BORNE_CENT.appliquer(j));
		assertTrue(BORNE_CENT.appliquer(j));
		assertFalse(LIMITE.appliquer(j));
		assertTrue(PANNE.appliquer(j));
		assertFalse(REPARATION.appliquer(j));
		assertTrue(ESSENCE.appliquer(j));
	}

}
