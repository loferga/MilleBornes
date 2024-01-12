package fr.loferga.mille_bornes.java.core.account;

public class Guest extends UserProfile {
	
	private static final long serialVersionUID = 12L;

	public Guest() {
		super("guest", true);
		super.createBlankSuccessLibrary();
	}
	
	public Guest(String name) {
		super(name, true);
		super.createBlankSuccessLibrary();
	}

}
