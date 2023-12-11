package fr.loferga.event.core;

import fr.loferga.event.Event;
import fr.loferga.event.EventManager;
import fr.loferga.jeu.Coup;
import fr.loferga.jeu.Joueur;

public class CarteJoueeEvent extends Event {
	
	private Coup coupJoue;
	private Joueur joueur;

	public CarteJoueeEvent(Coup coupJoue) {
		super(EventManager.get());
		this.coupJoue = coupJoue;
		super.trigger();
	}
	
	public Coup getCoupJoue() {
		return coupJoue;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}

}
