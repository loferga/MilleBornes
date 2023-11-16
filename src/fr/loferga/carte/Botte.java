package fr.loferga.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.jeu.Joueur;

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
