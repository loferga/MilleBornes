package fr.loferga.success;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import fr.loferga.carte.Botte;
import fr.loferga.carte.Probleme.Type;
import fr.loferga.event.Event;
import fr.loferga.event.EventExecutor;
import fr.loferga.event.EventHandler;
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
	
	private void registerListeners(Success s) {
		for (Method m : s.getClass().getDeclaredMethods()) {
			// return null if EventHandler is not present
			EventHandler eventHandlerAnnotation = m.getDeclaredAnnotation(EventHandler.class);
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (eventHandlerAnnotation != null                         // has EventHandler Annotation
					&& m.getReturnType() == void.class                 // return void
					&& parameterTypes.length == 1                      // takes 1 argument
					&& Event.class.isAssignableFrom(parameterTypes[0]) // this single argument is a subclass of Event
				) {
				// register this method as EventExecutor
				@SuppressWarnings("unchecked")
				Class<? extends Event> eventType = (Class<? extends Event>) parameterTypes[0];
				eventManager.subscribe(eventType, new EventExecutor<>(eventHandlerAnnotation, m, s));
			}
		}
	}
	
	public boolean register(Success s) {
		registerListeners(s);
		return registeredSuccess.add(s);
	}
	
	public static void main(String[] args) {
		SuccessRegister successRegister = new SuccessRegister(EventManager.get());
		successRegister.register(new ExampleSuccess());
		new CarteJoueeEvent(new Coup(new Botte(1, Type.ACCIDENT), new Joueur("1")));
	}
	
}
