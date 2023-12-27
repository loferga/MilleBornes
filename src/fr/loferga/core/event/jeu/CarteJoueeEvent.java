package fr.loferga.core.event.jeu;

import fr.loferga.core.event.Event;
import fr.loferga.core.event.EventManager;
import fr.loferga.core.jeu.Coup;
import fr.loferga.core.jeu.joueur.Joueur;

public class CarteJoueeEvent extends Event {
	
	private Coup coupJoue;
	private Joueur joueur;

	public CarteJoueeEvent(Coup coupJoue, Joueur joueur) {
		super(EventManager.get());
		this.coupJoue = coupJoue;
		this.joueur = joueur;
		super.trigger();
	}
	
	public Coup getCoupJoue() {
		return coupJoue;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}

}
