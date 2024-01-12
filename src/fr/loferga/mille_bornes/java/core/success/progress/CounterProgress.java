package fr.loferga.mille_bornes.java.core.success.progress;

public class CounterProgress extends Progress {
	
	private int toAchieve;
	private int counter;
	
	public CounterProgress(int toAchieve) {
		this.toAchieve = toAchieve;
		this.counter = 0;
	}
	
	@Override
	public boolean isDone() {
		return counter == toAchieve;
	}
	
	public void add(int value) {
		counter += value;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (isDone()) {
			str.append(UNLOCKED_STRING);
			str.append(' ');
		}
		str.append('(');
		str.append(counter);
		str.append('/');
		str.append(toAchieve);
		str.append(')');
		return str.toString();
	}
	
}
