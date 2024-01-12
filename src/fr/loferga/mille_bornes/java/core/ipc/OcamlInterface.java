package fr.loferga.mille_bornes.java.core.ipc;

import java.io.IOException;
import java.util.Scanner;

import fr.loferga.mille_bornes.java.core.account.UserProfile;

public class OcamlInterface {
	
	private static final String OCAML_EXECUTABLE = "bin/ocaml/interface";
	
	private enum Request {
		
		AVG_WIN(0), AVG_LOSE_SCORE(1);
		
		private int code;
		
		private Request(int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
		
	}
	
	private Process ocamlProcess;
	private Scanner responseScanner;
	
	public OcamlInterface(UserProfile userProfile) {
		ProcessBuilder ocamlProcessBuilder = new ProcessBuilder();
		ocamlProcessBuilder.command(OCAML_EXECUTABLE);
		try {
			ocamlProcess = ocamlProcessBuilder.start();
			responseScanner = new Scanner(ocamlProcess.inputReader());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendRequest(Request request) {
		try {
			ocamlProcess.getOutputStream().write(request.getCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public float askAverageWin() {
		sendRequest(Request.AVG_WIN);
		return responseScanner.nextFloat();
	}
	
	public int askAverageLoseScore() {
		sendRequest(Request.AVG_LOSE_SCORE);
		return responseScanner.nextInt();
	}
	
	public void kill() {
		ocamlProcess.destroyForcibly();
	}
	
}
