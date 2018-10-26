package model;

import java.util.ArrayList;
import java.util.List;

public class Trabalhadores implements Runnable{
	private int id;
	private float capacidade;
	private int quantTrabalhadores;
	private int tempoCarga;
	private int tempoTransporte;
	private float consumo;
	private float valorDiaria;
	private String status;
	private int qntFila;
	private List<Peca> pecas;
	
	public Trabalhadores() {
		this.id = 0;
		this.capacidade = 0;
		this.quantTrabalhadores = 0;
		this.tempoCarga = 0;
		this.tempoTransporte = 0;
		this.consumo = 0;
		this.valorDiaria = 0;
		this.status = "Dispon�vel";
		this.qntFila = 0;
		this.pecas =  new ArrayList<Peca>();
	}
	
	public Trabalhadores(int id, float capacidade, int quantTrabalhadores, int tempoC, int tempoT, float consumo, float valorDiaria) {
		this.id = id;
		this.capacidade = capacidade;
		this.quantTrabalhadores = quantTrabalhadores;
		this.tempoCarga = tempoC;
		this.tempoTransporte = tempoT;
		this.consumo = consumo;
		this.valorDiaria = valorDiaria;
		this.status = "Dispon�vel";
		this.qntFila = 0;
		this.pecas =  new ArrayList<Peca>();
	}

	public float getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}

	public int getQuantTrabalhadores() {
		return quantTrabalhadores;
	}

	public void setQuantTrabalhadores(int quantTrabalhadores) {
		this.quantTrabalhadores = quantTrabalhadores;
	}

	public int getTempoCarga() {
		return tempoCarga;
	}

	public void setTempo(int tempoC) {
		this.tempoCarga = tempoC;
	}

	public int getTempoTransporte() {
		return tempoTransporte;
	}

	public void setTempoTransporte(int tempoTransporte) {
		this.tempoTransporte = tempoTransporte;
	}

	public float getConsumo() {
		return consumo;
	}

	public void setConsumo(float consumo) {
		this.consumo = consumo;
	}

	public float getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(float valorDiaria) {
		this.valorDiaria = valorDiaria;
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
			this.setStatus("Dispon�vel");
			return true;
		}
		else if(this.getCapacidade() * this.getQuantTrabalhadores() >= (this.getQntFila() + 1) * this.pecas.get(0).getPeso()) {
			this.setStatus("Carregando");
			return true;
		}
		else
			this.setStatus("Transportando");
			return false;
	}
	
	public void novaEntrada(Peca peca) {
		this.pecas.add(peca);
		this.setQntFila(this.getQntFila() + 1);
		System.out.println("Nova entrada no Trabalhador " + this.id + "!\tPecas: " + this.getQntFila());
	}
	
	public List<Peca> novaSaida() {
		List<Peca> carga = new ArrayList<Peca>();
		
		for(int i = 0; i < this.getQntFila(); i++) {
			carga.add(this.pecas.get(0));
			this.pecas.remove(this.pecas.get(0));
		}
		
		System.out.println("Sa�da de " + this.getQntFila() + " pe�as nos Trasportadores " + this.id + "!\tRestante: " + this.getQntFila());
		this.setQntFila(0);
		return carga;
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