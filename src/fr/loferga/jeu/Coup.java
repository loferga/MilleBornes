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
		if (carte.getClass() == Attaque.class || carte.getClass() == DebutLimite.class) {
			return !j.equals(joueur);
		}
		return true;
	}
	
	public boolean jouer(Joueur j) {
		boolean jouee = false;
		
		if (joueur == null) {
			System.out.println("le joueur repose la carte " + carte + " dans le sabot");
			j.getJeu().getSabot().add(carte);
			jouee = true;
		} else {
			System.out.println("le joueur joue la carte " + carte + " sur " + joueur);
			jouee = carte.appliquer(j);
		}
		
		if (jouee) {
			j.getMain().jouer(carte);
		}
		
		return jouee;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup) {
			Coup other = (Coup) obj;
			return carte.equals(other.carte) &&
					(
							joueur == null && other.joueur == null // joueur = other.joueur même si null
							||
							joueur != null && joueur.equals(other.joueur) // joueur = other.joueur si non null
					);
		}
		return false;
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