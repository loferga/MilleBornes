package fr.loferga.mille_bornes.java;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.loferga.mille_bornes.java.boundary.tui.BoundaryChangeProfile;
import fr.loferga.mille_bornes.java.boundary.tui.BoundaryGameHistory;
import fr.loferga.mille_bornes.java.boundary.tui.BoundaryPlayGame;
import fr.loferga.mille_bornes.java.boundary.tui.BoundaryProfileDetails;
import fr.loferga.mille_bornes.java.boundary.tui.MainMenu;
import fr.loferga.mille_bornes.java.controller.ControlPlayGame;
import fr.loferga.mille_bornes.java.controller.ControlProfileDetails;
import fr.loferga.mille_bornes.java.core.account.Guest;
import fr.loferga.mille_bornes.java.core.event.EventManager;
import fr.loferga.mille_bornes.java.core.success.AttaqueJoueeSuccess;
import fr.loferga.mille_bornes.java.core.success.JouerMilleBornesSuccess;
import fr.loferga.mille_bornes.java.core.success.QuatreBotteSuccess;
import fr.loferga.mille_bornes.java.core.success.SuccessRegister;

public class Main {
	
	private static final String LOGGER_NAME = "mille-bornes";
	public static Logger logger = Logger.getLogger(LOGGER_NAME);
	
	private static void loggerLevel(Level lvl) {
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(lvl);
		logger.addHandler(consoleHandler);
		logger.setLevel(lvl);
	}
	
	private static void initSuccess() {
		AttaqueJoueeSuccess attaqueJoueeSuccess = new AttaqueJoueeSuccess();
		SuccessRegister.get().register(attaqueJoueeSuccess);
		EventManager.get().subscribeAll(attaqueJoueeSuccess);
		QuatreBotteSuccess quatreBotteSuccess = new QuatreBotteSuccess();
		SuccessRegister.get().register(quatreBotteSuccess);
		EventManager.get().subscribeAll(quatreBotteSuccess);
		JouerMilleBornesSuccess jouerMilleBornesSuccess = new JouerMilleBornesSuccess();
		SuccessRegister.get().register(jouerMilleBornesSuccess);
		EventManager.get().subscribeAll(jouerMilleBornesSuccess);
	}
	
	private static void initAccounts() {
		initSuccess();
		// TODO persistence des comptes
	}
	
	public static void main(String[] args) {
		loggerLevel(Level.INFO);
		
		initAccounts();
		
		ControlPlayGame controlPlayGame = new ControlPlayGame();
		BoundaryPlayGame boundaryPlayGame = new BoundaryPlayGame(controlPlayGame);
		
		ControlProfileDetails controlProfileDetails = new ControlProfileDetails();
		BoundaryProfileDetails boundaryProfileDetails = new BoundaryProfileDetails(controlProfileDetails);
		
		BoundaryChangeProfile boundaryChangeProfile = new BoundaryChangeProfile();
		
		BoundaryGameHistory boundaryGameHistory = new BoundaryGameHistory();
		
		MainMenu menu = new MainMenu(
				boundaryPlayGame,
				boundaryProfileDetails,
				boundaryChangeProfile,
				boundaryGameHistory
			);
		
		Guest guest = new Guest();
		menu.menu(guest);
	}
	
}