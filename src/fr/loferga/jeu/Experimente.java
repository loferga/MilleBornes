package fr.loferga.jeu;

import java.util.Comparator;
import java.util.Optional;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Carte;

public class Experimente extends Joueur {
	
	private static final Comparator<Coup> COMPARATOR_JOUER =
			(coupBase, coupAutre) -> {
				Carte carteBase = coupBase.getCarte();
				Carte carteAutre = coupAutre.getCarte();
				// attend un coup-fourré avant de jouer une Botte
				if (carteBase instanceof Botte botte
						&& coupBase.getCible().sommetBataille() instanceof Attaque attaque
						&& attaque.getType() == botte.getType()) {
					return 1;
				}
				
				return 1;
			};
	
	private static final Comparator<Coup> COMPARATOR_DEFAUSSE =
			(coupBase, coupAutre) -> {
				return 1;
			};
			
			
	public Experimente(String nom) {
		super(nom);
	}
	
	@Override
	public Optional<Coup> selectionner() {
		Optional<Coup> coup = super.selectionner();
		return coup;
	}
	
	@Override
	public Coup rendreCarte() {
		Coup coup = super.rendreCarte();
		return coup;
	}
	
}
