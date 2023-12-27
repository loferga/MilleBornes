package fr.loferga.core.success;

public class UnregisteredSuccessException extends RuntimeException {
	
	private static final long serialVersionUID = 3248908310546679078L;
	
	public UnregisteredSuccessException(Success success) {
		super(success.toString());
	}

}
