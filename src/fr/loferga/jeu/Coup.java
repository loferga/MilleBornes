package fr.loferga.jeu;

import java.util.HashMap;
import java.util.Map;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Borne;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Carte;
import fr.loferga.carte.DebutLimite;
import fr.loferga.carte.FinLimite;
import fr.loferga.carte.Parade;
import fr.loferga.utils.NotNull;

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
	
	@Override
	public int compareTo(Coup other) {
		Carte carteThis = this.carte;
		Carte carteOther = other.getCarte();
		int comparaison;
		if (carteThis instanceof Borne borneThis && carteOther instanceof Borne borneOther) {
			// cas comparaison borne à borne
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