package fr.loferga.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventExecutor<E extends Event> {
	
	private EventHandler annotation;
	
	private Method method;
	private Object instance;
	
	public EventExecutor(EventHandler annotation, Method method, Object instance) {
		this.annotation = annotation;
		this.method = method;
		this.instance = instance;
	}
	
	public EventPriority getPriority() {
		return annotation.priority();
	}
	
	public void execute(E e) {
		try {
			method.invoke(instance, e);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof EventExecutor<?> executor
				&& this.method.equals(executor.method);
	}
	
	@Override
	public int hashCode() {
		return 31 * (this.getClass().hashCode() + method.hashCode());
	}

}
