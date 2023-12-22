package fr.loferga.core.success;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fr.loferga.core.carte.Botte;
import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.core.event.Event;
import fr.loferga.core.event.EventExecutor;
import fr.loferga.core.event.EventHandler;
import fr.loferga.core.event.EventManager;
import fr.loferga.core.event.jeu.CarteJoueeEvent;
import fr.loferga.core.jeu.Coup;
import fr.loferga.core.jeu.Joueur;
import fr.loferga.utils.LoggerSupplier;

public class SuccessRegister implements Iterable<Success> {
	
	private class RegisteredSuccessIterator implements Iterator<Success> {
		
		private Iterator<Success> it;
		
		public RegisteredSuccessIterator() {
			it = registeredSuccess.iterator();
		}

		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public Success next() {
			return it.next();
		}
		
	}
	
	private EventManager eventManager;
	private Set<Success> registeredSuccess;
	
	public SuccessRegister(EventManager eventManager) {
		this.eventManager = eventManager;
		this.registeredSuccess = new HashSet<>();
	}
	
	/**
	 * register all function in s that are marked with EventHandler annotation and fit
	 * the requirements
	 * @param success instance to get listeners from
	 */
	private void registerListeners(Success success) {
		for (Method method : success.getClass().getDeclaredMethods()) {
			// return null if EventHandler is not present
			EventHandler eventHandlerAnnotation = method.getDeclaredAnnotation(EventHandler.class);
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (eventHandlerAnnotation != null                         // has EventHandler Annotation
					&& method.getReturnType() == void.class            // return void
					&& parameterTypes.length == 1                      // takes 1 argument
					&& Event.class.isAssignableFrom(parameterTypes[0]) // this single argument is a subclass of Event
				) {
				// register this method as EventExecutor
				@SuppressWarnings("unchecked") // we already checked that the parameter is a subclass of Event
				Class<? extends Event> eventType = (Class<? extends Event>) parameterTypes[0];
				eventManager.subscribe(
						eventType,
						new EventExecutor<>(method, success),
						eventHandlerAnnotation.priority()
				);
			}
		}
	}
	
	public boolean register(Success success) {
		registerListeners(success);
		return registeredSuccess.add(success);
	}
	
	@Override
	public Iterator<Success> iterator() {
		return new RegisteredSuccessIterator();
	}
	
	public static void main(String[] args) {
		LoggerSupplier.debugMode();
		SuccessRegister successRegister = new SuccessRegister(EventManager.get());
		successRegister.register(new ExampleSuccess());
		successRegister.register(new ExampleSuccess2());
		new CarteJoueeEvent(new Coup(new Botte(1, Type.ACCIDENT), new Joueur("1")));
	}
	
}
