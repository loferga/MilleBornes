package fr.loferga.jeu;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.loferga.carte.Attaque;
import fr.loferga.carte.Bataille;
import fr.loferga.carte.Borne;
import fr.loferga.carte.Botte;
import fr.loferga.carte.Carte;
import fr.loferga.carte.DebutLimite;
import fr.loferga.carte.Limite;
import fr.loferga.carte.Probleme.Type;
import fr.loferga.utils.Pile;
import fr.loferga.utils.PileAsLinkedList;
import fr.loferga.utils.Utils;

public class Joueur {
	
	private String nom;
	private Main main = new MainAsList();
	
	// cartes sur plateau
	private Pile<Limite> limites = new PileAsLinkedList<>();
	private Pile<Bataille> batailles = new PileAsLinkedList<>();
	private Collection<Borne> bornes = new LinkedList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
		// post-condition
		assert Utils.contains(main, carte);
	}
	
	public Carte prendreCarte(List<Carte> sabot) {
		if (sabot.isEmpty()) return null;
		Carte res = sabot.get(0);
		return res;
	}
	
	public int getKM() {
		int totalKM = 0;
		for (Borne borne : bornes) {
			totalKM += borne.getKm();
		}
		return totalKM;
	}
	
	private boolean possedeBotte(Type t) {
		for (Botte botte : bottes)
			if (botte.getType() == t)
				return true;
		return false;
	}
	
	private boolean limiteParAttaque() {
		Bataille derniereBataille = batailles.depiler();
		if (derniereBataille.getClass() == Attaque.class)
			if (!possedeBotte(derniereBataille.getType()))
				return true;
		return false;
	}
	
	private boolean limiteParLimite() {
		Limite derniereLimite = limites.depiler();
		if (derniereLimite.getClass() == DebutLimite.class)
			if (!possedeBotte(Type.FEU))
				return true;
		return false;
	}
	
	public int getLimite() {
			
		return 1;
	}
	
	public Main getMain() {
		return main;
	}

	public List<Limite> getLimites() {
		return limites;
	}

	public List<Bataille> getBatailles() {
		return batailles;
	}

	public Collection<Borne> getBornes() {
		return bornes;
	}

	public Set<Botte> getBottes() {
		return bottes;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Joueur autreJoueur &&
				nom.equals(autreJoueur.nom);
	}
	
}
