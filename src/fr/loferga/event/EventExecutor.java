package fr.loferga.event;

@FunctionalInterface
public interface EventExecutor<E extends Event> {
	
	void execute(E e);

}
