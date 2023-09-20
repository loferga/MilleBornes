package fr.loferga;

public class Borne extends Carte {
	private int km;

	public Borne(int nombre, int km) {
		super(nombre);
		this.km = km;
	}
	
	public int getKm() {
		return km;
	}
	
	public void setKm(int km) {
		this.km = km;
	}
	
}
