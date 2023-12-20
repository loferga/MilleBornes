package fr.loferga.core.carte;

import fr.loferga.core.jeu.Joueur;

public class FinLimite extends Limite {

	public FinLimite(int nombre) {
		super(nombre);
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		boolean ajoutee = false;
		if (super.derniereDebutLimite(j)) {
			j.getLimites().empiler(this);
			ajoutee = true;
		}
		return ajoutee;
	}
	
	@Override
	public String toString() {
		return "FIN DE LIMITE DE VITESSE";
	}
	
	@Override
	public boolean equals(Object other) {
		return super.equals(other);
	}
	
	@Override
	public int hashCode() {
		return (31 * this.getClass().hashCode());
	}
	
}
