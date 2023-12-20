package fr.loferga.core.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fr.loferga.utils.Utils;

public class JoueurChanceux extends Joueur {

	public JoueurChanceux(String nom) {
		super(nom);
	}
	
	private static final String LABEL = "JC";
	
	@Override
	protected String getLabel() {
		return LABEL;
	}
	
	@Override
	public Optional<Coup> selectionner() {
		Set<Coup> ensembleCoups = super.coupsPossibles(jeu.getJoueurs());
		List<Coup> coups = new ArrayList<>(ensembleCoups);
		coups = Utils.melanger(coups);
		return super.jouerPremierPossible(coups);
	}
	
	@Override
	public Coup rendreCarte() {
		Set<Coup> ensembleCoups = coupsParDefault();
		if (ensembleCoups.isEmpty()) return null;
		
		List<Coup> coups = new ArrayList<>(ensembleCoups);
		coups = Utils.melanger(coups);
		return super.jouerPremier(coups);
	}

}
