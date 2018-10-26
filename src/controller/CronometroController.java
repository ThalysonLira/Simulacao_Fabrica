package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.Observable;

import application.Main;
import cronometro.Acelerar;
import cronometro.Cronometro;
import cronometro.Reduzir;

public class CronometroController{
	private Stage stage;
  	private Parent parent;
  	private Cronometro cronometro;
  	    	
    @FXML
    private TextField tfTempoCronometro;

    @FXML
    private ComboBox<String> cbOpcoes;

    @FXML
    private RadioButton rbAcelerar;

    @FXML
    private RadioButton rbReduzir;
    
    public Cronometro getCronometro() {
		return cronometro;
	}

	public void setCronometro(Cronometro cronometro) {
		this.cronometro = cronometro;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@FXML
    void handleAcelerar(ActionEvent event) {
    	rbReduzir.setSelected(false);
    }

    @FXML
    void handleReduzir(ActionEvent event) {
    	rbAcelerar.setSelected(false);
    }
    
    @FXML
    void handleEnviarTempo(ActionEvent event) throws NumberFormatException, Exception {	
    	if(rbAcelerar.isSelected()) {
			cronometro = new Acelerar();
		}
		else if(rbReduzir.isSelected()) {
			cronometro = new Reduzir();
		}
		else{
			rbAcelerar.requestFocus();
// Adicionar Aviso				
		}
    	
    	try {
		cronometro.indicarTempo(Double.parseDouble(tfTempoCronometro.getText()));
		cronometro.setStatus("Executando");
		new Thread(cronometro).start();	
		this.getStage().close();
    	}
    	
    	catch(Exception e) {
// Adicionar Aviso	    		
    	}
    }
    
    public void abrirTela() {
		this.setStage(new Stage());
		Scene scene = new Scene(parent, 500, 400);
		stage.setTitle("Tempo de Simulação");
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
    }
}