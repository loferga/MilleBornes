package fr.loferga.mille_bornes.java.core.jeu;

import java.util.HashMap;
import java.util.Map;

import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.DebutLimite;
import fr.loferga.mille_bornes.java.core.carte.FinLimite;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;
import fr.loferga.mille_bornes.java.utils.NotNull;

public class Coup implements Comparable<Coup> {
	
	protected static Map<Class<? extends Carte>, Integer> valeursCartes = new HashMap<>();
	
	static {
		valeursCartes.put(Botte.class, 6);
		valeursCartes.put(Attaque.class, 5);
		valeursCartes.put(DebutLimite.class, 4);
		valeursCartes.put(FinLimite.class, 3);
		valeursCartes.put(Parade.class, 2);
		valeursCartes.put(Borne.class, 1);
	}
	
	private Carte carte;
	private Joueur cible;
	
	public Coup(@NotNull Carte carte, Joueur cible) {
		if (carte == null) throw new IllegalArgumentException("carte ne peut pas �tre null");
		this.carte = carte;
		this.cible = cible;
	}
	
	public Carte getCarte() {
		return carte;
	}
	
	public Joueur getCible() {
		return cible;
	}
	
	public boolean estValide(Joueur joueur) {
		if (cible == null) return true;
		boolean equals = joueur.equals(cible); // le jouant et la cible sont les mêmes joueurs
		// carte est ciblée sur un adversaire
		boolean ciblee = carte instanceof Attaque || carte instanceof DebutLimite;
		return Boolean.logicalXor(equals, ciblee)
				&& carte.estApplicable(cible);
	}
	
	public void jouer(Joueur joueur) {
		if (cible == null) {
			System.out.println("le joueur " + joueur + " repose la carte " + carte + " dans le sabot");
			joueur.getJeu().getSabot().defausser(carte);
		} else {
			System.out.println("le joueur " + joueur + " joue la carte " + carte + " sur " + cible);
			carte.appliquer(cible);
		}
		
		joueur.getMain().jouer(carte);
	}
	
	// fonction de test
	@Override
	public String toString() {
		String cibleString = "SABOT";
		if (cible != null) {
			cibleString = cible.toString();
		}
		return carte.toString() + '/' + cibleString;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup) {
			Coup other = (Coup) obj;
			return carte.equals(other.carte) &&
					(
							cible == null && other.cible == null // joueur = other.joueur m�me si null
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
	
	@Override
	public int compareTo(Coup other) {
		Carte carteThis = this.carte;
		Carte carteOther = other.getCarte();
		int comparaison;
		if (carteThis instanceof Borne borneThis && carteOther instanceof Borne borneOther) {
			// cas comparaison borne � borne
			comparaison = borneThis.getKm() - borneOther.getKm();
		} else {
			int valeurThis = valeursCartes.get(carteThis.getClass());
			int valeurOther = valeursCartes.get(carteOther.getClass());
			comparaison = valeurThis - valeurOther;
		}
		
		if (comparaison == 0) {
			comparaison = 1; // ne doit pas supprimer de coup
		}
		
		return comparaison;
	}
	
}