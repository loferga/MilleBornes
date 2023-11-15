package fr.loferga.jeu;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Carte;
import fr.loferga.carte.DebutLimite;
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
		return (carte.getClass() != Attaque.class || j != null) // attque -> joueur valide
				&& (carte.getClass() != DebutLimite.class || j != null); // debutLimite -> joueur valide
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Coup other &&
				carte.equals(other.carte) &&
				(
						joueur == null && other.joueur == null
						||
						joueur != null && joueur.equals(other.joueur)
				);
	}
	
	@Override
	public int hashCode() {
		int hashJoueur = 0;
		if (joueur != null) {
			hashJoueur = joueur.hashCode();
		}
		return 31 * (carte.hashCode() + hashJoueur);
	}
	
}