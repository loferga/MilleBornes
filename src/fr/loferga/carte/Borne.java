package fr.loferga.carte;

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
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(km);
		str.append(" BORNES");
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return
				other instanceof Borne brn &&
				this.km == brn.km;
	}
	
}
