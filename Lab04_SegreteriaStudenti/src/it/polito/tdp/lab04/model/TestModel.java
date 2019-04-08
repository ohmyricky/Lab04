package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.StudenteDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		
		String codins="02aqjpg";
				
		List<Studente> iscritti=model.getStudentiPerCorso(codins);
		
		for(Studente s:iscritti)
			System.out.println(s.toString());
		
		

	}

}
