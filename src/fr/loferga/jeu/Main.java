package fr.loferga.jeu;

import fr.loferga.carte.Carte;

public interface Main extends Iterable<Carte> {

	void prendre(Carte carte);
	
	void jouer(Carte carte);
	
}