package fr.loferga.core.event.jeu;

import fr.loferga.core.event.Event;
import fr.loferga.core.event.EventManager;
import fr.loferga.core.jeu.Coup;
import fr.loferga.core.jeu.Joueur;

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
