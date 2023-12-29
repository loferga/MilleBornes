package fr.loferga.core.success;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SuccessRegister implements Iterable<Success> {
	
	// ###### Singleton Pattern
	private static final SuccessRegister instance = new SuccessRegister();
	
	public static SuccessRegister get() {
		return instance;
	}
	// ######
	
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
		
		// NO remove operation so it's not supported (default)
		
	}
	
	private Set<Success> registeredSuccess;
	
	public SuccessRegister() {
		this.registeredSuccess = new HashSet<>();
	}
	
	public boolean register(Success success) {
		return registeredSuccess.add(success);
	}
	
	public int size() {
		return registeredSuccess.size();
	}
	
	@Override
	public Iterator<Success> iterator() {
		return new RegisteredSuccessIterator();
	}
	
}
