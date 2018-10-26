package model;

import java.util.ArrayList;
import java.util.List;

public class Maquina implements Runnable{
	private int id;
	private float capacidade;
	private int tempo;
	private float consumo;
	private String status;
	private int qntFila;
	private float quantidadeMaterial;
	private List<Peca> pecas;
	
	public Maquina() {
		this.id = 0;
		this.capacidade = 0;
		this.tempo = 0;
		this.consumo = 0;
		this.status = "Parado";
		this.qntFila = 0;
		this.quantidadeMaterial = 0;
		this.pecas = new ArrayList<Peca>();
	}
	
	public Maquina(int id, float capacidade, int tempo, float consumo) {
		this.id = id;
		this.capacidade = capacidade;
		this.tempo = tempo;
		this.consumo = consumo;
		this.status = "Parado";
		this.qntFila = 0;
		this.quantidadeMaterial = 0;
		this.pecas = new ArrayList<Peca>();
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
		if(this.pecas == null) {
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
	
	public void novaEntrada(float material) {
		System.out.println("Nova entrada na Máquina " + this.id + "!");
		this.quantidadeMaterial += material - 250;
		while(this.quantidadeMaterial >= 35) {
			this.pecas.add(new Peca(35, 1));
			this.setQntFila(this.getQntFila() + 1);
			this.quantidadeMaterial -= 35;
		}
	}
	
	public Peca novaSaida() {
		Peca peca = (Peca) this.pecas.get(0);
		this.setQntFila(this.getQntFila() - 1);
		this.pecas.remove(0);
		System.out.println("Saída na Máquina " + this.id + "!\tFila: " + this.getQntFila());
		return peca;
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