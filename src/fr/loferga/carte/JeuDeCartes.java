package fr.loferga.carte;

import java.util.ArrayList;
import java.util.List;

public class JeuDeCartes {
	
	private Carte[] typesDeCartes = new Carte[] {
		// TODO	
	};
	
	private List<Carte> listeCartes = new ArrayList<>();

	private void addAll(List<Carte> list, Carte carte) {
		for (int i = 0; i<carte.getNombre(); i++) {
			list.add(carte);
		}
	}
	
	JeuDeCartes() {
		for (Carte carte : typesDeCartes) {
			addAll(listeCartes, carte);
		}
	}
	
}