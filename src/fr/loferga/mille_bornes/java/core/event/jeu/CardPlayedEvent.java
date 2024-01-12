package fr.loferga.mille_bornes.java.core.event.jeu;

import fr.loferga.mille_bornes.java.core.event.Event;
import fr.loferga.mille_bornes.java.core.event.EventManager;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.core.jeu.Game;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class CardPlayedEvent extends Event {
	
	private Game game;
	private Coup move;
	private Joueur player;

	public CardPlayedEvent(Game game, Coup move, Joueur player) {
		super(EventManager.get());
		this.game = game;
		this.move = move;
		this.player = player;
		super.trigger();
	}
	
	public Game getGame() {
		return game;
	}
	
	public Coup getMove() {
		return move;
	}
	
	public Joueur getPlayer() {
		return player;
	}

}
