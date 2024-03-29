package fr.loferga.mille_bornes.java.core.carte;

import java.util.EnumMap;
import java.util.Map;

import fr.loferga.mille_bornes.java.core.event.jeu.CoupFourreEvent;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

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
	public boolean batailleEstApplicable(Joueur j, Bataille sommet) {
		return !j.estBloque()
				&& !j.possedeBotte(super.getType());
	}
	
	@Override
	public void appliquer(Joueur j) {
		if (j.getMain().contains(new Botte(1, this.getType()))) {
			new CoupFourreEvent(j.getJeu(), this, j);
			return;
		}
		super.appliquer(j);
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
