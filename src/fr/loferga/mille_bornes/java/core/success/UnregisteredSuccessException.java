package fr.loferga.mille_bornes.java.core.success;

public class UnregisteredSuccessException extends RuntimeException {
	
	private static final long serialVersionUID = 3248908310546679078L;
	
	public UnregisteredSuccessException(Class<? extends Success> success) {
		super(success.toString());
	}

}
