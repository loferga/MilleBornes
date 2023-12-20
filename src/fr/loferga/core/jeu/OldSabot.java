package fr.loferga.core.jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.loferga.core.carte.Carte;

public class OldSabot implements Iterable<Carte> {
	
	private Carte[] cartes;
	private int nbCartes;
	private int nbOperations = 0;

	public OldSabot(int cartesMax) {
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
		nbOperations++;
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
	
	/* ==================================================
	 *                     ITERATOR
	 * ==================================================
	 */
	
	public class IteratorSabot implements Iterator<Carte> {

		private int i = 0;
		private int nbOperationsRef = nbOperations;
		private boolean nextDone = false;

		@Override
		public boolean hasNext() {
			return i<nbCartes;
		}
		
		private void verifConcurrence() {
			if (nbOperationsRef != nbOperations)
				throw new ConcurrentModificationException();
		}

		@Override
		public Carte next() {
			verifConcurrence();
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Carte toReturn = cartes[i];
			i++;
			nextDone = true;
			return toReturn;
		}
		
		@Override
		public void remove() {
			verifConcurrence();
			if (nbCartes<1 || !nextDone) {
				throw new UnsupportedOperationException();
			}
			for (int j = i-1; j<nbCartes-1; j++) {
				cartes[j] = cartes[j+1];
			}
			nextDone = false;
			nbOperationsRef++; nbOperations++;
			nbCartes--;
			i--;
		}
		
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return new IteratorSabot();
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		Carte toReturn = it.next();
		it.remove();
		return toReturn;
	}
	
}
