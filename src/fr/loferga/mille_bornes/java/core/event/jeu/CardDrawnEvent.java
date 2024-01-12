package fr.loferga.mille_bornes.java.core.event.jeu;

import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.event.Event;
import fr.loferga.mille_bornes.java.core.event.EventManager;
import fr.loferga.mille_bornes.java.core.jeu.Game;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class CardDrawnEvent extends Event {
	
	private Game game;
	private Carte drawnCard;
	private Joueur picker;
	
	public CardDrawnEvent(Game game, Carte drawnCard, Joueur picker) {
		super(EventManager.get());
		this.game = game;
		this.drawnCard = drawnCard;
		this.picker = picker;
		super.trigger();
	}
	
	public Game getGame() {
		return game;
	}

	public Carte getDrawnCard() {
		return drawnCard;
	}

	public Joueur getPicker() {
		return picker;
	}
	
}
