 package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente cercaStudPerMatr(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola= ? ";

		Studente s=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int mat= rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				s=new Studente(mat, nome, cognome, cds);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				
			}
			conn.close();


		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Errore Db, studente non trovato");
		}
		return s;
	}
		
	public List<Studente> cercaStudPerCorso(String codins) {

		final String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS " + 
				"FROM iscrizione AS i, studente AS s " + 
				"WHERE s.matricola=i.matricola AND i.codins= ? ";

		List<Studente> studenti=new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int mat= rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Studente temp=new Studente(mat, nome, cognome, cds);
				studenti.add(temp);
				
			}
			conn.close();


		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Errore Db, studente non trovato");
		}
		return studenti;
	}

}
