package dev.console;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
import dev.service.CalculService;

public class AppTest {
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);
	
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	private CalculService calculService;
	private Scanner scanner;
	private App app = new App(scanner, calculService);
	

	@Before
	public void setUp() throws Exception {
		this.calculService = mock(CalculService.class);
		this.app = new App(new Scanner(System.in), calculService);
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();

		String logConsole = systemOutRule.getLog();

		assertThat(logConsole).contains("**** Application Calculatrice ****");

	}

	@Test
	public void testEvaluer() throws Exception {

		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(35);

		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);

		verify(calculService).additionner(expression);

		assertThat(systemOutRule.getLog()).contains("1+34=35");

	}

	@Test
	public void testEvaluerNonValide() throws CalculException {
		String msg = null;
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1-34";
		when(calculService.additionner(expression)).thenThrow(CalculException.class);

		LOG.info("Lorsque la méthode evaluer est invoquée");
		try {
			this.app.evaluer(expression);
		} catch (CalculException e) {
			// TODO Auto-generated catch block*
			msg = "Erreur d'expression";
		}

		verify(calculService).additionner(expression);

		assertThat(msg).contains("Erreur d'expression");

	}

	@Test
	public void testEtape1() {
		this.app = new App(new Scanner(System.in), new CalculService());
		systemInMock.provideLines("fin");
		this.app.demarrer();

		assertThat(systemOutRule.getLog()).contains("Aurevoir");

	}

	@Test
	public void testEtape2() {
		this.app = new App(new Scanner(System.in), new CalculService());
		systemInMock.provideLines("1+2", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Aurevoir");

	}
	@Test
	public void testEtape3() {
		this.app = new App(new Scanner(System.in), new CalculService());
		systemInMock.provideLines("AAAA", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("L'expression AAAA est invalide");
		assertThat(systemOutRule.getLog()).contains("Aurevoir");

	}
	@Test
	public void testEtape4() {
		this.app = new App(new Scanner(System.in), new CalculService());
		systemInMock.provideLines("1+2", "30+2", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("30+2=32");
		assertThat(systemOutRule.getLog()).contains("Aurevoir");

	}
	
}
