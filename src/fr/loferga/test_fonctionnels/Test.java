package fr.loferga.test_fonctionnels;

import java.util.Iterator;

import fr.loferga.Attaque;
import fr.loferga.Botte;
import fr.loferga.Carte;
import fr.loferga.Parade;
import fr.loferga.Probleme.Type;
import fr.loferga.jeu.Sabot;

public class Test {
	
	public static void main(String[] args) {
		Sabot sabot = new Sabot(110);
		sabot.ajouterFamilleCarte(
				new Attaque(3, Type.ACCIDENT),
				new Parade(3, Type.ACCIDENT),
				new Botte(1, Type.ACCIDENT)
				);
		Iterator<Carte> it = sabot.iterator();
		while (it.hasNext()) {
			System.out.println("je pioche " + sabot.piocher());			
		}
	}
	
}