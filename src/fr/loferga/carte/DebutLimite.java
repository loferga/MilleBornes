package fr.loferga.carte;

import fr.loferga.carte.Probleme.Type;
import fr.loferga.jeu.Joueur;

public class DebutLimite extends Limite {

	public DebutLimite(int nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	private boolean derniereDebutLimite(Joueur j) {
		if (j.getLimites().isEmpty()) return false;
		
		Limite derniereLimite = j.getLimites().sommet();
		return derniereLimite.getClass() == this.getClass();
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		boolean ajoutee = false;
		if (!j.possedeBotte(Type.FEU) && !derniereDebutLimite(j)) {
			j.getLimites().empiler(this);
			ajoutee = true;
		}
		return ajoutee;
	}
	
	@Override
	public String toString() {
		return "LIMITE DE VITESSE";
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
	
}
