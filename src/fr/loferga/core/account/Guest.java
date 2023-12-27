package fr.loferga.core.account;

public class Guest extends UserProfile {
	
	private static final long serialVersionUID = 12L;
	private static int guest_id = 0;

	public Guest() {
		super("guest_" + Integer.toString(guest_id));
		guest_id++;
	}
	
	public Guest(String name) {
		super(name);
	}

}
