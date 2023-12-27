package fr.loferga.core.success.progress;

public class BooleanProgress implements Progress {
	
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

}
