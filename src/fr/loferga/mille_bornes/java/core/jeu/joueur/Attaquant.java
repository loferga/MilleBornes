package fr.loferga.mille_bornes.java.core.jeu.joueur;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.jeu.Coup;

public class Attaquant extends Joueur {

	public Attaquant(Bot botProfile) {
		super(botProfile);
	}
	
	@Override
	public NavigableSet<Coup> coupsValides(Set<Joueur> participants) {
		NavigableSet<Coup> coupsTries = new TreeSet<>(Collections.reverseOrder());
		coupsTries.addAll(super.coupsValides(participants));
		return coupsTries;
	}
	
	@Override
	public NavigableSet<Coup> coupsDefausse() {
		NavigableSet<Coup> coupsTries = new TreeSet<>();
		coupsTries.addAll(super.coupsDefausse());
		return coupsTries;
	}

}
