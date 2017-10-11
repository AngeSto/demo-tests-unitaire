package dev.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;
import dev.exception.CalculException;

/**
 *  * Test unitaire de la classe dev.service.CalculService.  
 */
public class CalculServiceTest {
	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws Exception {
		LOG.info("Etant donné, une instance de la classe CalculService");
		// TODO
		CalculService cs = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		// TODO
		int somme = cs.additionner("1+3+4");
		LOG.info("Alors j'obtiens le résultat "+somme);
		assertThat(somme).isEqualTo(8);
	}
	
	@Test
	public void testAdditionnerNonValide() throws Exception {

		CalculService cs = new CalculService();
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3-4");
		// TODO
		String msg = null;
		try {
			cs.additionner("1+3-4");
		} catch (CalculException e) {
			msg = e.getMessage();
		}
		assert (msg!=null && msg.equals("Erreur d'expression"));
	}
}