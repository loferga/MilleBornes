package fr.loferga.mille_bornes.java.core.jeu.joueur;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.jeu.Coup;

public class Presse extends Joueur {
	
	private static final Comparator<Coup> COMPARATOR =
			(coupBase, coupAutre) -> {
				Carte carteBase = coupBase.getCarte();
				Carte carteAutre = coupAutre.getCarte();
				boolean baseEstBorne = carteBase instanceof Borne;
				boolean autreEstBorne = carteAutre instanceof Borne;
				
				 // une Borne viens toujours avant les autres
				if (baseEstBorne && !autreEstBorne) return 1;
				
				 // les autres viennent toujours apr�s une Borne
				if (!baseEstBorne && autreEstBorne) return -1;
				
				/* dans le cas d'une comparaison de borne � borne,
				 * l'ordre naturel tri d�j� par km les bornes entre elles
				 */
				return coupBase.compareTo(coupAutre);
			};
	
	public Presse(Bot botProfile) {
		super(botProfile);
	}
	
	private NavigableSet<Coup> trierCoups(Collection<Coup> coups, Comparator<Coup> comparator) {
		NavigableSet<Coup> coupsTries = new TreeSet<>(comparator);
		coupsTries.addAll(coups);
		return coupsTries;
	}
	
	@Override
	public NavigableSet<Coup> coupsValides(Set<Joueur> participants) {
		return trierCoups(super.coupsValides(participants), Collections.reverseOrder(COMPARATOR));
	}
	
	@Override
	public NavigableSet<Coup> coupsDefausse() {
		return trierCoups(super.coupsDefausse(), COMPARATOR);
	}

}
