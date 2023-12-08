package fr.loferga.jeu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.carte.Carte;
import fr.loferga.carte.JeuDeCartes;

public class Jeu {
	
	private static final int NOMBRE_CARTE_PIOCHEE = 6;
	
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private JeuDeCartes jeuDeCartes = new JeuDeCartes();
	/* prise en charche d'un système de pile de défausse */
	private Sabot sabot = new Sabot();
	
	public Set<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public Sabot getSabot() {
		return sabot;
	}
	
	public boolean inscrire(Joueur j) {
		boolean ajoutee = joueurs.add(j);
		if (ajoutee) {
			j.setJeu(this);
		}
		return ajoutee;
	}
	
	public void distribuerCartes() {
		Iterator<Carte> iteratorCartes = jeuDeCartes.iterator();
		// chaque joueur pioche 6 cartes
		for (Joueur j : joueurs) {
			for (int i = 0; i < NOMBRE_CARTE_PIOCHEE; i++) {
				Carte c = iteratorCartes.next();
				j.getMain().prendre(c);
			}
		}
		iteratorCartes.forEachRemaining(c -> sabot.remettre(c)); // le reste vas au sabot
	}
	
	public NavigableSet<Joueur> getVainqueurs() {
		NavigableSet<Joueur> res = new TreeSet<>(Collections.reverseOrder(
				(j1, j2) -> {
					int comparaison = j1.getKM() - j2.getKM();
					if (comparaison == 0) {
						comparaison = Integer.compare(j1.hashCode(), j2.hashCode());
					}
					return comparaison;
				}
			));
		res.addAll(joueurs);
		return res;
	}
	
	private void afficherClassement() {
		System.out.println("Le sabot a été vidé voici le classement:");
		StringBuilder str = new StringBuilder();
		str.append('[');
		for (Iterator<Joueur> it = getVainqueurs().iterator(); it.hasNext();) {
			Joueur j = it.next(); 
			str.append(j.toString());
			str.append(" -> ");
			str.append(j.getKM());
			if (it.hasNext())
				str.append(", ");
		}
		str.append(']');
		System.out.println(str.toString());
	}
	
	public void lancer() {
		Iterator<Joueur> it = joueurs.iterator();
		if (!it.hasNext()) return;
		
		distribuerCartes();
		for (Joueur j : joueurs)
			System.out.println(j + ":" + j.getMain());
		boolean finDePartie = sabot.isEmpty();
		Joueur j = null;
		while (!finDePartie) {
			j = it.next();
			
			Carte cartePrise = sabot.piocher();
			System.out.println("Le joueur "  + j + " prend la carte " + cartePrise);
			j.getMain().prendre(cartePrise);
			System.out.println("sa main: " + j.getMain());
			
			System.out.print("\t");
			Optional<Coup> coupSelectionne = j.selectionner();
			if (coupSelectionne.isEmpty()) {
				j.rendreCarte();
			}
			
			finDePartie = j.getKM() >= 1000;
			
			if (!it.hasNext())
				it = joueurs.iterator();
			if (sabot.isEmpty() && (!sabot.restituer() || sabot.isEmpty())) {
				finDePartie = true;
			}
		}
		
		System.out.println();
		if (sabot.isEmpty()) {
			afficherClassement();
		} else {
			System.out.println("Le jeu est terminé. " + j + " a gagné!");
		}
	}
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		jeu.inscrire(new Experimente("1"));
		jeu.inscrire(new Joueur("2"));
		jeu.inscrire(new Joueur("3"));
		jeu.lancer();
	}
	
}