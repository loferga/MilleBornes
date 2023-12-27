package fr.loferga.core.jeu.joueur;

import java.util.Collections;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

import fr.loferga.core.carte.Borne;
import fr.loferga.core.carte.Carte;
import fr.loferga.core.jeu.Coup;

public class Presse extends Joueur {
	
	private static final Comparator<Coup> COMPARATOR =
			(coupBase, coupAutre) -> {
				Carte carteBase = coupBase.getCarte();
				Carte carteAutre = coupAutre.getCarte();
				boolean baseEstBorne = carteBase instanceof Borne;
				boolean autreEstBorne = carteAutre instanceof Borne;
				
				 // une Borne viens toujours avant les autres
				if (baseEstBorne && !autreEstBorne) return 1;
				
				 // les autres viennent toujours après une Borne
				if (!baseEstBorne && autreEstBorne) return -1;
				
				/* dans le cas d'une comparaison de borne à borne,
				 * l'ordre naturel tri déjà par km les bornes entre elles
				 */
				return coupBase.compareTo(coupAutre);
			};
			
	public Presse(String nom) {
		super(nom);
	}
	
	private static final String LABEL = "P";
	
	@Override
	protected String getLabel() {
		return LABEL;
	}
	
	@Override
	public Optional<Coup> selectionner() {
		// du plus grand au plus petit
		NavigableSet<Coup> coups = new TreeSet<>(Collections.reverseOrder(COMPARATOR));
		coups.addAll(super.coupsPossibles(super.jeu.getJoueurs()));
		return super.jouerPremierPossible(coups);
	}
	
	@Override
	public Coup rendreCarte() {
		NavigableSet<Coup> coups = new TreeSet<>(COMPARATOR);
		coups.addAll(super.coupsParDefault());
		return super.jouerPremier(coups);
	}

}
