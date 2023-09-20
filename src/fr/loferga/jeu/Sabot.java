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
			
			private int i = 0;
			private boolean doneNext = false;

			@Override
			public boolean hasNext() {
				return i<nbCartes;
			}

			@Override
			public Carte next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				Carte toReturn = cartes[i];
				i++;
				doneNext = true;
				return toReturn;
			}
			
			@Override
			public void remove() {
				if (nbCartes<1 || !doneNext) {
					throw new UnsupportedOperationException();
				}
				for (int j = i-1; j<nbCartes-1; j++) {
					cartes[j] = cartes[j+1];
				}
				doneNext = false;
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
