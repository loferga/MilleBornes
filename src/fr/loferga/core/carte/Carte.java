package fr.loferga.core.carte;

import fr.loferga.core.jeu.Joueur;

public abstract class Carte {
	
	private int nombre;

	protected Carte(int nombre) {
		this.nombre = nombre;
	}
	
	public int getNombre() {
		return nombre;
	}
	
	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	public abstract boolean appliquer(Joueur j);
	
	@Override
	public boolean equals(Object other) {
		return other != null && other.getClass() == this.getClass();
	}
	
	@Override
	public int hashCode() {
		return 31 * this.getClass().hashCode();
	}
	
}
