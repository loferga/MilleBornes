package fr.loferga.carte;

import fr.loferga.jeu.Joueur;

public class Botte extends Probleme {

	public Botte(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		if (!j.getBatailles().isEmpty()) {
			Bataille derniereBataille = j.getBatailles().sommet();
			// si la botte répond à une attaque, la supprimer:
			if (derniereBataille.equals(new Attaque(1, super.getType()))) {
				j.getBatailles().depiler(); // retire l'attaque
			}
		}
		
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
	
	@Override
	public int hashCode() {
		return (31 * super.getType().hashCode() * super.hashCode());
	}

}
