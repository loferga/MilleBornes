package fr.loferga.jeu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.loferga.core.carte.Attaque;
import fr.loferga.core.carte.Borne;
import fr.loferga.core.carte.Botte;
import fr.loferga.core.carte.DebutLimite;
import fr.loferga.core.carte.FinLimite;
import fr.loferga.core.carte.Parade;
import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.core.jeu.Joueur;

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
	
	@Test
	void test_get_limite() {
		// rien
		assertEquals(200, joueur.getLimite());
		
		// limite
		joueur.getLimites().empiler(new DebutLimite(1));
		assertEquals(50, joueur.getLimite());
		
		// limite contrée
		joueur.getLimites().empiler(new FinLimite(1));
		assertEquals(200, joueur.getLimite());
		joueur.getLimites().empiler(new DebutLimite(1));
		
		// limite mauvaise botte
		joueur.getBottes().add(new Botte(1, Type.ACCIDENT));
		assertEquals(50, joueur.getLimite());
		
		// limite bonne botte
		joueur.getBottes().add(new Botte(1, Type.FEU));
		assertEquals(200, joueur.getLimite());
		
		// attaque
		joueur.getBatailles().empiler(new Attaque(1, Type.ESSENCE));
		assertEquals(200, joueur.getLimite());
		
		// attaque contrée
		joueur.getBatailles().empiler(new Parade(1, Type.ESSENCE));
		assertEquals(200, joueur.getLimite());
		joueur.getBatailles().empiler(new Attaque(1, Type.ESSENCE));
		
		// attque mauvaise botte
		joueur.getBottes().add(new Botte(1, Type.CREVAISON));
		assertEquals(200, joueur.getLimite());
		
		// attaque bonne botte
		joueur.getBottes().add(new Botte(1, Type.ESSENCE));
		assertEquals(200, joueur.getLimite());
	}
	
	@Test
	void test_est_bloque() {
		// cas de base sans aucune contrainte
		assertTrue(joueur.estBloque());
		
		// un feu rouge
		joueur.getBatailles().empiler(new Attaque(1, Type.FEU));
		assertTrue(joueur.estBloque());
		
		// véhicule prioritaire
		joueur.getBottes().add(new Botte(1, Type.FEU));
		assertFalse(joueur.estBloque());
		
		// un accident
		joueur.getBatailles().empiler(new Attaque(1, Type.ACCIDENT));
		assertTrue(joueur.estBloque());
		
		// as du volant
		joueur.getBottes().add(new Botte(1, Type.ACCIDENT));
		assertFalse(joueur.estBloque());
		
		// une panne d'essence
		joueur.getBatailles().empiler(new Attaque(1, Type.ESSENCE));
		assertTrue(joueur.estBloque());
		
		// camion-citerne
		joueur.getBottes().add(new Botte(1, Type.ESSENCE));
		assertFalse(joueur.estBloque());
		
		// plus de bottes
		joueur.getBottes().clear();
		assertTrue(joueur.estBloque());
		
		/*
		 * un feu vert
		 * note: cette situation est impossible dans le jeu
		 * car l'opération suppression de toutes les bottes
		 * n'as pas d'équivoqe dans le déroulé d'une partie
		 * c'est pour cela qu'un feu vert fait repartir un joueur
		 * car il représente le fait que la dernière attaque
		 * viens d'être contrée avec une parade
		 */
		joueur.getBatailles().empiler(new Parade(1, Type.FEU));
		assertFalse(joueur.estBloque());
	}

}
