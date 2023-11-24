package fr.loferga.jeu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Borne;
import fr.loferga.carte.Botte;
import fr.loferga.carte.DebutLimite;
import fr.loferga.carte.FinLimite;
import fr.loferga.carte.Parade;
import fr.loferga.carte.Probleme.Type;

class CoupTest {
	
	private static Joueur j1;
	private static Joueur j2;
	
	// carte ciblées
	private static final Attaque ATTAQUE = new Attaque(1, Type.CREVAISON);
	private static final DebutLimite DEBUT_LIMITE = new DebutLimite(1);
	// carte personnelles
	private static final Parade PARADE = new Parade(1, Type.CREVAISON);
	private static final FinLimite FIN_LIMITE = new FinLimite(1);
	private static final Botte BOTTE = new Botte(1, Type.FEU);
	private static final Borne BORNE = new Borne(1, 100);
	

	@BeforeEach
	void setUp() throws Exception {
		j1 = new Joueur("J1");
		j2 = new Joueur("J2");
	}

	@Test
	void test_est_valide() {
		// attaque sur perso
		assertFalse(new Coup(ATTAQUE, j1).estValide(j1));
		assertFalse(new Coup(DEBUT_LIMITE, j1).estValide(j1));
		// attaque valide
		assertTrue(new Coup(ATTAQUE, j2).estValide(j1));
		assertTrue(new Coup(DEBUT_LIMITE, j2).estValide(j1));
		// defausse attaque
		assertTrue(new Coup(ATTAQUE, null).estValide(j1));
		assertTrue(new Coup(DEBUT_LIMITE, null).estValide(j1));
		
		// perso sur cible
		assertFalse(new Coup(PARADE, j2).estValide(j1));
		assertFalse(new Coup(FIN_LIMITE, j2).estValide(j1));
		assertFalse(new Coup(BOTTE, j2).estValide(j1));
		assertFalse(new Coup(BORNE, j2).estValide(j1));
		// perso valide
		assertTrue(new Coup(PARADE, j1).estValide(j1));
		assertTrue(new Coup(FIN_LIMITE, j1).estValide(j1));
		assertTrue(new Coup(BOTTE, j1).estValide(j1));
		assertTrue(new Coup(BORNE, j1).estValide(j1));
		// defausse perso
		assertTrue(new Coup(PARADE, null).estValide(j1));
		assertTrue(new Coup(FIN_LIMITE, null).estValide(j1));
		assertTrue(new Coup(BOTTE, null).estValide(j1));
		assertTrue(new Coup(BORNE, null).estValide(j1));
	}

}
