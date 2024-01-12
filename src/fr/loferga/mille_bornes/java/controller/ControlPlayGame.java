package fr.loferga.mille_bornes.java.controller;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.jeu.Game;
import fr.loferga.mille_bornes.java.core.jeu.Jeu;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Attaquant;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Experimente;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class ControlPlayGame {
	
	public void launchBotGame() {
		Game game = new Jeu();
		Joueur j1 = new Attaquant(new Bot("1"));
		Joueur j2 = new Experimente(new Bot("2"));
		Joueur j3 = new Experimente(new Bot("3"));
		game.add(j1);
		game.add(j2);
		game.add(j3);
		game.start();
	}
	
}
