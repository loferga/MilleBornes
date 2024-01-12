package fr.loferga.mille_bornes.java.boundary.tui;

import fr.loferga.mille_bornes.java.controller.ControlPlayGame;
import fr.loferga.mille_bornes.java.core.account.UserProfile;

public class BoundaryPlayGame {
	
	private ControlPlayGame controlPlayGame;
	
	public BoundaryPlayGame(ControlPlayGame controlPlayGame) {
		this.controlPlayGame = controlPlayGame;
	}

	public void playGame(UserProfile profile) {
		controlPlayGame.launchBotGame();
	}
	
}
