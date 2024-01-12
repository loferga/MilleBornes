package fr.loferga.mille_bornes.java.test_fonctionnels;

import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.jeu.OldSabot;

public class TestSabot {
	
	public static void main(String[] args) {
		OldSabot sabot = new OldSabot(110);
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