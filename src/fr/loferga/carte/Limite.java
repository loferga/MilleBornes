package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public abstract class Limite extends Carte {

	protected Limite(int nombre) {
		super(nombre);
	}
	
	
	protected boolean derniereDebutLimite(Joueur j) {
		if (j.getLimites().isEmpty()) return false;
		
		Limite derniereLimite = j.getLimites().sommet();
		return derniereLimite instanceof DebutLimite;
	}
	
}