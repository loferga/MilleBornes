package fr.loferga.mille_bornes.java.core.jeu.joueur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import fr.loferga.mille_bornes.java.core.account.Bot;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.utils.Utils;

public class JoueurChanceux extends Joueur {
	
	public JoueurChanceux(Bot botProfile) {
		super(botProfile);
	}
	
	private Collection<Coup> melangerCoups(Collection<Coup> coups) {
		List<Coup> coupsAsList = coups instanceof List<Coup> list ? list : new ArrayList<>(coups);
		return Utils.melanger(coupsAsList);
	}
	
	@Override
	public Collection<Coup> coupsValides(Set<Joueur> participants) {
		return melangerCoups(super.coupsValides(participants));
	}
	
	@Override
	public Collection<Coup> coupsDefausse() {
		return melangerCoups(super.coupsDefausse());
	}

}
