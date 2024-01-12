package fr.loferga.mille_bornes.java.carte;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.DebutLimite;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.jeu.Jeu;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

class AppliquerTest {
	
	private Joueur j;
	
	@BeforeEach
	void before_each() {
		j = new Joueur(new Bot("Jean"));
		Jeu jeu = new Jeu();
		jeu.add(j);
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
	void test_est_applicable() {
		assertTrue(FEU_VERT.estApplicable(j));
		FEU_VERT.appliquer(j);
		assertFalse(FEU_VERT.estApplicable(j));
		
		assertTrue(FEU_ROUGE.estApplicable(j));
		FEU_ROUGE.appliquer(j);
		assertFalse(BORNE_CENT.estApplicable(j));
		
		assertTrue(VEHICULE_PRIORITAIRE.estApplicable(j));
		VEHICULE_PRIORITAIRE.appliquer(j);
		assertTrue(BORNE_CENT.estApplicable(j));
		BORNE_CENT.appliquer(j);
		assertTrue(BORNE_CENT.estApplicable(j));
		BORNE_CENT.appliquer(j);
		assertFalse(LIMITE.estApplicable(j));
		
		assertTrue(PANNE.estApplicable(j));
		PANNE.appliquer(j);
		assertFalse(REPARATION.estApplicable(j));
		
		assertTrue(ESSENCE.estApplicable(j));
		ESSENCE.appliquer(j);
	}

}
