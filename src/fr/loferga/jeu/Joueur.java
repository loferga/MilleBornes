package fr.loferga.jeu;

import java.util.ArrayList;
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
import fr.loferga.carte.FinLimite;
import fr.loferga.carte.Limite;
import fr.loferga.carte.Parade;
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
		return sabot.get(0);
	}
	
	public int getKM() {
		int totalKM = 0;
		for (Borne borne : bornes) {
			totalKM += borne.getKm();
		}
		return totalKM;
	}
	
	public boolean possedeBotte(Type t) {
		for (Botte botte : bottes) {
			if (botte.getType() == t) {
				return true;
			}
		}
		return false;
	}
	
	public int getLimite() {
		if (possedeBotte(Type.FEU) // joueur prioritaire
				|| limites.isEmpty() // pas de limite
				|| limites.sommet().getClass() == FinLimite.class // dernière limite est une fin de limite
				)
			return 200;
		return 50;
	}
	
	public boolean estBloque() {
		if (batailles.isEmpty()) return false;
		
		Bataille derniereBataille = batailles.sommet();
		if (derniereBataille.getClass() == Attaque.class // derniereBataille est une Attaque
				&& !possedeBotte(derniereBataille.getType())) // le joueur n'est pas immunisé contre cette Attaque
			return true;
		return false;
	}
	
	public Set<Coup> coupsPossibles(List<Joueur> participants) {
		Set<Coup> resultat = new HashSet<>();
		for (Joueur cible : participants) {
			for (Carte carte : getMain()) {
				Coup coup = new Coup(carte, cible);
				if (coup.estValide(this)) {
					resultat.add(coup);
				}
			}
		}
		return resultat;
	}
	
	public Set<Coup> coupsParDefault() {
		List<Joueur> ensembleVide = new ArrayList<>();
		ensembleVide.add(null);
		return coupsPossibles(ensembleVide);
	}
	
	public Main getMain() {
		return main;
	}

	public Pile<Limite> getLimites() {
		return limites;
	}

	public Pile<Bataille> getBatailles() {
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
		return obj instanceof Joueur other &&
				nom.equals(other.nom);
	}
	
	public static void main(String[] args) {
		Joueur j = new Joueur("Jean");
		Carte c = new Parade(1, Type.ACCIDENT);
		j.getMain().prendre(c);
		j.getMain().prendre(c);
		System.out.println(j.coupsParDefault());
	}
	
}
