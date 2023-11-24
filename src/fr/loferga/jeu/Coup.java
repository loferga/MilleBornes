package fr.loferga.jeu;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Carte;
import fr.loferga.carte.DebutLimite;
import fr.loferga.utils.NotNull;

public class Coup {
	
	private Carte carte;
	private Joueur cible;
	
	public Coup(@NotNull Carte carte, Joueur cible) {
		if (carte == null) throw new IllegalArgumentException("carte ne peut pas être null");
		this.carte = carte;
		this.cible = cible;
	}
	
	public Carte getCarte() {
		return carte;
	}
	
	public Joueur getJoueur() {
		return cible;
	}
	
	public boolean estValide(Joueur joueur) {
		if (cible == null) return true;
		boolean equals = joueur.equals(cible); // le jouant et la cible sont les mêmes joueurs
		// carte est ciblée sur un adversaire
		boolean ciblee = carte instanceof Attaque || carte instanceof DebutLimite;
		// xor (equals, ciblee)
		if (ciblee) {
			return !equals;
		}
		return equals;
	}
	
	public boolean jouer(Joueur joueur) {
		boolean jouee = false;
		
		if (cible == null) {
			System.out.println("le joueur " + joueur + " repose la carte " + carte + " dans le sabot");
			joueur.getJeu().getSabot().defausser(carte);
			jouee = true;
		} else {
			jouee = carte.appliquer(cible);
			if (jouee) {
				System.out.println("le joueur " + joueur + " joue la carte " + carte + " sur " + cible);
			}
		}
		
		if (jouee) {
			joueur.getMain().jouer(carte);
		}
		
		return jouee;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup) {
			Coup other = (Coup) obj;
			return carte.equals(other.carte) &&
					(
							cible == null && other.cible == null // joueur = other.joueur même si null
							||
							cible != null && cible.equals(other.cible) // joueur = other.joueur si non null
					);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hashJoueur = 0;
		if (cible != null) {
			hashJoueur = cible.hashCode();
		}
		return 31 * (carte.hashCode() + hashJoueur);
	}
	
	public static void main(String[] args) {
		
	}
	
}