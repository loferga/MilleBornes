package fr.loferga.mille_bornes.java.core.jeu;

import fr.loferga.mille_bornes.java.core.carte.Carte;
import fr.loferga.mille_bornes.java.utils.Pile;
import fr.loferga.mille_bornes.java.utils.PileAsLinkedList;

/* Sabot comme dans le jeu de plateau
 * poss�de une pile de pioche et une pile de d�fausse
 */
public class Sabot {
	
	// nb max de restitution avant �chec
	private static final int N = 6;
	
	private Pile<Carte> pioche = new PileAsLinkedList<>();
	private Pile<Carte> defausse = new PileAsLinkedList<>();
	
	private int nbRestitutions = 0;
	
	// remet une carte dans la pioche
	public void remettre(Carte c) {
		pioche.empiler(c);
	}
	
	// pioche
	public Carte piocher() {
		return pioche.depiler();
	}
	
	// pioche
	public void defausser(Carte c) {
		defausse.empiler(c);
	}
	
	// nombre carte restantes dans la pioche
	public int reste() {
		return pioche.size();
	}
	
	public boolean isEmpty() {
		return pioche.isEmpty();
	}
	
	// transverse la defausse dans la pioche
	// �chou au bout de N restitutions
	public boolean restituer() {
		if (nbRestitutions > N) return false;
		while (!defausse.isEmpty()) {
			pioche.empiler(defausse.depiler());
		}
		nbRestitutions++;
		return true;
	}
	
}
