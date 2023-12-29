package fr.loferga.boundary.tui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Clavier {
	private static Scanner scan = new Scanner(System.in);

	public static int entrerEntier(String question) {
		boolean entreeCorrecte = false;
		int chiffre = 0;
		do {
			System.out.println(question);
			try {
				chiffre = scan.nextInt();
				entreeCorrecte = true;
			} catch (InputMismatchException e) {
				System.out.println("Vous devez entrer un chiffre positif !");
				scan.next();
				entreeCorrecte = false;
			}
		} while (!(entreeCorrecte && chiffre > 0));
		return chiffre;
	}

	public static double entrerDouble(String question) {
		boolean entreeCorrecte = false;
		double chiffre = 0;
		do {
			System.out.println(question);
			try {
				chiffre = scan.nextDouble();
				entreeCorrecte = true;
			} catch (InputMismatchException e) {
				System.out.println("Vous devez entrer un chiffre positif !");
				scan.next();
				entreeCorrecte = false;
			}
		} while (!(entreeCorrecte && chiffre > 0));
		return chiffre;
	}

}
