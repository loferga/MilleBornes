package fr.loferga.jeu;

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
		
		if (sabot.isEmpty()) {
			System.out.println("Le sabot a été vidé voici le classement: " + getVainqueurs());
		} else {
			System.out.println("Le jeu est terminé. " + j + " a gagné!");
		}
	}
	
	public Set<Joueur> getVainqueurs() {
		NavigableSet<Joueur> res = new TreeSet<>(
				(j1, j2) -> (j2.getKM() - (j1.getKM() + 1))
			);
		res.addAll(joueurs);
		return res;
	}
	
	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		jeu.inscrire(new Joueur("1"));
		jeu.inscrire(new Joueur("2"));
		jeu.inscrire(new Joueur("3"));
		jeu.lancer();
	}
	
}