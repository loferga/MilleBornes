package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public class Attaque extends Bataille {

	public Attaque(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean appliquerBataille(Joueur j, Bataille sommet) {
		if (j.possedeBotte(super.getType()) || j.estBloque()) return false;
		
		j.getBatailles().empiler(sommet);
		return true;
	}
	
	@Override
	public String toString() {
		switch(super.getType()) {
		case FEU:
			return "FEU ROUGE";
		case ESSENCE:
			return "PANNE D'ESSENCE";
		case CREVAISON:
			return "CREVAISON";
		case ACCIDENT:
			return "ACCIDENT";
		default:
			return "Invalid";
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		
		Attaque atk = (Attaque) other;
		return super.equalsProbleme(atk);
	}
	
}
