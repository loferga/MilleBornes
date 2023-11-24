package fr.loferga.jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import fr.loferga.utils.Utils;

public class JoueurChanceux extends Joueur {
	
	private static Random rng = new Random();

	public JoueurChanceux(String nom) {
		super(nom);
	}
	
	@Override
	public Optional<Coup> selectionner() {
		Set<Coup> ensembleCoups = coupsPossibles(super.jeu.getJoueurs());
		if (ensembleCoups.isEmpty()) return Optional.empty();
		
		List<Coup> listeCoups = new ArrayList<>(ensembleCoups);
		int size = listeCoups.size();
		boolean[] testes = new boolean[size];
		Coup coup;
		int rand;
		
		do {
			rand = rng.nextInt(size);
			testes[rand] = true;
			if (Utils.bigAssertTrue(testes))
				return Optional.empty();
			coup = listeCoups.get(rand);
		} while (!coup.jouer(this));
		
		return Optional.of(coup);
	}
	
	@Override
	public Coup rendreCarte() {
		Set<Coup> ensembleCoups = coupsParDefault();
		if (ensembleCoups.isEmpty()) return null;
		
		List<Coup> listeCoups = new ArrayList<>(ensembleCoups);
		return listeCoups.get(rng.nextInt(listeCoups.size()));
	}

}
