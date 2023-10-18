package fr.loferga.carte;

public class Parade extends Bataille {

	public Parade(int nombre, Type type) {
		super(nombre, type);
		// TODO Auto-generated constructor stub
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
