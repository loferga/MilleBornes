package fr.loferga.jeu;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

import fr.loferga.carte.Borne;
import fr.loferga.carte.Carte;

public class Presse extends Joueur {
	
	private static final Comparator<Coup> COMPARATOR =
			(coupBase, coupAutre) -> {
				Carte carteBase = coupBase.getCarte();
				Carte carteAutre = coupAutre.getCarte();
				boolean baseEstBorne = carteBase instanceof Borne;
				boolean autreEstBorne = carteAutre instanceof Borne;
				
				 // une Borne viens toujours avant les autres
				if (baseEstBorne && !autreEstBorne) return 1;
				
				 // les autres viennent toujours apr�s une Borne
				if (!baseEstBorne && autreEstBorne) return -1;
				
				/* dans le cas d'une comparaison de borne � borne,
				 * l'ordre naturel tri d�j� par km les bornes entre elles
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
		Iterator<Coup> it = coups.iterator();
		Coup coup = it.next();
		coup.jouer(this);
		return coup;
	}

}
