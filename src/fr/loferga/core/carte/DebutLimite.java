package fr.loferga.core.carte;

import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.core.jeu.joueur.Joueur;

public class DebutLimite extends Limite {

	public DebutLimite(int nombre) {
		super(nombre);
	}
	
	@Override
	public boolean estApplicable(Joueur j) {
		return !j.estBloque()
				&& !j.possedeBotte(Type.FEU)
				&& !super.derniereDebutLimite(j);
	}
	
	@Override
	public String toString() {
		return "LIMITE DE VITESSE";
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
