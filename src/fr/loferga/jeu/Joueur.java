package fr.loferga.jeu;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
	
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	protected String nom;
	protected Main main = new MainAsList();
	protected Jeu jeu;

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
		Carte piochee = sabot.get(0);
		sabot.remove(0); // remove piochee
		donner(piochee);
		return piochee;
	}
	
	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public int getKM() {
		int totalKM = 0;
		for (Borne borne : bornes) {
			totalKM += borne.getKm();
		}
		return totalKM;
	}
	
	public boolean possedeBotte(Type t) {
		return bottes.contains(new Botte(1, t));
	}
	
	public int getLimite() {
		if (possedeBotte(Type.FEU) // joueur prioritaire
				|| limites.isEmpty() // pas de limite
				|| limites.sommet() instanceof FinLimite // dernière limite est une fin de limite
				)
			return 200;
		return 50;
	}
	
	/*
	 * interprète le sommet de la pile de bataille
	 */
	public Bataille interpreterSommet() {
		if (batailles.isEmpty()) {
			if (possedeBotte(Type.FEU))
				return Parade.FEU_VERT;
			return Attaque.FEU_ROUGE;
		}
		return batailles.sommet();
	}
	
	// TODO envoyer
	public boolean estBloque() {
		Bataille sommet = interpreterSommet();
		if (sommet instanceof Attaque // derniereBataille est une Attaque
				&& !possedeBotte(sommet.getType())) // le joueur n'est pas immunisé contre cette Attaque
			return true;
		return false;
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants/*List -> Set car Jeu possède un Set de joueurs*/) {
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
		Set<Joueur> singletonVide = new HashSet<>();
		singletonVide.add(null);
		return coupsPossibles(singletonVide);
	}
	
	protected final Optional<Coup> jouerPremierPossible(Set<Coup> coups) {
		for (Iterator<Coup> it = coups.iterator(); it.hasNext();) {
			Coup next = it.next();
			if (next.jouer(this)) {
				return Optional.of(next);
			}
		}
		return Optional.empty();
	}
	
	public Optional<Coup> selectionner() {
		Set<Coup> coups = coupsPossibles(jeu.getJoueurs());
		return jouerPremierPossible(coups);
	}
	
	public Coup rendreCarte() {
		Set<Coup> coups = coupsParDefault();
		Iterator<Coup> it = coups.iterator();
		Coup next = it.next();
		next.jouer(this);
		return next;
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
	
	private static final String LABEL = "J";
	
	protected String getLabel() {
		return LABEL;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (estBloque())
			str.append(ANSI_RED);
		else if (getLimite() != 200)
			str.append(ANSI_YELLOW);
		else
			str.append(ANSI_GREEN);
		str.append(this.getLabel());
		str.append(nom);
		str.append(ANSI_RESET);
		return str.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur other = (Joueur) obj;
			return nom.equals(other.nom);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return (31 * getClass().hashCode() * nom.hashCode());
	}
	
}
