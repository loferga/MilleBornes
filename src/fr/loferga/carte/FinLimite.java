package fr.loferga.carte;

import fr.loferga.carte.Probleme.Type;
import fr.loferga.jeu.Joueur;

public class FinLimite extends Limite {

	public FinLimite(int nombre) {
		super(nombre);
		// TODO Auto-generated constructor stub
	}
	
	private boolean derniereFinLimite(Joueur j) {
		if (j.getLimites().isEmpty()) return false;
		
		boolean ajoutee = false;
		Limite derniereLimite = j.getLimites().sommet();
		if (!j.possedeBotte(Type.FEU)
				&& derniereLimite.equals(new DebutLimite(1))) {
			j.getLimites().empiler(this);
			ajoutee = true;
		}
		return ajoutee;
	}
	
	@Override
	public boolean appliquer(Joueur j) {
		boolean ajoutee = false;
		if (!j.possedeBotte(Type.FEU) && !derniereFinLimite(j)) {
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
