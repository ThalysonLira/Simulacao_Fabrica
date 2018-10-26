package model;

import java.util.ArrayList;
import java.util.List;

public class Caminhao implements Runnable{
	private int id;
	private float capacidade;
	private int tempoCheio;
	private int tempoVazio;
	private float consumo;
	private String status;
	private int qntPecas;
	private String local;
	private List<Peca> pecas;
	
	public Caminhao() {
		this.id = 0;
		this.capacidade = 0;
		this.tempoCheio = 0;
		this.tempoVazio = 0;
		this.status = "Disponível";
		this.qntPecas = 0;
		this.local = "Fábrica";
		this.pecas =  new ArrayList<Peca>();
	}
	
	public Caminhao(int id, float capacidade, float tempoCheio, float tempoVazio, float consumo) {
		this.id = id;
		this.capacidade = capacidade;
		this.tempoCheio = 0;
		this.tempoVazio = 0;
		this.consumo = consumo;
		this.status = "Disponível";
		this.qntPecas = 0;
		this.local = "Fábrica";
		this.pecas =  new ArrayList<Peca>();
	}

	public float getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}

	public int getTempoCheio() {
		return tempoCheio;
	}

	public void setTempoCheio(int tempoCheio) {
		this.tempoCheio = tempoCheio;
	}

	public int getTempoVazio() {
		return tempoVazio;
	}

	public void setTempoVazio(int tempoVazio) {
		this.tempoVazio = tempoVazio;
	}

	public float getConsumo() {
		return consumo;
	}

	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQntPecas() {
		return qntPecas;
	}

	public void setQntPecas(int qntPecas) {
		this.qntPecas = qntPecas;
	}
	
	public boolean verificarDisponibilidade() {
		if((this.pecas == null) && (this.local.equals("Fábrica"))) {
			this.setStatus("Disponível");
			return true;
		}
		else if((this.pecas == null) && (this.local.equals("Empacotadora"))) {
			this.setStatus("Retornando");
			return false;
		}		
		else if((this.getCapacidade() >= (this.getQntPecas() + 1) * this.pecas.get(0).getPeso()) && (this.local.equals("Fábrica"))) {
				this.setStatus("Carregando");
				return true;
		}
		else if((this.getCapacidade() >= (this.getQntPecas() + 1) * this.pecas.get(0).getPeso()) && (this.local.equals("Empacotadora"))) {
			this.setStatus("Descarregando");
			return true;
		}			
		else {
			this.setStatus("Transportando");
			
			if(this.local.equals("Empacotora"))
				this.local = "Fábrica";
			else
				this.local = "Empacotora";
		}
			return false;
	}
	
	public void novaEntrada(Peca peca) {
		this.pecas.add(peca);
		this.setQntPecas(this.getQntPecas() + 1);
		System.out.println("Nova entrada no Caminhão " + this.id + "!\tFila: " + this.getQntPecas());
	}
	
	public Peca novaSaida() {
		Peca peca = (Peca) this.pecas.get(0);
		this.setQntPecas(this.getQntPecas() - 1);
		this.pecas.remove(0);
		System.out.println("Saída no Caminhão " + this.id + "!\tRestante: " + this.getQntPecas());
		return peca;
	}

	@Override
	public void run() {
//		// TODO Auto-generated method stub
//		try {
//			Thread.sleep(this.getTempo());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}