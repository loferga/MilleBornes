package fr.loferga.jeu;

import fr.loferga.carte.Carte;
import fr.loferga.utils.NotNull;

public class Coup {
	
	private Carte carte;
	private Joueur joueur;
	
	public Coup(@NotNull Carte carte, Joueur joueur) {
		if (carte == null) throw new IllegalArgumentException("carte ne peut pas être null");
		this.carte = carte;
		this.joueur = joueur;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueur() {
		return joueur;
	}
	
	public boolean estValide(Joueur j) {
		// j'ai pas compris ce que fait cette fonction
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Coup other &&
				carte.equals(other.carte) &&
				joueur.equals(other.joueur);
	}
	
	@Override
	public int hashCode() {
		return 31 * (carte.hashCode() + joueur.hashCode());
	}
	
}