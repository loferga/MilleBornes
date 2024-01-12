package fr.loferga.mille_bornes.java.core.event.jeu;

import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.event.Event;
import fr.loferga.mille_bornes.java.core.event.EventManager;
import fr.loferga.mille_bornes.java.core.jeu.Game;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class CoupFourreEvent extends Event {
	
	private Game game;
	private Attaque triggerCard;
	private Joueur player;
	
	public CoupFourreEvent(Game game, Attaque triggerCard, Joueur player) {
		super(EventManager.get());
		this.game = game;
		this.triggerCard = triggerCard;
		this.player = player;
		super.trigger();
	}
	
	public Game getGame() {
		return game;
	}
	
	public Attaque getTriggerCard() {
		return triggerCard;
	}

	public Joueur getPlayer() {
		return player;
	}
	
}
