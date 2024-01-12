package fr.loferga.mille_bornes.java.core.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class Botte extends Probleme {
	
	private static final String ANSI_COLOR = "\u001B[0;35m";
	private static final String ANSI_RESET = "\u001B[0m";
	
	private static final Map<Type, String> NAMES = new EnumMap<>(Type.class);
	
	private static final String colorString(String string) {
		return ANSI_COLOR + string + ANSI_RESET;
	}
	
	static {
		NAMES.put(Type.FEU      , colorString("VEHICULE PRIORITAIRE"));
		NAMES.put(Type.ESSENCE  , colorString("CITERNE D'ESSENCE")   );
		NAMES.put(Type.CREVAISON, colorString("INCREVABLE")          );
		NAMES.put(Type.ACCIDENT , colorString("AS DU VOLANT")        );
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
