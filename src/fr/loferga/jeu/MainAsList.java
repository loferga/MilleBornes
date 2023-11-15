package fr.loferga.jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.loferga.carte.Carte;

public class MainAsList implements Main {
	
	private List<Carte> cartes = new LinkedList<>();
	
	public MainAsList() {
	}

	@Override
	public void prendre(Carte carte) {
		cartes.add(carte);
	}

	@Override
	public void jouer(Carte carte) {
		assert cartes.contains(carte);
		cartes.remove(carte);
	}

	@Override
	public Iterator<Carte> iterator() {
		return cartes.iterator();
	}

}