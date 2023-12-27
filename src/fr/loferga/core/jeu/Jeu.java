package fr.loferga.core.jeu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.core.account.Bot;
import fr.loferga.core.account.Profile;
import fr.loferga.core.account.UserProfile;
import fr.loferga.core.carte.Carte;
import fr.loferga.core.carte.JeuDeCartes;
import fr.loferga.core.jeu.joueur.Joueur;

public class Jeu implements Game {
	
	private static final int NOMBRE_CARTE_PIOCHEE = 6;
	
	private HashMap<Joueur, Profile> profiles = new HashMap<>();
	private JeuDeCartes jeuDeCartes = new JeuDeCartes();
	/* prise en charche d'un système de pile de défausse */
	private Sabot sabot = new Sabot();
	private Ordonnanceur ordonnanceur;
	
	public Set<Joueur> getJoueurs() {
		return profiles.keySet();
	}
	
	public Sabot getSabot() {
		return sabot;
	}
	
	@Override
	public boolean isLinked(Joueur j) {
		return profiles.containsKey(j);
	}
	
	private boolean put(Joueur j, Profile p) {
		if (isLinked(j)) return false;
		profiles.put(j, p);
		j.setJeu(this);
		return true;
	}
	
	@Override
	public boolean add(Joueur j) {
		return put(j, new Bot());
	}
	
	@Override
	public boolean link(Joueur j, UserProfile p) {
		return put(j, p);
	}
	
	@Override
	public Profile getProfile(Joueur j) {
		assert isLinked(j);
		return profiles.get(j);
	}
	
	public void distribuerCartes() {
		Iterator<Carte> iteratorCartes = jeuDeCartes.iterator();
		// chaque joueur pioche 6 cartes
		for (Joueur j : getJoueurs()) {
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
		res.addAll(getJoueurs());
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
	
	@Override
	public void start() {
		Set<Joueur> joueurs = getJoueurs();
		ordonnanceur = new Ordonnanceur(joueurs);
		
		distribuerCartes();
		for (Joueur j : joueurs)
			System.out.println(j + ":" + j.getMain());
		boolean finDePartie = sabot.isEmpty();
		Joueur j = ordonnanceur.courant();
		while (!finDePartie) {
			
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
			
			j = ordonnanceur.prochain();
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
	
}