package fr.loferga.core.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.core.jeu.joueur.Joueur;

public class Attaque extends Bataille {
	
	public static final Attaque FEU_ROUGE = new Attaque(5, Type.FEU);
	
	private static final Map<Type, String> NAMES = new EnumMap<>(Type.class);
	
	static {
		NAMES.put(Type.FEU      , "FEU ROUGE"      );
		NAMES.put(Type.ESSENCE  , "PANNE D'ESSENCE");
		NAMES.put(Type.CREVAISON, "CREVAISON"      );
		NAMES.put(Type.ACCIDENT , "ACCIDENT"       );
	}

	public Attaque(int nombre, Type type) {
		super(nombre, type);
	}

	@Override
	public boolean appliquerBataille(Joueur j, Bataille sommet) {
		if (j.estBloque() || j.possedeBotte(super.getType())) return false;
		
		j.getBatailles().empiler(this);
		return true;
	}
	
	@Override
	public String toString() {
		return NAMES.get(super.getType());
	}
	
	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		
		Attaque atk = (Attaque) other;
		return super.equalsProbleme(atk);
	}
	
	@Override
	public int hashCode() {
		return (31 * this.getClass().hashCode() * super.getType().hashCode());
	}
	
}
