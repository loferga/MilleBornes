package fr.loferga.mille_bornes.java.boundary.tui;

import fr.loferga.mille_bornes.java.controller.ControlProfileDetails;
import fr.loferga.mille_bornes.java.core.account.Guest;
import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.success.Success;

public class BoundaryProfileDetails {
	
	private static final String ANSI_YELLOW = "\u001B[0;33m";
	private static final String ANSI_RESET = "\u001B[0m";
	
	private ControlProfileDetails controlProfileDetails;
	
	public BoundaryProfileDetails(ControlProfileDetails controlProfileDetails) {
		this.controlProfileDetails = controlProfileDetails;
	}
	
	private String successDetails(Success success) {
		StringBuilder successString = new StringBuilder();
		boolean colorIsChanged = false;
		if (success.isUnlocked()) {
			successString.append(ANSI_YELLOW);
			colorIsChanged = true;
		}
		successString.append(success.toString());
		successString.append("                   ");	
		successString.append(success.getProgress().toString());
		successString.append('\n');
		successString.append('\t');
		successString.append(success.getDescription());
		if (colorIsChanged) {
			successString.append(ANSI_RESET);
		}
		return successString.toString();
	}
	
	public void profileDetails(UserProfile profile) {
		Success[] profileSuccess = controlProfileDetails.getProfileSuccess(profile);
		StringBuilder output = new StringBuilder();
		if (profile instanceof Guest) {
			output.append("Sign In if you want to keep your progression!\n");
		}
		for (Success success : profileSuccess) {
			output.append('\n');
			output.append(successDetails(success));
			output.append('\n');
		}
		System.out.println(output.toString());
	}
	
}
