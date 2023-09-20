package fr.loferga.jeu;

import fr.loferga.Carte;

public class Sabot {
	
	private Carte[] cartes;
	private int nbCartes;

	public Sabot(int cartesMax) {
		this.cartes = new Carte[cartesMax];
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	private void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException();
		}
		cartes[nbCartes] = carte;
		nbCartes++;
	}
	
	public void ajouterFamilleCarte(Carte carte) {
		for (int i = 0; i<carte.getNombre(); i++) {
			ajouterCarte(carte);
		}
	}
	
	public void ajouterFamilleCarte(Carte... cartes) {
		for (int i = 0; i<cartes.length; i++) {
			ajouterFamilleCarte(cartes[i]);
		}
	}
	
}
