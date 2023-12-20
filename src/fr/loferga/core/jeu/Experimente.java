package fr.loferga.core.jeu;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.core.carte.Attaque;
import fr.loferga.core.carte.Borne;
import fr.loferga.core.carte.Botte;
import fr.loferga.core.carte.Carte;
import fr.loferga.core.carte.DebutLimite;
import fr.loferga.core.carte.FinLimite;
import fr.loferga.core.carte.Parade;

public class Experimente extends Joueur {
	
	private static Map<Class<? extends Carte>, Integer> valeursCartes = new HashMap<>();
	
	static {
		valeursCartes.put(Botte.class, 6);
		valeursCartes.put(Parade.class, 5);
		valeursCartes.put(Borne.class, 4);
		valeursCartes.put(FinLimite.class, 3);
		valeursCartes.put(Attaque.class, 2);
		valeursCartes.put(DebutLimite.class, 1);
	}
	
	private static final Comparator<Coup> COMPARATOR_JOUER = new Comparator<>() {
		
		private boolean contientBorneLimitee(Main main) {
			for (Carte carte : main)
				if (carte instanceof Borne borne && borne.getKm() > 50)
						return true;
			return false;
		}
		
		private int valeurCoup(Coup coup) {
			Carte carte = coup.getCarte();
			
			// joue une carte fin limite seulement si nécéssaire
			if (carte instanceof FinLimite) {
				Joueur joueur = coup.getCible(); // joueur jouant la carte (dans ce contexte)
				if (contientBorneLimitee(joueur.getMain())) {
					return 7;
				} else {
					return -1;
				}
			}
			
			return valeursCartes.get(carte.getClass());
		}

		@Override
		public int compare(Coup coupBase, Coup coupAutre) {
			int comparaison = valeurCoup(coupBase) - valeurCoup(coupAutre);
			if (comparaison == 0) {
				comparaison = 1;
			}
			return comparaison;
		}
		
	};
	
	private static Map<Class<? extends Carte>, Integer> scoreDeffausse = new HashMap<>();
	
	static {
		// borne 25 : 10
		// borne 50 : 9
		scoreDeffausse.put(FinLimite.class, 8);
		// borne 75 : 7
		scoreDeffausse.put(Parade.class, 6);
		scoreDeffausse.put(DebutLimite.class, 5);
		scoreDeffausse.put(Attaque.class, 4);
		// borne 100 : 3
		// borne 200 : 2
		scoreDeffausse.put(Botte.class, 1);
	}
	
	private static final Comparator<Coup> COMPARATOR_DEFAUSSE = new Comparator<>() {
		
		private int valeurCoup(Coup coup) {
			Carte carte = coup.getCarte();
			
			if (carte instanceof Borne borne) {
				switch (borne.getKm()) {
					case 200:
						return 2;
					case 100:
						return 3;
					case 75:
						return 7;
					case 50:
						return 9;
					case 25:
						return 10;
					default:
						throw new IllegalStateException(
								"Le Milles Borne ne prend pas en charge des valeurs de borne autres que celle indiqués dans les règles"
							);
				}
			}
			
			return scoreDeffausse.get(carte.getClass());
		}

		@Override
		public int compare(Coup coupBase, Coup coupAutre) {
			int comparaison = valeurCoup(coupBase) - valeurCoup(coupAutre);
			if (comparaison == 0) {
				comparaison = 1;
			}
			return comparaison;
		}
		
	};
			
	public Experimente(String nom) {
		super(nom);
	}
	
	private static final String LABEL = "E";
	
	@Override
	protected String getLabel() {
		return LABEL;
	}
	
	@Override
	public Optional<Coup> selectionner() {
		NavigableSet<Coup> coups = new TreeSet<>(Collections.reverseOrder(COMPARATOR_JOUER));
		Set<Coup> coupsPossibles = super.coupsPossibles(super.jeu.getJoueurs());
		coups.addAll(coupsPossibles);
		System.out.println("coups possibles: " + coupsPossibles);
		System.out.println("\tcoups ordonnés:  " + coups);
		return super.jouerPremierPossible(coups);
	}
	
	@Override
	public Coup rendreCarte() {
		NavigableSet<Coup> coups = new TreeSet<>(Collections.reverseOrder(COMPARATOR_DEFAUSSE));
		Set<Coup> coupsPossibles = super.coupsParDefault();
		coups.addAll(coupsPossibles);
		System.out.println("\tdéffausses possibles: " + coupsPossibles);
		System.out.println("\tdéffausses ordonnés:  " + coups);
		return super.jouerPremier(coups);
	}
	
}
