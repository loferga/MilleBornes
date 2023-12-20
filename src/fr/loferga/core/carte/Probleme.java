package fr.loferga.core.carte;

public abstract class Probleme extends Carte {
	
	public enum Type {
		FEU, ESSENCE, CREVAISON, ACCIDENT;
	}
	
	private Type type;

	protected Probleme(int nombre, Type type) {
		super(nombre);
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	protected boolean equalsProbleme(Probleme pb) {
		return this.type == pb.type;
	}
	
}
