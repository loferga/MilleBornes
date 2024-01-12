package fr.loferga.mille_bornes.java.core.jeu.joueur;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.DebutLimite;
import fr.loferga.mille_bornes.java.core.carte.FinLimite;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.core.jeu.Main;

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
			Carte carteBase = coupBase.getCarte();
			Carte carteAutre = coupAutre.getCarte();
			if (carteBase instanceof Botte botteBase && carteAutre instanceof Botte botteAutre) {
				if (coupBase.getCible().estBloque() && coupBase.getCible().sommetBataille().getType() == botteBase.getType()) {
					return 1;
				} else {
					return -1;
				}
			}
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
								"Le Milles Borne ne prend pas en charge des valeurs de borne autres que celle indiqu�s dans les r�gles"
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
			
	public Experimente(Bot botProfile) {
		super(botProfile);
	}
	
	private NavigableSet<Coup> trierCoups(Collection<Coup> coups, Comparator<Coup> comparator) {
		NavigableSet<Coup> coupsTries = new TreeSet<>(comparator);
		coupsTries.addAll(coups);
		return coupsTries;
	}
	
	@Override
	public NavigableSet<Coup> coupsValides(Set<Joueur> participants) {
		return trierCoups(super.coupsValides(participants), Collections.reverseOrder(COMPARATOR_JOUER));
	}
	
	@Override
	public NavigableSet<Coup> coupsDefausse() {
		return trierCoups(super.coupsDefausse(), Collections.reverseOrder(COMPARATOR_DEFAUSSE));
	}
	
}
