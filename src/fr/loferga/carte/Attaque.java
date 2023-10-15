package fr.loferga.carte;

public class Attaque extends Bataille implements Cloneable {

	public Attaque(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
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
		return
				other instanceof Attaque atk &&
				super.equalsProbleme(atk);
	}
	
	@Override
	public Attaque clone() {
		return new Attaque(super.getNombre(), super.getType());
	}
	
}
