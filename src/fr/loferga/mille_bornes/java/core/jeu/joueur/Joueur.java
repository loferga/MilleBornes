package fr.loferga.mille_bornes.java.core.jeu.joueur;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import fr.loferga.mille_bornes.java.core.account.Profile;
import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.carte.Bataille;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.carte.Botte;
import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.core.carte.FinLimite;
import fr.loferga.mille_bornes.java.core.carte.Limite;
import fr.loferga.mille_bornes.java.core.carte.Parade;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.core.jeu.Jeu;
import fr.loferga.mille_bornes.java.core.jeu.Main;
import fr.loferga.mille_bornes.java.core.jeu.MainAsList;
import fr.loferga.mille_bornes.java.utils.Pile;
import fr.loferga.mille_bornes.java.utils.PileAsLinkedList;
import fr.loferga.mille_bornes.java.utils.Utils;

public class Joueur {
	
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	protected Profile profile;
	protected Main main = new MainAsList();
	
	protected boolean isPlaying = false;
	protected Jeu jeu;

	// cartes sur plateau
	private Pile<Limite> limites = new PileAsLinkedList<>();
	private Pile<Bataille> batailles = new PileAsLinkedList<>();
	private Collection<Borne> bornes = new LinkedList<>();
	private Set<Botte> bottes = new HashSet<>();
	
	public Joueur(Profile profile) {
		this.profile = profile;
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
	
	public boolean isPlaying() {
		return isPlaying;
	}
	
	public Jeu getJeu() {
		return this.jeu;
	}
	
	public void setJeu(Jeu jeu) {
		this.isPlaying = true;
		this.jeu = jeu;
	}
	
	public Profile getProfile() {
		return this.profile;
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
				|| limites.sommet() instanceof FinLimite // derni�re limite est une fin de limite
				)
			return 200;
		return 50;
	}
	
	/* interprète le sommet de la pile de bataille:
	 * si la pile est vide prendre le sommet "fictif"
	 * au d"but de la partie tout le monde doit poser
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
	
	public Collection<Coup> coupsValides(Set<Joueur> participants) {
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
	
	public Collection<Coup> coupsDefausse() {
		Set<Coup> resultat = new HashSet<>();
		for (Carte carte : main) {
			resultat.add(new Coup(carte, null));
		}
		if (resultat.isEmpty())
			throw new IllegalStateException("La main est vide");
		return resultat;
	}
	
	protected Optional<Coup> chercherCoup(Collection<Coup> coups) {
		return coups.stream().findFirst();
	}
	
	public Coup selectionner() {
		Collection<Coup> coups;
		coups = coupsValides(jeu.getJoueurs());
		Optional<Coup> coupApplicable = chercherCoup(coups);
		if (coupApplicable.isPresent()) {
			return coupApplicable.get();
		}
		coups = coupsDefausse();
		if (coups.isEmpty())
			throw new IllegalStateException("Le joueur " + this.toString() + " ne peux pas jouer");
		return chercherCoup(coupsDefausse()).get(); /* !coupsDefausse.empty */
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
		StringBuilder str = new StringBuilder();
		if (estBloque())
			str.append(ANSI_RED);
		else if (getLimite() != 200)
			str.append(ANSI_YELLOW);
		else
			str.append(ANSI_GREEN);
		str.append(profile.getName());
		str.append(ANSI_RESET);
		return str.toString();
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof Joueur joueur
			&& this.profile.equals(joueur.profile);
	}
	
	@Override
	public int hashCode() {
		return this.profile.hashCode();
	}
	
}
