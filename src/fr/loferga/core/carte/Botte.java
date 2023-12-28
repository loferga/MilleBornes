package fr.loferga.core.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.core.jeu.joueur.Joueur;

public class Botte extends Probleme {
	
	private static final Map<Type, String> NAMES = new EnumMap<>(Type.class);
	
	static {
		NAMES.put(Type.FEU      , "VEHICULE PRIORITAIRE");
		NAMES.put(Type.ESSENCE  , "CITERNE D'ESSENCE"   );
		NAMES.put(Type.CREVAISON, "INCREVABLE"          );
		NAMES.put(Type.ACCIDENT , "AS DU VOLANT"        );
	}

	public Botte(int nombre, Type type) {
		super(nombre, type);
	}
	
	@Override
	public boolean estApplicable(Joueur j) {
		return !j.possedeBotte(this.getType());
	}
	
	@Override
	public void appliquer(Joueur j) {
		j.getBottes().add(this);
	}
	
	@Override
	public String toString() {
		return NAMES.get(super.getType());
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
