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
	
	private void coupFourre(Joueur j) {
		// vérifie si coup fourré d'une attaque
		if (!j.getBatailles().isEmpty()) {
			Bataille derniereBataille = j.getBatailles().sommet();
			// si la botte répond à une attaque, la supprimer:
			if (derniereBataille.equals(new Attaque(0, super.getType()))) {
				// retire l'attaque
				j.getBatailles().depiler();
				j.getJeu().getSabot().defausser(derniereBataille);
			}
		}
		
		// la botte Véhicule Prioritaire peut aussi supprimer une limite de vitesse
		if (super.getType() == Type.FEU && !j.getLimites().isEmpty()) {
			Limite derniereLimite = j.getLimites().sommet();
			if (derniereLimite instanceof DebutLimite) {
				// retire la limite
				j.getLimites().depiler();
				j.getJeu().getSabot().defausser(derniereLimite);
			}
		}
		
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		boolean ajoutee = j.getBottes().add(this);
		if (ajoutee && !j.getBatailles().isEmpty()) {
			coupFourre(j);
		}
		
		return ajoutee;
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
