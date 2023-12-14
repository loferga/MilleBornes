package fr.loferga.event;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class EventManager {
	
	private static final Comparator<EventExecutor<?>> COMPARATOR =
			(executorBase, executorOther) -> {
				// use order in which enums contant are declared
				int compare = executorBase.getPriority().compareTo(executorOther.getPriority());
				if (compare == 0) {
					compare = 1;
				}
				return compare;
			};
	
	// ###### Singleton
	private static final EventManager instance = new EventManager();
	
	public static EventManager get() {
		return instance;
	}
	// ######
	
	private Map<Class<? extends Event>, SortedSet<EventExecutor<?>>> eventHandlers;
	
	private EventManager() {
		eventHandlers = new HashMap<>();
	}
	
	public <E extends Event> void subscribe(Class<E> eventClass, EventExecutor<E> eventHandler) {
		System.err.println(eventHandler.toString() + " subscribed");
		SortedSet<EventExecutor<?>> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new TreeSet<>(COMPARATOR));
		handlers.add(eventHandler);
	}
	
	public <E extends Event> void trigger(E e) {
		SortedSet<EventExecutor<?>> toTrigger = eventHandlers.get(e.getClass());
		if (toTrigger == null) return;
		
		for (EventExecutor<?> executor : toTrigger) {
			// the way Consumer<?> are subscribed doesn't need type check
			@SuppressWarnings("unchecked")
			EventExecutor<E> eventExecutor = (EventExecutor<E>) executor;
			eventExecutor.execute(e);
		}
	}
	
}
