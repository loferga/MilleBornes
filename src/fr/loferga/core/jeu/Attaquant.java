package fr.loferga.core.jeu;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

public class Attaquant extends Joueur {

	public Attaquant(String nom) {
		super(nom);
	}
	
	private static final String LABEL = "A";
	
	@Override
	protected String getLabel() {
		return LABEL;
	}
	
	@Override
	public Optional<Coup> selectionner() {
		// de la plus grande valeur à la plus petite
		NavigableSet<Coup> coups = new TreeSet<>(Collections.reverseOrder());
		coups.addAll(super.coupsPossibles(super.jeu.getJoueurs()));
		return super.jouerPremierPossible(coups);
	}
	
	@Override
	public Coup rendreCarte() {
		// de la plus petite valeur à la plus grande
		NavigableSet<Coup> coups = new TreeSet<>();
		coups.addAll(super.coupsParDefault());
		return super.jouerPremier(coups);
	}

}
