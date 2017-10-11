package dev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;

public class CalculService {
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);
	
	public int additionner(String expression) throws CalculException{
		
		LOG.debug("Evaluation de l'expression "+expression);
		String[] tab = expression.split("\\+");
		Integer somme = 0;
		for (String i : tab){
			try {
				somme += Integer.parseInt(i);
			} catch (NumberFormatException e) {
				throw new CalculException("L'expression "+expression+" est invalide");
			}
		}

		return somme;
	}

}
