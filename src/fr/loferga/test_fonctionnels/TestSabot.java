package fr.loferga.test_fonctionnels;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Parade;
import fr.loferga.carte.Probleme.Type;
import fr.loferga.jeu.Sabot;

public class TestSabot {
	
	public static void main(String[] args) {
		Sabot sabot = new Sabot(110);
		sabot.ajouterFamilleCarte(
				new Attaque(3, Type.ACCIDENT),
				new Parade(3, Type.ACCIDENT),
				new Botte(1, Type.ACCIDENT)
				);
		while (!sabot.estVide()) {
			System.out.println("Je pioche " + sabot.piocher()) ;
		}
		sabot.ajouterFamilleCarte(new Attaque(2, Type.ACCIDENT));
		System.out.println("carte: " + sabot.piocher());
	}
	
}