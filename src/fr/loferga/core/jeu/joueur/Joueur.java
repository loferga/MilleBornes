package fr.loferga.core.jeu.joueur;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fr.loferga.core.account.Profile;
import fr.loferga.core.account.UserProfile;
import fr.loferga.core.carte.Attaque;
import fr.loferga.core.carte.Bataille;
import fr.loferga.core.carte.Borne;
import fr.loferga.core.carte.Botte;
import fr.loferga.core.carte.Carte;
import fr.loferga.core.carte.FinLimite;
import fr.loferga.core.carte.Limite;
import fr.loferga.core.carte.Parade;
import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.core.jeu.Coup;
import fr.loferga.core.jeu.Jeu;
import fr.loferga.core.jeu.Main;
import fr.loferga.core.jeu.MainAsList;
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
	
	public boolean isPlaying() {
		return jeu != null;
	}
	
	public Profile getProfile() {
		if (!isPlaying()) return null;
		return jeu.getProfile(this);
	}
	
	public UserProfile getUser() {
		Profile profile = getProfile();
		if (profile != null && profile instanceof UserProfile userProfile)
			return userProfile;
		return null;
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
	
	/* interprète le sommet de la pile de bataille:
	 * si la pile est vide prendre le sommet "fictif"
	 * au début de la partie tout le monde doit poser
	 * un feu vert ou un véhicule prioritaire pour
	 * avancer, c'est comme si ils avait un feu rouge
	 */
	public Bataille sommetBataille() {
		if (batailles.isEmpty()) {
			if (possedeBotte(Type.FEU))
				return Parade.FEU_VERT;
			return Attaque.FEU_ROUGE;
		}
		return batailles.sommet();
	}
	
	public boolean estBloque() {
		Bataille sommet = this.sommetBataille();
		return sommet instanceof Attaque // derniereBataille est une Attaque
				&& !possedeBotte(sommet.getType()); // le joueur n'est pas immunisé contre cette Attaque
		// le test sur la possession d'une botte associer est obsolète car le sommet de la bataille ne
		// peux pas contenir une telle Attaque (cf. Coup fourrés dans fr.loferga.carte.Botte)
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants/*List -> Set car Jeu possède un Set de joueurs*/) {
		Set<Coup> resultat = new HashSet<>();
		for (Joueur cible : participants) {
			for (Carte carte : main) {
				Coup coup = new Coup(carte, cible);
				if (coup.estValide(this)) {
					resultat.add(coup);
				}
			}
		}
		return resultat;
	}
	
	public Set<Coup> coupsParDefault() {
		Set<Coup> resultat = new HashSet<>();
		for (Carte carte : main) {
			resultat.add(new Coup(carte, null));
		}
		if (resultat.isEmpty())
			throw new IllegalStateException("La main est vide");
		return resultat;
	}
	
	// si aucun coup n'est possible Optional.empty()
	protected final Optional<Coup> jouerPremierPossible(Collection<Coup> coups) {
		for (Iterator<Coup> it = coups.iterator(); it.hasNext();) {
			Coup next = it.next();
			if (next.estValide(this)) {
				next.jouer(this);
				return Optional.of(next);
			}
		}
		return Optional.empty();
	}
	
	protected final Coup jouerPremier(Collection<Coup> coups) {
		if (coups.isEmpty())
			throw new IllegalArgumentException("coups ne peux pas être vide");
		Iterator<Coup> it = coups.iterator();
		Coup first = it.next();
		first.jouer(this);
		return first;
	}
	
	public Optional<Coup> selectionner() {
		Set<Coup> coups = coupsPossibles(jeu.getJoueurs());
		return jouerPremierPossible(coups);
	}
	
	// pré-condition: la main n'est pas vide
	public Coup rendreCarte() {
		Set<Coup> coups = coupsParDefault();
		return jouerPremier(coups);
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
