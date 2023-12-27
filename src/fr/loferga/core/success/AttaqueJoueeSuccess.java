package fr.loferga.core.success;

import java.util.function.Predicate;

import fr.loferga.core.account.UserProfile;
import fr.loferga.core.carte.Attaque;
import fr.loferga.core.event.EventHandler;
import fr.loferga.core.event.jeu.CarteJoueeEvent;
import fr.loferga.core.jeu.Coup;
import fr.loferga.core.success.progress.BooleanProgress;

public class AttaqueJoueeSuccess extends Success {
	
	private void grant() {
		progress.achieve();
	}
	
	private static final Predicate<Coup> isAttaque = c -> c.getCarte() instanceof Attaque && c.getCible() != null;
	
	@EventHandler
	public void onCarteJouee(CarteJoueeEvent e) {
		UserProfile userProfile = e.getJoueur().getUser();
		if (userProfile == null) return;
		
		if (isAttaque.test(e.getCoupJoue())) {
			userProfile.getSuccess(this).grant();
		}
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
