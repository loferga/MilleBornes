package fr.loferga.mille_bornes.java.core.jeu;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.JeuDeCartes;
import fr.loferga.mille_bornes.java.core.event.EventHandler;
import fr.loferga.mille_bornes.java.core.event.EventManager;
import fr.loferga.mille_bornes.java.core.event.Listener;
import fr.loferga.mille_bornes.java.core.event.jeu.CardDrawnEvent;
import fr.loferga.mille_bornes.java.core.event.jeu.CardPlayedEvent;
import fr.loferga.mille_bornes.java.core.event.jeu.CoupFourreEvent;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;

public class Jeu implements Game, Listener {
	
	private static final int NOMBRE_CARTE_PIOCHEE = 6;
	
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private JeuDeCartes jeuDeCartes = new JeuDeCartes();
	/* prise en charche d'un système de pile de défausse */
	private Sabot sabot = new Sabot();
	private Ordonnanceur ordonnanceur;
	
	public Jeu() {
		EventManager.get().subscribeAll(this);
	}
	
	public Set<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public Sabot getSabot() {
		return sabot;
	}
	
	@Override
	public boolean add(Joueur j) {
		if (joueurs.contains(j)) return false;
		j.setJeu(this);
		return joueurs.add(j);
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
	
	private NavigableSet<Joueur> getVainqueurs() {
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
		Joueur j = ordonnanceur.prochain();
		while (!finDePartie) {
			
			// pioche
			Carte cartePrise = sabot.piocher();
			new CardDrawnEvent(this, cartePrise, j);
			System.out.println("Le joueur "  + j + " prend la carte " + cartePrise);
			j.getMain().prendre(cartePrise);
			System.out.println("sa main: " + j.getMain());
			
			// sélection
			System.out.print("\t");
			Coup coupSelectionne = j.selectionner();
			
			// jeu
			new CardPlayedEvent(this, coupSelectionne, j);
			coupSelectionne.jouer(j);
			
			// procédure de fin de tour
			finDePartie = j.getKM() >= 1000 /* j est gagnant avec ce tour */
					|| sabot.isEmpty() && (!sabot.restituer() || sabot.isEmpty()); /* le sabot n'est pas vide */
			if (!finDePartie)
				j = ordonnanceur.prochain();
		}
		
		System.out.println();
		if (sabot.isEmpty()) {
			afficherClassement();
		} else {
			System.out.println("Le jeu est terminé. " + j + " a gagné!");
		}
	}
	
	@EventHandler
	public void onCoupFourre(CoupFourreEvent e) {
		System.out.println("COUP-FOURRE!");
		System.out.println("au tour de " + e.getPlayer().toString() + " de jouer");
		ordonnanceur.definirProchain(e.getPlayer());
	}
	
}