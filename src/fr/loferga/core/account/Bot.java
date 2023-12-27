package fr.loferga.core.account;

public class Bot extends Profile {
	
	private static int bot_id = 0;

	public Bot() {
		super("bot_" + Integer.toString(bot_id));
		bot_id++;
	}
	
	public Bot(String name) {
		super(name);
	}

}
