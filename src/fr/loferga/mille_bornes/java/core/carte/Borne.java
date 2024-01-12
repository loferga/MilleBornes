package fr.loferga.mille_bornes.java.core.carte;

import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class Borne extends Carte {
	
	private int km;

	public Borne(int nombre, int km) {
		super(nombre);
		this.km = km;
	}
	
	@Override
	public boolean estApplicable(Joueur j) {
		return !j.estBloque()
				&& km <= j.getLimite()
				&& (j.getKM() + km <= 1000);
	}
	
	@Override
	public void appliquer(Joueur j) {
		j.getBornes().add(this);
	}
	
	public int getKm() {
		return km;
	}
	
	public void setKm(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(km);
		str.append(" BORNES");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		if (!super.equals(other)) return false;
		
		Borne borne = (Borne) other;
		return this.km == borne.km;
	}
	
	@Override
	public int hashCode() {
		return (31 * this.getClass().hashCode() * km);
	}
	
}
