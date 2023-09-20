package fr.loferga;

public abstract class Probleme extends Carte {
	
	public static enum Type {
		FEU, ESSENCE, CREVAISON, ACCIDENT;
	}
	
	private Type type;

	protected Probleme(int nombre, Type type) {
		super(nombre);
		this.type = type;
	}
}
