package fr.loferga.core.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.core.jeu.joueur.Joueur;

public class Parade extends Bataille {
	
	public static final Parade FEU_VERT = new Parade(6, Type.FEU);
	
	private static final Map<Type, String> NAMES = new EnumMap<>(Type.class);
	
	static {
		NAMES.put(Type.FEU      , "FEU VERT"       );
		NAMES.put(Type.ESSENCE  , "ESSENCE"        );
		NAMES.put(Type.CREVAISON, "ROUE DE SECOURS");
		NAMES.put(Type.ACCIDENT , "REPARATIONS"    );
	}

	public Parade(int nombre, Type type) {
		super(nombre, type);
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
		return NAMES.get(super.getType());
	}

	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		
		Parade parade = (Parade) other;
		return super.equalsProbleme(parade);
	}
	
	@Override
	public int hashCode() {
		return (31 * this.getClass().hashCode());
	}
	
}
