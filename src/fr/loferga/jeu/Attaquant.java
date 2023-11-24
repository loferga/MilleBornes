package fr.loferga.jeu;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Borne;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Carte;
import fr.loferga.carte.DebutLimite;
import fr.loferga.carte.FinLimite;
import fr.loferga.carte.Parade;

public class Attaquant extends Joueur {
	
	private static Map<Class<? extends Carte>, Integer> valeursCarte = new HashMap<>();
	
	static {
		valeursCarte.put(Attaque.class, 6);
		valeursCarte.put(DebutLimite.class, 5);
		valeursCarte.put(FinLimite.class, 4);
		valeursCarte.put(Parade.class, 3);
		valeursCarte.put(Borne.class, 2);
		valeursCarte.put(Botte.class, 1);
	}
	
	private static final Comparator<Coup> COMPARATOR = (c1, c2) -> {
		return 1;
	};

	public Attaquant(String nom) {
		super(nom);
	}
	
	

}
