package fr.loferga.mille_bornes.java.core.success;

import java.util.function.Predicate;

import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.carte.Attaque;
import fr.loferga.mille_bornes.java.core.event.EventHandler;
import fr.loferga.mille_bornes.java.core.event.jeu.CardPlayedEvent;
import fr.loferga.mille_bornes.java.core.jeu.Coup;
import fr.loferga.mille_bornes.java.core.success.progress.BooleanProgress;

public class AttaqueJoueeSuccess extends Success {
	
	private static final Predicate<Coup> isAttaque = c -> c.getCarte() instanceof Attaque && c.getCible() != null;
	
	@EventHandler
	public void onCarteJouee(CardPlayedEvent e) {
		if (!(e.getPlayer().getProfile() instanceof UserProfile userProfile)) return;
		
		if (isAttaque.test(e.getMove())) {
			userProfile.getSuccess(AttaqueJoueeSuccess.class).getProgress().achieve();;
		}
	}
	
	private static final String SUCCESS_NAME = "A l'attaque!";
	
	@Override
	public String getName() {
		return SUCCESS_NAME;
	}
	
	private static final String SUCCESS_DESC = "Jouez une carte de type Attaque sur un autre Joueur";
	
	@Override
	public String getDescription() {
		return SUCCESS_DESC;
	}
	
	private BooleanProgress progress;
	
	@Override
	public BooleanProgress getProgress() {
		return progress;
	}

	@Override
	public void initProgress() {
		progress = new BooleanProgress();
	}
	
	@Override
	public AttaqueJoueeSuccess clone() {
		AttaqueJoueeSuccess cloned = new AttaqueJoueeSuccess();
		cloned.initProgress();
		return cloned;
	}

}
