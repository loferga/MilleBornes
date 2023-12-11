package fr.loferga.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventManager {
	
	// ###### Singleton
	private static EventManager instance = new EventManager();
	
	public static EventManager get() {
		return instance;
	}
	// ######
	
	private Map<Class<? extends Event>, ArrayList<Consumer<?>>> eventHandlers;
	
	private EventManager() {
		eventHandlers = new HashMap<>();	
	}
	
	public <E extends Event> void subscribe(Class<E> eventClass, Consumer<E> eventHandler) {
		System.err.println(eventHandler.toString() + " subscribed");
		ArrayList<Consumer<?>> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new ArrayList<>());
		handlers.add(eventHandler);
	}
	
	public <E extends Event> void trigger(E e) {
		ArrayList<Consumer<?>> toTrigger = eventHandlers.get(e.getClass());
		if (toTrigger == null) return;
		
		for (Consumer<?> executor : toTrigger) {
			// the way Consumer<?> are subscribed doesn't need type check
			@SuppressWarnings("unchecked")
			Consumer<E> eventExecutor = (Consumer<E>) executor;
			eventExecutor.accept(e);
		}
	}
	
}
