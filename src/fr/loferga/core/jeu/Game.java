package fr.loferga.core.jeu;

import fr.loferga.core.account.Profile;
import fr.loferga.core.account.UserProfile;
import fr.loferga.core.jeu.joueur.Joueur;

public interface Game {
	
	boolean isLinked(Joueur j);
	
	boolean add(Joueur j);
	
	boolean link(Joueur j, UserProfile userProfile);
	
	Profile getProfile(Joueur j);
	
	void start();
	
}
