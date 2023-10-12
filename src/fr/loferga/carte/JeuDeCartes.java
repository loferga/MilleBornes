package fr.loferga.carte;

import java.util.ArrayList;
import java.util.List;

import fr.loferga.carte.Probleme.Type;
import fr.loferga.utils.Utils;

public class JeuDeCartes {
	
	private Carte[] typesDeCartes = new Carte[] {
		new Attaque(3, Type.ACCIDENT),
		new Attaque(3, Type.CREVAISON),
		new Attaque(3, Type.ESSENCE),
		new Attaque(5, Type.FEU),
		
		new Borne(10, 25),
		new Borne(10, 50),
		new Borne(10, 75),
		new Borne(12, 100),
		new Borne(4, 200),
		
		new Botte(1, Type.ACCIDENT),
		new Botte(1, Type.CREVAISON),
		new Botte(1, Type.ESSENCE),
		new Botte(1, Type.FEU),
		
		new DebutLimite(4),
		new FinLimite(6),
		
		new Parade(6, Type.ACCIDENT),
		new Parade(6, Type.CREVAISON),
		new Parade(6, Type.ESSENCE),
		new Parade(6, Type.FEU)
	};
	
	private List<Carte> listeCartes = new ArrayList<>();

	private void addAll(List<Carte> list, Carte carte) {
		for (int i = 0; i<carte.getNombre(); i++) {
			list.add(carte);
		}
	}
	
	public JeuDeCartes() {
		for (Carte carte : typesDeCartes) {
			addAll(listeCartes, carte);
		}
		List<Carte> newListeCartes = Utils.melanger(listeCartes);
		if (!Utils.verifierMelange(listeCartes, newListeCartes))
			throw new IllegalStateException("melange Cartes");
		else listeCartes = newListeCartes;
	}
	
	public boolean checkCount() {
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		listeCartes.forEach(c -> str.append(c.toString() + '\n'));
		return str.toString();
	}
	
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println(jeu);
	}
	
}