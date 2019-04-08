package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	private Model model;
	List<Corso> corsi;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="CorsiSwitch"
    private ComboBox<Corso> CorsiSwitch; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResult"
    private TextField txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	
    	int matr=Integer.parseInt(txtMatricola.getText());
    	
    	Studente s=model.cercaStud(matr);
    	if(s==null) {
    		txtResult.appendText("Matricola inesistente");
    	} else {
    		List<Corso> corsi=model.getCorsiperStudente(matr);
    		
    		txtResult.appendText(corsi.toString());
    	}

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtResult.clear();
    	
    	Corso corso=CorsiSwitch.getValue();
    	if(!(corso.getCrediti()==8)) {
    		txtResult.appendText("Selezionare Corso");
    	}
    	
    	List<Studente> iscritti=model.getStudentiPerCorso(corso.getCodins());
    	
    	String res="";
    	for(Studente s:iscritti) {
    		res=s.toString()+"\n"+res;
    	}
    	txtResult.appendText(res);

    }

    @FXML
    void doControllaMatricola(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    	
    	int m= Integer.parseInt(txtMatricola.getText());
    	Studente s=this.model.cercaStud(m);
    	txtNome.appendText(s.getNome());
    	txtCognome.appendText(s.getCognome());
    	
    	Corso corso=CorsiSwitch.getValue();
    	if(corso.getCrediti()==8) {
    		boolean flag=false;
    		List<Corso> corsi=model.getCorsiperStudente(m);
    		for(Corso temp: corsi) {
    			if(temp.getNome().equals(corso.getNome())) {
    				txtResult.appendText("Lo studente è iscritto al corso.");
    				flag=true;
    			}    			
    		}
    		if(!flag)
    			txtResult.appendText("Lo studente NON è iscritto al corso.");
    		
    		
    		/*if(corsi.contains(corso))
    			txtResult.appendText("Lo studente è iscritto al corso.");
    		else
    			txtResult.appendText("Lo studente NON è iscritto al corso.");*/
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();    	
    }
    
    private void setComboItems() {

		// Ottieni tutti i corsi dal model
		corsi = model.getCorsi();

		// Aggiungi tutti i corsi alla ComboBox
		Collections.sort(corsi);
		CorsiSwitch.getItems().addAll(corsi);
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 assert CorsiSwitch != null : "fx:id=\"CorsiSwitch\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
         assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		setComboItems();
	}
}
