package fr.loferga.mille_bornes.java.core.jeu;

import java.util.Collection;
import java.util.NoSuchElementException;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class Ordonnanceur {
	
	private Joueur[] joueurs;
	private int courant = -1;
	
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
	
	public Joueur prochain() {
		courant = (courant + 1) % joueurs.length;
		return joueurs[courant];
	}
	
	/**
	 * définie le joueur qui sera donné au prochain appel de prochain
	 * @param joueur le joueur qui jouera au prochain tour
	 */
	public void definirProchain(Joueur joueur) {
		int nbParcourus = 0;
		while (nbParcourus < joueurs.length && joueurs[courant+1].equals(joueur)) {
			nbParcourus++;
			courant++;
		}
		if (nbParcourus >= joueurs.length) {
			throw new NoSuchElementException("le Joueur n'est pas contenue dans l'ordonnanceur");
		}
	}
	
}