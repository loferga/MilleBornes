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
		Carte carteRetiree = null;
		Bataille derniereBataille = j.getBatailles().sommet();
		// si la botte r�pond � une attaque, la supprimer:
		if (derniereBataille.equals(new Attaque(0, super.getType()))) {
			carteRetiree = j.getBatailles().depiler(); // retire l'attaque
		} else if (super.getType() == Type.FEU) {
			// la botette V�hicule Prioritaire peut aussi supprimer une limite de vitesse
			if (j.getLimites().isEmpty()) return;
			
			Limite derniereLimite = j.getLimites().sommet();
			if (derniereLimite instanceof DebutLimite) {
				carteRetiree = j.getLimites().depiler(); // retire la limite
			}
		}
		if (carteRetiree != null)
			j.getJeu().getSabot().defausser(carteRetiree);
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
