package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import cronometro.Cronometro;
import factory.CronometroFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Caminhao;
import model.Empacotadora;
import model.Esteira;
import model.Forno;
import model.Maquina;
import model.Patio;
import model.Peca;
import model.Trabalhadores;

public class FluxoController implements Initializable, Observer{
	
		@FXML
	    private TextField tfCapacidadeEsteira1;

	    @FXML
	    private TextField tfTempoEsteira1;

	    @FXML
	    private TextField tfConsumoEsteira1;

	    @FXML
	    private TextField tfQuantidadeEsteira1;

	    @FXML
	    private TextField tfStatusEsteira1;

	    @FXML
	    private TextField tfCapacidadeForno;

	    @FXML
	    private TextField tfTempoForno;

	    @FXML
	    private TextField tfConsumoForno;

	    @FXML
	    private TextField tfQuantidadeForno;

	    @FXML
	    private TextField tfStatusForno;

	    @FXML
	    private TextField tfCapacidadeMaquina;

	    @FXML
	    private TextField tfTempoMaquina;

	    @FXML
	    private TextField tfConsumoMaquina;

	    @FXML
	    private TextField tfQuantidadeMaquina;

	    @FXML
	    private TextField tfStatusMaquina;

	    @FXML
	    private TextField tfCapacidadeEsteira2;

	    @FXML
	    private TextField tfTempoEsteira2;

	    @FXML
	    private TextField tfConsumoEsteira2;

	    @FXML
	    private TextField tfQuantidadeEsteira2;

	    @FXML
	    private TextField tfStatusEsteira2;

	    @FXML
	    private TextField tfCapacidadeCaminhao;

	    @FXML
	    private TextField tfVelCheioCaminhao;

	    @FXML
	    private TextField tfVelVazioCaminhao;

	    @FXML
	    private TextField tfConsumoCaminhao;

	    @FXML
	    private TextField tfStatusCaminhao;

	    @FXML
	    private TextField tfQuantidadeCaminhao;

	    @FXML
	    private TextField tfCapacidadeTrabalhadores;

	    @FXML
	    private TextField tfTempoCargaTrabalhadores;

	    @FXML
	    private TextField tfTempoTransporteTrabalhadores;

	    @FXML
	    private TextField tfConsumoTrabalhadores;

	    @FXML
	    private TextField tfQuantidadeTrabalhadores;

	    @FXML
	    private TextField tfDiariaTrabalhadores;

	    @FXML
	    private TextField tfStatusTrabalhadores;

	    @FXML
	    private TextField tfCapacidadeEmpacotadora;

	    @FXML
	    private TextField tfConsumoEmpacotadora;

	    @FXML
	    private TextField tfStatusEmpacotadora;

	    @FXML
	    private TextField tfQuantidadeEmpacotadora;

	    @FXML
	    private TextField tfCapacidadePatio;

	    @FXML
	    private TextField tfStatusPatio;

	    @FXML
	    private TextField tfQuantidadePatio;

	    @FXML
	    private TextField tfTempoDecorrido;
	  
	    @FXML
	    private TextField tfTempoEmpacotadora;
	    
	    @FXML
	    private TextField tfDistanciaFabricaEmpacotadora;
	    
	    @FXML
	    private Button btIniciar;

	    @FXML
	    private Button btPlayPausa;

	    @FXML
	    private Button btParar;
	    
	    private CronometroController cronometro;
	    
	    private Esteira e1;
	    private Forno f1;
	    private Maquina m1;
	    private Esteira e2;
	    private Caminhao c1;
	    private Trabalhadores t1;
	    private Empacotadora emp1;
	    private Patio p1;    
	    private int contollerE1, contollerE2, contollerF1,
	    contollerM1, contollerC1, contollerT1, contollerEmp1;
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			this.botoesIniciar();
			
