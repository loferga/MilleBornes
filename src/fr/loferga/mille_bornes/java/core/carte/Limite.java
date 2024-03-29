package fr.loferga.mille_bornes.java.core.carte;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public abstract class Limite extends Carte {

	protected Limite(int nombre) {
		super(nombre);
	}
	
	@Override
	public void appliquer(Joueur j) {
		j.getLimites().empiler(this);
	}
	
	protected boolean derniereDebutLimite(Joueur j) {
		if (j.getLimites().isEmpty()) return false;
		
		Limite derniereLimite = j.getLimites().sommet();
		return derniereLimite instanceof DebutLimite;
	}
	
}