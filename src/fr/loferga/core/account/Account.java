package fr.loferga.core.account;

public class Account extends UserProfile {
	
	private static final long serialVersionUID = 11L;

	private Account(String name) {
		super(name);
	}
	
	public static Account createNewAccount(String name) {
		Account newAccount = new Account(name);
		newAccount.createBlankSuccessMap();
		return newAccount;
	}
	
	// TODO implementer methode pour persistence des comptes

}
