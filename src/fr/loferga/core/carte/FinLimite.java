package fr.loferga.core.carte;

import fr.loferga.core.jeu.joueur.Joueur;

public class FinLimite extends Limite {

	public FinLimite(int nombre) {
		super(nombre);
	}
	
	@Override
	public boolean estApplicable(Joueur j) {
		return super.derniereDebutLimite(j);
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
