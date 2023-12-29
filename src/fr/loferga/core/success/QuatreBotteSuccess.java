package fr.loferga.core.success;

import fr.loferga.core.account.UserProfile;
import fr.loferga.core.carte.Probleme.Type;
import fr.loferga.core.event.EventHandler;
import fr.loferga.core.event.jeu.CarteJoueeEvent;
import fr.loferga.core.jeu.joueur.Joueur;
import fr.loferga.core.success.progress.BooleanProgress;

public class QuatreBotteSuccess extends Success {
	
	private void grant() {
		progress.achieve();
	}
	
	@EventHandler
	public void onCarteJouee(CarteJoueeEvent e) {
		UserProfile userProfile = e.getJoueur().getUser();
		if (userProfile == null) return;
		
		Joueur j = e.getJoueur();
		for (Type t : Type.values())
			if (!j.possedeBotte(t))
				return;
		// j a toutes les bottes
		userProfile.getSuccess(this).grant();
	}
	
	private static final String SUCCESS_NAME = "Intouchable";
	
	@Override
	public String getName() {
		return SUCCESS_NAME;
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
	public QuatreBotteSuccess clone() {
		QuatreBotteSuccess cloned = new QuatreBotteSuccess();
		cloned.initProgress();
		return cloned;
	}
	
}
