package fr.loferga.mille_bornes.java.boundary.tui;

import java.util.Scanner;

import fr.loferga.mille_bornes.java.core.account.UserProfile;

public class MainMenu {
	
	private static final String MENU_BANNER = "1 - Play a game\n"
			+ "2 - Profile Details\n"
			+ "3 - Change Profile\n"
			+ "4 - Game History\n"
			+ "5 - Quit the game\n"
			+ "--------------------------";

	private Scanner stdin = new Scanner(System.in);
	private BoundaryPlayGame boundaryPlayGame;
	private BoundaryProfileDetails boundaryProfileDetails;
	private BoundaryChangeProfile boundaryChangeProfile;
	private BoundaryGameHistory boundaryGameHistory;
	
	public MainMenu(BoundaryPlayGame boundaryPlayGame, BoundaryProfileDetails boundaryProfileDetails,
			BoundaryChangeProfile boundaryChangeProfile, BoundaryGameHistory boundaryGameHistory) {
		this.boundaryPlayGame = boundaryPlayGame;
		this.boundaryProfileDetails = boundaryProfileDetails;
		this.boundaryChangeProfile = boundaryChangeProfile;
		this.boundaryGameHistory = boundaryGameHistory;
	}
	
	public void menu(UserProfile profile) {
		System.out.println("Connected as " + profile.getName());
		int choixUtilisateur;
		do {
			choixUtilisateur = Clavier.entrerEntier(MENU_BANNER);
			if (choixUtilisateur > 0 && choixUtilisateur < 4) {
				switch (choixUtilisateur) {
				case 1:
					boundaryPlayGame.playGame(profile);
					break;
				case 2:
					boundaryProfileDetails.profileDetails(profile);
					break;
				case 3:
					boundaryChangeProfile.changeProfile(profile);
					break;
				case 4:
					boundaryGameHistory.gameHistory(profile);
					break;
				default:
					System.out.println(
							"Vous devez entrer un chiffre entre 1 et 4");
					break;
				}
			}
		} while (choixUtilisateur != 5);
		stdin.close();
		System.out.println("Program Terminated");
	}
	
}