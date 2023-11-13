package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public class Botte extends Probleme {

	public Botte(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		return j.getBottes().add(this);
	}
	
	@Override
	public String toString() {
		switch(super.getType()) {
		case FEU:
			return "VEHICULE PRIORITAIRE";
		case ESSENCE:
			return "CITERNE D'ESSENCE";
		case CREVAISON:
			return "INCREVABLE";
		case ACCIDENT:
			return "AS DU VOLANT";
		default:
			return "Invalid";
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		Botte botte = (Botte) other;
		return super.equalsProbleme(botte);
	}

}
