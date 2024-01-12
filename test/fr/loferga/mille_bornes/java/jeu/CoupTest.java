package fr.loferga.mille_bornes.java.jeu;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.DebutLimite;
import fr.loferga.mille_bornes.java.core.carte.FinLimite;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

class CoupTest {
	
	private static Joueur j1;
	private static Joueur j2;
	
	// carte cibl�es
	private static final Attaque ATTAQUE = new Attaque(1, Type.CREVAISON);
	private static final DebutLimite DEBUT_LIMITE = new DebutLimite(1);
	// carte personnelles
	private static final Parade PARADE = new Parade(1, Type.CREVAISON);
	private static final FinLimite FIN_LIMITE = new FinLimite(1);
	private static final Botte BOTTE = new Botte(1, Type.FEU);
	private static final Borne BORNE = new Borne(1, 100);
	

	@BeforeEach
	void setUp() throws Exception {
		j1 = new Joueur(new Bot("J1"));
		j2 = new Joueur(new Bot("J2"));
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
