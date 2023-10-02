package fr.loferga.test_fonctionnels;

import java.util.Iterator;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Carte;
import fr.loferga.carte.Parade;
import fr.loferga.carte.Probleme.Type;
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