package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public abstract class Bataille extends Probleme {

	protected Bataille(int nombre, Type type) {
		super(nombre, type);
	}
	
	protected abstract boolean appliquerBataille(Joueur j, Bataille sommet);
	
	@Override
	public boolean appliquer(Joueur j) {
		if (j.getBatailles().isEmpty()) {
			if (this.equals(new Parade(1, Type.FEU))) {
				j.getBatailles().empiler(this);
				return true;
			}
			return false;
		}
		
		return this.appliquerBataille(j, j.getBatailles().sommet());
	}

}
