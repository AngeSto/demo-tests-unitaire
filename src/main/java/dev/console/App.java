package dev.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	private Scanner scanner;
	private CalculService calculatrice;

	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}

	protected void demarrer() {
		afficherTitre();
		boolean continuer = true;
		while (continuer) {
			try {
				String choix = scanner.nextLine();
				switch (choix) {

				case "fin":
					LOG.info("Aurevoir :-(");
					continuer = false;
					break;

				default:
					evaluer(choix);
					break;
				}
			} catch (CalculException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	public void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");

	}

	protected void evaluer(String expression) {
		int resultat = calculatrice.additionner(expression);
		LOG.info(expression + "=" + resultat);

	}

}
