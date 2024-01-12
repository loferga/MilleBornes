package fr.loferga.mille_bornes.java.core.account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.loferga.mille_bornes.java.Main;
import fr.loferga.mille_bornes.java.core.success.Success;
import fr.loferga.mille_bornes.java.core.success.SuccessRegister;
import fr.loferga.mille_bornes.java.core.success.UnregisteredSuccessException;

public abstract class UserProfile extends Profile implements Serializable {
	
	private static interface SuccessLibrary {
		
		<S extends Success> void put(S success);
		
		<S extends Success> S get(Class<S> successClass);
		
		boolean hasUnlocked(Class<? extends Success> successClass);
		
	}
	
	private static class SuccessLibraryAsMap implements SuccessLibrary {
		
		private Map<Class<? extends Success>, Success> map;
		
		public SuccessLibraryAsMap() {
			map = new HashMap<>();
		}

		@Override
		public <S extends Success> void put(S success) {
			map.put(success.getClass(), success);
		}

		@Override
		public <S extends Success> S get(Class<S> successClass) {
			if (!map.containsKey(successClass)) throw new UnregisteredSuccessException(successClass);
			@SuppressWarnings("unchecked")
			S result = (S) map.get(successClass);
			return result;
		}

		@Override
		public boolean hasUnlocked(Class<? extends Success> successClass) {
			if (!map.containsKey(successClass)) throw new UnregisteredSuccessException(successClass);
			return map.get(successClass).getProgress().isDone();
		}
		
	}
	
	private static final long serialVersionUID = 1L;
	
	protected SuccessLibrary success;

	protected UserProfile(String name, boolean uuidSuffix) {
		super(name, uuidSuffix);
		success = new SuccessLibraryAsMap();
	}
	
	protected final void createBlankSuccessLibrary() {
		for (Success s : SuccessRegister.get()) {
			putSuccess(s.clone());
		}
	}
	
	public <S extends Success> void putSuccess(S success) {
		Main.logger.fine(success.toString() + " put in success library for user " + super.name);
		this.success.put(success);
	}
	
	public <S extends Success> S getSuccess(Class<S> successClass) {
		return this.success.get(successClass);
	}
	
	public boolean hasUnlocked(Class<? extends Success> successClass) {
		return this.success.hasUnlocked(successClass);
	}

}
