package fr.loferga.jeu;

import java.util.Collection;
import java.util.NoSuchElementException;

public class Ordonnanceur {
	
	private Joueur[] joueurs;
	private int courant;
	
	public Ordonnanceur(Joueur... joueurs) {
		this.joueurs = joueurs;
	}
	
	public Ordonnanceur(Collection<? extends Joueur> joueurs) {
		this.joueurs = new Joueur[joueurs.size()];
		int i = 0;
		for (Joueur joueur : joueurs) {
			this.joueurs[i++] = joueur;
		}
	}
	
	public Joueur courant() {
		return joueurs[courant];
	}
	
	public Joueur prochain() {
		courant = (courant + 1) % joueurs.length;
		return courant();
	}
	
	// @pre j est dans joueurs
	public void sauter(Joueur j) {
		int nbParcourus = 0;
		while (nbParcourus < joueurs.length && !prochain().equals(j)) {
			nbParcourus++;
		}
		if (nbParcourus >= joueurs.length) {
			throw new NoSuchElementException("le Joueur n'est pas contenue dans l'ordonnanceur");
		}
	}
	
}