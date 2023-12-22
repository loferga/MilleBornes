package fr.loferga;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.loferga.core.event.EventManager;
import fr.loferga.core.success.ExampleSuccess;
import fr.loferga.core.success.ExampleSuccess2;
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
		SuccessRegister successRegister = new SuccessRegister(EventManager.get());
		successRegister.register(new ExampleSuccess());
		successRegister.register(new ExampleSuccess2());
	}
	
}