package fr.loferga.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventManager {
	
	// ###### Singleton
	private static final EventManager instance = new EventManager();
	
	public static EventManager get() {
		return instance;
	}
	// ######
	
	private static class EventPriorityList<T> implements Iterable<T> {
		
		private class EventPriorityListIterator implements Iterator<T> {
			
			private Iterator<ArrayList<T>> lit;
			private Iterator<T> eit;
			
			public EventPriorityListIterator() {
				lit = eventExecutors.iterator();
			}

			@Override
			public boolean hasNext() {
				if (eit != null && eit.hasNext()) return true;
				while (eit == null || !eit.hasNext()) {
					if (!lit.hasNext()) return false;
					eit = lit.next().iterator();
				}
				return true;
			}

			@Override
			public T next() {
				assert hasNext();
				return eit.next();
			}
			
		}
		
		private ArrayList<ArrayList<T>> eventExecutors;
		
		public EventPriorityList() {
			EventPriority[] priorities = EventPriority.values();
			eventExecutors = new ArrayList<>(priorities.length);
			for (int i = 0; i < priorities.length; i++) {
				eventExecutors.add(new ArrayList<>());
			}
		}
		
		public void add(T executor, EventPriority priority) {
			eventExecutors.get(priority.getSlot()).add(executor);
		}

		@Override
		public Iterator<T> iterator() {
			return new EventPriorityListIterator();
		}
		
	}
	
	private Map<Class<? extends Event>, EventPriorityList<EventExecutor<?>>> eventHandlers;
	
	private EventManager() {
		eventHandlers = new HashMap<>();
	}
	
	public <E extends Event> void subscribe(Class<E> eventClass, EventExecutor<E> eventHandler, EventPriority priority) {
		System.err.println(eventHandler.toString() + " subscribed");
		EventPriorityList<EventExecutor<?>> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new EventPriorityList<>());
		handlers.add(eventHandler, priority);
	}
	
	public <E extends Event> void trigger(E e) {
		EventPriorityList<EventExecutor<?>> toTrigger = eventHandlers.get(e.getClass());
		if (toTrigger == null) return;
		
		for (EventExecutor<?> executor : toTrigger) {
			// the way Consumer<?> are subscribed doesn't need type check
			@SuppressWarnings("unchecked")
			EventExecutor<E> eventExecutor = (EventExecutor<E>) executor;
			eventExecutor.execute(e);
		}
	}
	
}
