package fr.loferga.core.account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import fr.loferga.core.success.Success;
import fr.loferga.core.success.SuccessRegister;
import fr.loferga.core.success.UnregisteredSuccessException;

public abstract class UserProfile extends Profile implements Serializable {
	
	private static interface SuccessLibrary {
		
		<S extends Success> void put(S state);
		
		<S extends Success> S get(S success);
		
		boolean hasUnlocked(Success success);
		
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
		public <S extends Success> S get(S success) {
			if (!map.containsKey(success.getClass())) throw new UnregisteredSuccessException(success);
			@SuppressWarnings("unchecked")
			S result = (S) map.get(success.getClass());
			return result;
		}

		@Override
		public boolean hasUnlocked(Success success) {
			if (!map.containsKey(success.getClass())) throw new UnregisteredSuccessException(success);
			return map.get(success.getClass()).getProgress().isDone();
		}
		
	}
	
	private static final long serialVersionUID = 1L;
	
	protected SuccessLibrary success;

	protected UserProfile(String name) {
		super(name);
		success = new SuccessLibraryAsMap();
	}
	
	protected final void createBlankSuccessMap() {
		for (Success s : SuccessRegister.get()) {
			this.success.put(s.clone());
		}
	}
	
	public <S extends Success> void putSuccess(S success) {
		this.success.put(success);
	}
	
	public <S extends Success> S getSuccess(S success) {
		return this.success.get(success);
	}
	
	public boolean hasUnlocked(Success success) {
		return this.success.hasUnlocked(success);
	}

}
