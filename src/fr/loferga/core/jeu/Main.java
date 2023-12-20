package fr.loferga.core.jeu;

import fr.loferga.core.carte.Carte;

public interface Main extends Iterable<Carte> {

	void prendre(Carte carte);
	
	void jouer(Carte carte);
	
	int size();
	
}