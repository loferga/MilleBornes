package fr.loferga.mille_bornes.java.boundary.tui;

import fr.loferga.mille_bornes.java.controller.ControlGameHistory;
import fr.loferga.mille_bornes.java.core.account.UserProfile;

public class BoundaryGameHistory {
	
	public void gameHistory(UserProfile profile) {
		try (ControlGameHistory controlGameHistory = new ControlGameHistory(profile)) {
			
		}
	}
	
}
