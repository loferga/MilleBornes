package fr.loferga.mille_bornes.java.core.carte;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public abstract class Bataille extends Probleme {

	protected Bataille(int nombre, Type type) {
		super(nombre, type);
	}
	
	protected abstract boolean batailleEstApplicable(Joueur j, Bataille sommet);
	
	@Override
	public boolean estApplicable(Joueur j) {
		return this.batailleEstApplicable(j, j.sommetBataille());
	}
	
	@Override
	public void appliquer(Joueur j) {
		j.getBatailles().empiler(this);
	}

}
