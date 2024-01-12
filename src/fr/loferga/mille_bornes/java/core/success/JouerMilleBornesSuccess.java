package fr.loferga.mille_bornes.java.core.success;

import fr.loferga.mille_bornes.java.core.account.UserProfile;
import fr.loferga.mille_bornes.java.core.carte.Borne;
import fr.loferga.mille_bornes.java.core.event.EventHandler;
import fr.loferga.mille_bornes.java.core.event.jeu.CardPlayedEvent;
import fr.loferga.mille_bornes.java.core.success.progress.CounterProgress;

public class JouerMilleBornesSuccess extends Success {
	
	@EventHandler
	public void onCarteJouee(CardPlayedEvent e) {
		if (!(e.getPlayer().getProfile() instanceof UserProfile userProfile)) return;
		
		if (!(e.getMove().getCarte() instanceof Borne)) return;
		userProfile.getSuccess(JouerMilleBornesSuccess.class).getProgress().add(1);
	}
	
	private static final String SUCCESS_NAME = "Mille Bornes!";
	
	@Override
	public String getName() {
		return SUCCESS_NAME;
	}
	
	private static final String SUCCESS_DESC = "Jouez 1000 cartes bornes (litéralement)";
	
	@Override
	public String getDescription() {
		return SUCCESS_DESC;
	}
	
	private CounterProgress progress;
	
	@Override
	public CounterProgress getProgress() {
		return progress;
	}

	@Override
	public void initProgress() {
		progress = new CounterProgress(1000);
	}
	
	@Override
	public JouerMilleBornesSuccess clone() {
		JouerMilleBornesSuccess cloned = new JouerMilleBornesSuccess();
		cloned.initProgress();
		return cloned;
	}
	
}
