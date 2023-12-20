package fr.loferga.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSupplier {
	
	private static ConsoleHandler consoleHandler = new ConsoleHandler();
	private static final String LOGGER_NAME = "mille-bornes";
	private static Logger logger = Logger.getLogger(LOGGER_NAME);
	
	public static Logger get() {
		return logger;
	}
	
	public static void setLevel(Level level) {
		consoleHandler.setLevel(level);
		logger.addHandler(consoleHandler);
		logger.setLevel(level);
	}
	
	public static void debugMode() {
		setLevel(Level.ALL);
	}
	
}
