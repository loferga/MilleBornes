package fr.loferga.mille_bornes.java.core.jeu;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public interface Game {
	
	boolean add(Joueur j);
	
	void start();
	
}