			try {
				this.criarEntidades();
			} catch (Exception e) {
				// TODO Auto-generated catch block
// Adicionar Aviso					
			}
		}	
	    
		@FXML
	    void handleIniciar(ActionEvent event) {
	    	try {
				this.criarCronometro();
				tfTempoDecorrido.setText(cronometro.getCronometro().informarTempo());
				this.executar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
// Adicionar Aviso				
			}
	    	
			this.botoesExecucao();
			this.adicionarDependeteCronometro();
		}

	    @FXML
	    void handleParar(ActionEvent event) {
    		cronometro.getCronometro().setStatus("Parado");
    		this.botoesIniciar();
	    }

	    @FXML
	    void handlePausar(ActionEvent event) {
	    	if(btPlayPausa.getText().equals("Pausar")) {
	    		btPlayPausa.setText("Retomar");
	    		cronometro.getCronometro().setStatus("Pausado");
	    	}
	    	else {
	    		btPlayPausa.setText("Pausar");	
	    		cronometro.getCronometro().setStatus("Executando");
	    	}
	    }
	    
	    private void criarEntidades() throws NumberFormatException, Exception {
	    	// Iniciando Esteira 1
	    	e1 = new Esteira(1, Float.parseFloat(tfCapacidadeEsteira1.getText()),
	    			this.calcularMilissegundos(tfTempoEsteira1),
	    			Float.parseFloat(tfConsumoEsteira1.getText()));
	    	new Thread(e1).start();	
	    	
	    	// Iniciando Forno 1
	    	f1 = new Forno(1, Float.parseFloat(tfCapacidadeForno.getText()),
	    			this.calcularMilissegundos(tfTempoForno),
	    			Float.parseFloat(tfConsumoForno.getText()));
	    	new Thread(f1).start();	
	    	
//	    	// Iniciando Maquina 1
//	    	m1 = new Maquina(1, Float.parseFloat(tfCapacidadeMaquina.getText()),
//	    			this.calcularMilissegundos(tfTempoMaquina),
//	    			Float.parseFloat(tfConsumoMaquina.getText()));
////	    	new Thread(m1).start();	
//	    	
//	    	// Iniciando Esteira 2
//	    	e2 = new Esteira(2, Float.parseFloat(tfCapacidadeEsteira2.getText()),
//	    			this.calcularMilissegundos(tfTempoEsteira2),
//	    			Float.parseFloat(tfConsumoEsteira2.getText()));
////	    	new Thread(e2).start();	
//	    	
//	    	// Iniciando Caminhao 1
//	    	c1 = new Caminhao(1, Float.parseFloat(tfCapacidadeCaminhao.getText()),
//	    			Float.parseFloat(tfVelCheioCaminhao.getText()),
//	    			Float.parseFloat(tfVelVazioCaminhao.getText()),
//	    			Float.parseFloat(tfConsumoCaminhao.getText()));
////	    	new Thread(c1).start();	
//	    	
//	    	// Iniciando Trabalhadores 1
//	    	t1 = new Trabalhadores(1, Float.parseFloat(tfCapacidadeCaminhao.getText()),
//	    			Integer.parseInt(tfQuantidadeTrabalhadores.getText()),
//	    			this.calcularMilissegundos(tfTempoCargaTrabalhadores),
//	    			this.calcularMilissegundos(tfTempoTransporteTrabalhadores),
//	    			Float.parseFloat(tfConsumoTrabalhadores.getText()),
//	    			Float.parseFloat(tfDiariaTrabalhadores.getText()));
////	    	new Thread(t1).start();	
//	    	
//	    	// Iniciando Empacotadora 1
//	    	emp1 = new Empacotadora(1, Float.parseFloat(tfCapacidadeEmpacotadora.getText()),
//	    			this.calcularMilissegundos(tfTempoEmpacotadora),
//	    			Float.parseFloat(tfConsumoEmpacotadora.getText()));
////	    	new Thread(emp1).start();	
//	    	
//	    	// Iniciando Patio 1
//	    	p1 = new Patio(1, Float.parseFloat(tfCapacidadeEmpacotadora.getText()));
////	    	new Thread(p1).start();	
	    }
	    
	    private int calcularMilissegundos(TextField entidade) {
	    	String tempo = entidade.getPromptText();
	    	int mili = 0;
	    	
	    	switch(tempo) {
	    	case "Dias":
	    		mili = Integer.parseInt(entidade.getText()) * 86400000;
	    		break;
	    		
	    	case "Horas":
	    		mili = Integer.parseInt(entidade.getText())  * 3600000;
	    		break;
	    		
	    	case "Minutos":
	    		mili = Integer.parseInt(entidade.getText())  * 60000;
	    		break;
	    		
	    	case "Segundos":
	    		mili = Integer.parseInt(entidade.getText())  * 1000;
	    		break;
	    	}
	    	
	    	return mili;
	    }
	    
	    private int calcularSegundos(TextField entidade) {
	    	String tempo = entidade.getPromptText();
	    	int mili = 0;
	    	
	    	switch(tempo) {
	    	case "Dias":
	    		mili = Integer.parseInt(entidade.getText()) * 864000;
	    		break;
	    		
	    	case "Horas":
	    		mili = Integer.parseInt(entidade.getText())  * 3600;
	    		break;
	    		
	    	case "Minutos":
	    		mili = Integer.parseInt(entidade.getText())  * 60;
	    		break;
	    		
	    	case "Segundos":
	    		mili = Integer.parseInt(entidade.getText())  * 1;
	    		break;
	    	}
	    	
	    	return mili;
	    }
	    
	    private void botoesIniciar() {
	    	btIniciar.setVisible(true);
	    	btParar.setVisible(false);
	    	btPlayPausa.setVisible(false);
	    }
	    
	    private void botoesExecucao() {
	    	btIniciar.setVisible(false);
	    	btParar.setVisible(true);
	    	btPlayPausa.setVisible(true);
	    }

		private void criarCronometro() throws IOException {
			cronometro = CronometroFactory.getInstance();
			cronometro.abrirTela();
		}
		
		private void adicionarDependeteCronometro() {
			Observable obs = cronometro.getCronometro();
			obs.addObserver(this);
		}

		@Override
		public void update(Observable arg0, Object arg1) {
			// TODO Auto-generated method stub
			try {
			tfTempoDecorrido.setText(cronometro.getCronometro().informarTempo());
			tfStatusEsteira1.setText(e1.getStatus());
			tfStatusForno.setText(f1.getStatus());
//			tfStatusMaquina.setText(m1.getStatus());
//			tfStatusEsteira2.setText(e2.getStatus());
//			tfStatusCaminhao.setText(c1.getStatus());
//			tfStatusTrabalhadores.setText(t1.getStatus());
//			tfStatusEmpacotadora.setText(emp1.getStatus());
//			tfStatusPatio.setText(p1.getStatus());
			
			this.executar();
			}
			catch(Exception e) {
				e.getMessage();
			}
		}
		
		private void entradaPecaEsteira() {			
			if(this.e1.verificarDisponibilidade()) {
				this.e1.novaEntrada(new Peca(100, cronometro.getCronometro().tempoTotal()));
			}
		}
		
		public void transicaoEsteiraForno() {	
			if(this.f1.verificarDisponibilidade()) {
				this.f1.novaEntrada(this.e1.novaSaida());
				this.e1.verificarDisponibilidade();
			}
		}
		
		public void transicaoFornoMaquina() {
			if(this.m1.verificarDisponibilidade()) {
				this.m1.novaEntrada(this.f1.novaSaida());
				this.f1.verificarDisponibilidade();
			}
		}
		
		public void transicaoMaquinaEsteira2() {
			if(this.e2.verificarDisponibilidade()) {
				this.e2.novaEntrada(this.m1.novaSaida());
				this.m1.verificarDisponibilidade();
			}
		}
		
		public void transicaoEsteira2Caminhao() {
			if(this.c1.verificarDisponibilidade()) {
				this.c1.novaEntrada(this.e2.novaSaida());
				this.e2.verificarDisponibilidade();
			}
		}
		
		public void transicaoCaminhaoTrabalhadores() {
			if(this.t1.verificarDisponibilidade()) {
				this.t1.novaEntrada(this.c1.novaSaida());
				this.c1.verificarDisponibilidade();
			}
		}
		
		public void transicaoTrabalhadoresEmpacotadora() {
			if(this.emp1.verificarDisponibilidade()) {
				this.emp1.novaEntrada(this.t1.novaSaida());
				this.t1.verificarDisponibilidade();
			}
		}
		
		public void transicaoEmpacotadoraPatio() {
			if(this.p1.verificarDisponibilidade()) {
				this.p1.novaEntrada(this.emp1.novaSaida());
				this.emp1.verificarDisponibilidade();
			}
		}
		
		public void executar() {
			this.entradaPecaEsteira();
			this.transicaoEsteiraForno();
//			this.transicaoFornoMaquina();
//			this.transicaoMaquinaEsteira2();
//			this.transicaoEsteira2Caminhao();
//			this.transicaoCaminhaoTrabalhadores();
//			this.transicaoTrabalhadoresEmpacotadora();
//			this.transicaoEmpacotadoraPatio();
		}
}