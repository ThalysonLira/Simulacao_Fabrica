package model;

import java.util.ArrayList;
import java.util.List;

public class Forno implements Runnable{
	private int id;
	private float capacidade;
	private int tempo;
	private float consumo;
	private String status;
	private int qntFila;
	private List<Peca> pecas;
	
	public Forno() {
		this.id = 0;
		this.capacidade = 0;
		this.tempo = 0;
		this.consumo = 0;
		this.status = "Parado";
		this.qntFila = 0;
		this.pecas =  new ArrayList<Peca>();
	}
	
	public Forno(int id, float capacidade, int tempo, float consumo) {
		this.id = id;
		this.capacidade = capacidade;
		this.tempo = tempo;
		this.consumo = consumo;
		this.status = "Parado";
		this.qntFila = 0;
		this.pecas =  new ArrayList<Peca>();
	}

	public float getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
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

	public int getQntFila() {
		return qntFila;
	}

	public void setQntFila(int qntFila) {
		this.qntFila = qntFila;
	}
	
	public boolean verificarDisponibilidade() {
		if((this.pecas == null) || (this.pecas.isEmpty())) {
			this.setStatus("Disponível");
			return true;
		}
		else if(this.getCapacidade() >= (this.getQntFila() + 1) * this.pecas.get(0).getPeso()) {
			this.setStatus("Carregando");
			return true;
		}
		else
			this.setStatus("Executando");
			return false;
	}
	
	public void novaEntrada(Peca peca) {
		this.pecas.add(peca);
		this.setQntFila(this.getQntFila() + 1);
		System.out.println("Nova entrada no Forno " + this.id + "!\tFila: " + this.getQntFila());
	}
	
	public Float novaSaida() {
		this.setQntFila(0);
		this.pecas.removeAll(pecas);
		System.out.println("Saída no Forno " + this.id + "!\tFila: " + this.getQntFila());
		return this.getCapacidade();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.getStatus().equals("Parado")) {
		try {
			Thread.sleep(this.getTempo());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
}