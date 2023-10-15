package fr.loferga.carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.loferga.carte.Probleme.Type;
import fr.loferga.utils.Utils;

public class JeuDeCartes {
	
	private final Carte[] typesDeCartes = {
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
	
	public JeuDeCartes() {
		for (Carte carte : typesDeCartes) {
			for (int i = 0; i<carte.getNombre(); i++) {
				listeCartes.add(carte);
			}
		}
		listeCartes = Utils.melanger(listeCartes);
	}
	
	public List<Carte> getCartes() {
		return listeCartes;
	}
	
	public boolean checkCount() {
		for (Carte carte : typesDeCartes)
			if (Collections.frequency(listeCartes, carte) != carte.getNombre())
				return false;
		return true;
	}
	
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		System.out.println(jeu.getCartes());
	}
	
}