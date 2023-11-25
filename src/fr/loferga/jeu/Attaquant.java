package fr.loferga.jeu;

import java.util.Collections;
import java.util.Iterator;
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
		// du plus grand au plus petit
		NavigableSet<Coup> coups = new TreeSet<>(Collections.reverseOrder());
		coups.addAll(super.coupsPossibles(super.jeu.getJoueurs()));
		return super.jouerPremierPossible(coups);
	}
	
	@Override
	public Coup rendreCarte() {
		NavigableSet<Coup> coups = new TreeSet<>();
		coups.addAll(super.coupsParDefault());
		Iterator<Coup> it = coups.iterator();
		Coup coup = it.next();
		coup.jouer(this);
		return coup;
	}

}
