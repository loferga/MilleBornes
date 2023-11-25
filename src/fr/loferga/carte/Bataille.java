package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public abstract class Bataille extends Probleme {

	protected Bataille(int nombre, Type type) {
		super(nombre, type);
	}
	
	protected abstract boolean appliquerBataille(Joueur j, Bataille sommet);
	
	@Override
	public boolean appliquer(Joueur j) {
		return this.appliquerBataille(j, j.sommetBataille());
	}

}
