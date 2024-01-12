package fr.loferga.mille_bornes.java.core.success.progress;

public class BooleanProgress extends Progress {
	
	private boolean achieved;
	
	public BooleanProgress() {
		achieved = false;
	}
	
	@Override
	public boolean isDone() {
		return achieved;
	}
	
	public void achieve() {
		achieved = true;
	}
	
	@Override
	public String toString() {
		return isDone() ? UNLOCKED_STRING : "not unlocked";
	}

}
