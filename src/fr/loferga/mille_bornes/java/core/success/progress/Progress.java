package fr.loferga.mille_bornes.java.core.success.progress;

public abstract class Progress {
	
	protected static final String UNLOCKED_STRING = "DONE";
	
	public abstract boolean isDone();
	
	@Override
	public abstract String toString();
	
}
