package fr.loferga.jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.loferga.carte.Carte;

public class Jeu {
	
	private Set<Joueur> joueurs = new HashSet<>();
	private List<Carte> sabot = new ArrayList<>();
	
	public Set<Joueur> getJoueurs() {
		return joueurs;
	}
	
	public List<Carte> getSabot() {
		return sabot;
	}
	
	public boolean inscrire(Joueur j) {
		boolean ajoutee = joueurs.add(j);
		if (ajoutee) {
			j.setJeu(this);
		}
		return ajoutee;
	}
	
}