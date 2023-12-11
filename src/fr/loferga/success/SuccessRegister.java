package fr.loferga.success;

import java.util.HashSet;
import java.util.Set;

import fr.loferga.carte.Botte;
import fr.loferga.carte.Probleme.Type;
import fr.loferga.event.EventManager;
import fr.loferga.event.core.CarteJoueeEvent;
import fr.loferga.jeu.Coup;
import fr.loferga.jeu.Joueur;

public class SuccessRegister {
	
	private EventManager eventManager;
	private Set<Success> registeredSuccess;
	
	public SuccessRegister(EventManager eventManager) {
		this.eventManager = eventManager;
		this.registeredSuccess = new HashSet<>();
	}
	
	public boolean register(Success s) {
		s.registerListeners(eventManager);
		return registeredSuccess.add(s);
	}
	
	public static void main(String[] args) {
		SuccessRegister successRegister = new SuccessRegister(EventManager.get());
		successRegister.register(new ExampleSuccess());
		new CarteJoueeEvent(new Coup(new Botte(1, Type.ACCIDENT), new Joueur("1")));
	}
	
}
