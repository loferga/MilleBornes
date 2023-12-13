package fr.loferga.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventManager {
	
	// ###### Singleton
	private static EventManager instance = new EventManager();
	
	public static EventManager get() {
		return instance;
	}
	// ######
	
	private Map<Class<? extends Event>, Set<EventExecutor<?>>> eventHandlers;
	
	private EventManager() {
		eventHandlers = new HashMap<>();
	}
	
	public <E extends Event> void subscribe(Class<E> eventClass, EventExecutor<E> eventHandler) {
		System.err.println(eventHandler.toString() + " subscribed");
		Set<EventExecutor<?>> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new HashSet<>());
		handlers.add(eventHandler);
	}
	
	public <E extends Event> void trigger(E e) {
		Set<EventExecutor<?>> toTrigger = eventHandlers.get(e.getClass());
		if (toTrigger == null) return;
		
		for (EventExecutor<?> executor : toTrigger) {
			// the way Consumer<?> are subscribed doesn't need type check
			@SuppressWarnings("unchecked")
			EventExecutor<E> eventExecutor = (EventExecutor<E>) executor;
			eventExecutor.execute(e);
		}
	}
	
}
