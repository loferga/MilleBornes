package fr.loferga.mille_bornes.java.core.success;

import fr.loferga.mille_bornes.java.core.event.Listener;
import fr.loferga.mille_bornes.java.core.success.progress.Progress;

public abstract class Success implements Listener, Cloneable {
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	public abstract void initProgress();
	
	public abstract Progress getProgress();
	
	public boolean isUnlocked() {
		return getProgress().isDone();
	}
	
	@Override
	public abstract Success clone();
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public final boolean equals(Object other) {
		return other != null && this.getClass() == other.getClass();
	}
	
	@Override
	public final int hashCode() {
		return this.getClass().hashCode();
	}
	
}
