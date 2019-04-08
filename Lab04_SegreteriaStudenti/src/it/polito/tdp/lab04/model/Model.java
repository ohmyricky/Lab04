package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO cdao= new CorsoDAO();
	StudenteDAO sdao= new StudenteDAO();
	
	public Studente cercaStud(int matricola) {
		Studente s=sdao.cercaStudPerMatr(matricola);
		return s;
	}

	public List<Corso> getCorsi() {
		
		return cdao.getTuttiICorsi();		
	}
	
	public List<Studente> getStudentiPerCorso(String codins){
		return sdao.cercaStudPerCorso(codins);
	}
	
	public List<Corso> getCorsiperStudente(int matricola) {
		return cdao.getCorsiPerStudente(matricola);
	}

}
