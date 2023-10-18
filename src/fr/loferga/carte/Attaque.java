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
		if (!super.equals(other)) return false;
		
		Attaque atk = (Attaque) other;
		return super.equalsProbleme(atk);
	}
	
}
