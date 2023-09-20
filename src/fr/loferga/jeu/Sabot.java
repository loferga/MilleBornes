package fr.loferga.jeu;

import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.loferga.Carte;
import fr.loferga.Parade;
import fr.loferga.Probleme.Type;

public class Sabot implements Iterable<Carte> {
	
	private Carte[] cartes;
	private int nbCartes;

	public Sabot(int cartesMax) {
		this.cartes = new Carte[cartesMax];
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	private void ajouterCarte(Carte carte) {
		if (nbCartes >= cartes.length) {
			throw new IllegalStateException();
		}
		cartes[nbCartes] = carte;
		nbCartes++;
	}
	
	public void ajouterFamilleCarte(Carte carte) {
		for (int i = 0; i<carte.getNombre(); i++) {
			ajouterCarte(carte);
		}
	}
	
	public void ajouterFamilleCarte(Carte... cartes) {
		for (int i = 0; i<cartes.length; i++) {
			ajouterFamilleCarte(cartes[i]);
		}
	}
	

	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterator<Carte>() {
			
			private int i = -1;

			@Override
			public boolean hasNext() {
				return i<nbCartes-1;
			}

			@Override
			public Carte next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				i++;
				return cartes[i];
			}
			
			@Override
			public void remove() {
				if (i<0 || i>nbCartes) {
					throw new UnsupportedOperationException();
				}
				for (int j = i; j<nbCartes-1; j++) {
					cartes[j] = cartes[j+1];
				}
				nbCartes--;
				i--;
			}
			
		};
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		Carte toReturn = it.next();
		it.remove();
		return toReturn;
	}
	
	// Test
	public void print() {
		for (int i = 0; i<nbCartes; i++)
			System.out.println(cartes[i]);
	}
	
	public static void main(String[] args) {
		Sabot sabot = new Sabot(110);
		sabot.ajouterFamilleCarte(new Parade(6, Type.ACCIDENT));
		System.out.println("-- BEFORE --");
		sabot.print();
		System.out.println("-- PIOCHER --");
		System.out.println(sabot.piocher());
		System.out.println("-- AFTER --");
		sabot.print();
	}
	
}
