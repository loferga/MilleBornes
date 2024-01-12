package fr.loferga.mille_bornes.java.core.success;

import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.carte.Probleme.Type;
import fr.loferga.mille_bornes.java.core.event.EventHandler;
import fr.loferga.mille_bornes.java.core.event.jeu.CardPlayedEvent;
import fr.loferga.mille_bornes.java.core.jeu.joueur.Joueur;
import fr.loferga.mille_bornes.java.core.success.progress.BooleanProgress;

public class QuatreBotteSuccess extends Success {
	
	@EventHandler
	public void onCarteJouee(CardPlayedEvent e) {
		Joueur j = e.getPlayer();
		if (!(j.getProfile() instanceof UserProfile userProfile)) return;
		
		for (Type t : Type.values())
			if (!j.possedeBotte(t))
				return;
		// j a toutes les bottes
		userProfile.getSuccess(QuatreBotteSuccess.class).getProgress().achieve();
	}
	
	private static final String SUCCESS_NAME = "Intouchable";
	
	@Override
	public String getName() {
		return SUCCESS_NAME;
	}
	
	private static final String SUCCESS_DESC = "Possédez toutes les bottes possibles sur le plateau";
	
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
	public QuatreBotteSuccess clone() {
		QuatreBotteSuccess cloned = new QuatreBotteSuccess();
		cloned.initProgress();
		return cloned;
	}
	
}
