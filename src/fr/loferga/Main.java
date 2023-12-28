package fr.loferga;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.loferga.core.account.Account;
import fr.loferga.core.event.EventManager;
import fr.loferga.core.jeu.Game;
import fr.loferga.core.jeu.Jeu;
import fr.loferga.core.jeu.joueur.Joueur;
import fr.loferga.core.success.AttaqueJoueeSuccess;
import fr.loferga.core.success.SuccessRegister;

public class Main {
	
	private static final String LOGGER_NAME = "mille-bornes";
	public static Logger logger = Logger.getLogger(LOGGER_NAME);
	
	private static void debugMode() {
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
		logger.setLevel(Level.ALL);
	}
	
	public static void main(String[] args) {
		debugMode();
		SuccessRegister successRegister = SuccessRegister.get();
		AttaqueJoueeSuccess exSuccess = new AttaqueJoueeSuccess();
		successRegister.register(exSuccess);
		EventManager.get().subscribeAll(exSuccess);
		
		Game game = new Jeu();
		game.add(new Joueur("2"));
		Account account = Account.createNewAccount("Jean");
		game.link(new Joueur(account.getName()), account);
		game.start();
	}
	
}