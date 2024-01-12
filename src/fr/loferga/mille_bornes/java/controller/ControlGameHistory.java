package fr.loferga.mille_bornes.java.controller;

import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.ipc.OcamlInterface;

public class ControlGameHistory implements AutoCloseable {
	
	private OcamlInterface ocamlInterface;
	
	public ControlGameHistory(UserProfile profile) {
		ocamlInterface = new OcamlInterface(profile);
	}
	
	public float winRate() {
		return ocamlInterface.askAverageWin();
	}
	
	public int averageScoreWhenLosing() {
		return ocamlInterface.askAverageLoseScore();
	}
	
	@Override
	public void close() {
		ocamlInterface.kill();
	}
	
}
