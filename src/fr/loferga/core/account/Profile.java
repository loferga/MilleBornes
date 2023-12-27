package fr.loferga.core.account;

public abstract class Profile {
	
	protected String name;
	
	protected Profile(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}	
	
}