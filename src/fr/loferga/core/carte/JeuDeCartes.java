package fr.loferga.core.carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.utils.Utils;

public class JeuDeCartes implements Iterable<Carte> {
	
	private static final Carte[] typesDeCartes = {
		new Attaque(3, Type.ACCIDENT),
		new Attaque(3, Type.CREVAISON),
		new Attaque(3, Type.ESSENCE),
		new Attaque(5, Type.FEU),
		
		new Borne(10, 25),
		new Borne(10, 50),
		new Borne(10, 75),
		new Borne(12, 100),
		new Borne(4, 200),
		
		new Botte(1, Type.ACCIDENT),
		new Botte(1, Type.CREVAISON),
		new Botte(1, Type.ESSENCE),
		new Botte(1, Type.FEU),
		
		new DebutLimite(4),
		new FinLimite(6),
		
		new Parade(6, Type.ACCIDENT),
		new Parade(6, Type.CREVAISON),
		new Parade(6, Type.ESSENCE),
		new Parade(6, Type.FEU)
	};
	
	private List<Carte> listeCartes = new ArrayList<>();
	
	public JeuDeCartes() {
		for (Carte carte : typesDeCartes) {
			for (int i = 0; i<carte.getNombre(); i++) {
				listeCartes.add(carte);
			}
		}
		listeCartes = Utils.melanger(listeCartes);
	}
	
	public List<Carte> getCartes() {
		return listeCartes;
	}
	
	public boolean checkCount() {
		for (Carte carte : typesDeCartes)
			if (Collections.frequency(listeCartes, carte) != carte.getNombre())
				return false;
		return true;
	}
	
	// renvoie le nombre d'occurence de carteCherchee
	private int getNumberOf(Carte carteCherchee) {
		int count = 0;
		for (Carte carte : listeCartes) {
			if (carte.equals(carteCherchee)) {
				count++;
			}
		}
		return count;
	}
	
	public boolean checkCountEfficace() {
		// les elements qui ont déjà été testés
		List<Carte> comptees = new LinkedList<>();
		for (Carte c : listeCartes) {
			// si il n'a pas encore été testé
			if (!comptees.contains(c)) {
				if (c.getNombre() == getNumberOf(c)) {
					comptees.add(c);
				} else {
					return false;
				}
			}
		}
		// si aucun test n'as abouti à un return false alors:
		return true;
	}

	@Override
	public Iterator<Carte> iterator() {
		return listeCartes.iterator();
	}
	
}