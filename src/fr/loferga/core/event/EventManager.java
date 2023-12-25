package fr.loferga.core.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.loferga.Main;

public class EventManager {
	
	// ###### Singleton Pattern
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
	
	private Map<Class<? extends Event>, EventPriorityList<EventExecutor<? super Event>>> eventHandlers;
	
	private EventManager() {
		eventHandlers = new HashMap<>();
	}
	
	public <E extends Event> void subscribe(Class<E> eventClass, EventExecutor<? super Event> eventHandler, EventPriority priority) {
		Main.logger.fine(eventHandler.toString() + " subscribed");
		EventPriorityList<EventExecutor<? super Event>> handlers = eventHandlers.computeIfAbsent(eventClass, k -> new EventPriorityList<>());
		handlers.add(eventHandler, priority);
	}
	
	/**
	 * subscribe all function in listener that are marked with EventHandler annotation and fit
	 * the requirements to the appropriate Event
	 * @param success instance to get listeners from
	 */
	public void subscribeAll(Listener success) {
		Method[] methods = success.getClass().getDeclaredMethods();
		EventHandler eventHandlerAnnotation;
		Class<?>[] parameterTypes;
		for (Method method : methods) {
			// return null if EventHandler is not present
			eventHandlerAnnotation = method.getDeclaredAnnotation(EventHandler.class);
			parameterTypes = method.getParameterTypes();
			if (eventHandlerAnnotation != null                         // has EventHandler Annotation
					&& method.getReturnType() == void.class            // return void
					&& parameterTypes.length == 1                      // takes 1 argument
					&& Event.class.isAssignableFrom(parameterTypes[0]) // this single argument is a subclass of Event
				) {
				// register this method as EventExecutor
				Class<? extends Event> eventType = parameterTypes[0].asSubclass(Event.class);
				subscribe(
						eventType,
						new EventExecutor<>(method, success),
						eventHandlerAnnotation.priority()
				);
			}
		}
	}
	
	public <E extends Event> void trigger(E e) {
		EventPriorityList<EventExecutor<? super Event>> toTrigger = eventHandlers.get(e.getClass());
		if (toTrigger == null) return;
		
		for (EventExecutor<? super Event> executor : toTrigger) {
			// the way Consumer<?> are subscribed doesn't need type check
			EventExecutor<E> eventExecutor = EventExecutor.castEventExecutor(executor);
			eventExecutor.execute(e);
		}
	}
	
}
