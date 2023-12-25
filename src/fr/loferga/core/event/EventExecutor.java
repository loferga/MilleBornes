package fr.loferga.core.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventExecutor<E extends Event> {
	
	private Method method;
	private Object instance;
	
	public EventExecutor(Method method, Object instance) {
		this.method = method;
		this.instance = instance;
	}
	
	public void execute(E e) {
		try {
			method.invoke(instance, e);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	public static <E extends Event> EventExecutor<E> castEventExecutor(EventExecutor<? super Event> from) {
		return new EventExecutor<E>(from.method, from.instance) {

			@Override
			public void execute(E e) {
				from.execute(e);
			}
			
		};
	}
	
	@Override
	public String toString() {
		return method.toString();
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
