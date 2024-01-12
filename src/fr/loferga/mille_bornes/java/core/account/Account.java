package fr.loferga.mille_bornes.java.core.account;

public class Account extends UserProfile {
	
	private static final long serialVersionUID = 11L;

	private Account(String name) {
		super(name, false);
	}
	
	public static Account createNewAccount(String name) {
		Account newAccount = new Account(name);
		newAccount.createBlankSuccessLibrary();
		return newAccount;
	}
	
	public static Account accountFromGuest(Guest guest, String name) {
		Account newAccount = new Account(name);
		newAccount.success = guest.success;
		return newAccount;
	}
	
	// TODO implementer methode pour persistance des comptes

}
