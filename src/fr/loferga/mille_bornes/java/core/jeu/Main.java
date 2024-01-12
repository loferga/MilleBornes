package fr.loferga.mille_bornes.java.core.jeu;

import fr.loferga.mille_bornes.java.core.carte.Carte;

public interface Main extends Iterable<Carte> {

	void prendre(Carte carte);
	
	void jouer(Carte carte);
	
	int size();
	
	boolean contains(Carte carte);
	
}