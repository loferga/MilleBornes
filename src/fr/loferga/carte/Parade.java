package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public class Parade extends Bataille {

	public Parade(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliquerBataille(Joueur j, Bataille sommet) {
		if (sommet.getClass() == Attaque.class
				&& sommet.getType() == super.getType()) {
			j.getBatailles().empiler(this);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		switch(super.getType()) {
		case FEU:
			return "FEU VERT";
		case ESSENCE:
			return "ESSENCE";
		case CREVAISON:
			return "ROUE DE SECOURS";
		case ACCIDENT:
			return "REPARATIONS";
		default:
			return "Invalid";
		}
	}

	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		
		Parade parade = (Parade) other;
		return super.equalsProbleme(parade);
	}
	
}
