package model;

import java.util.ArrayList;
import java.util.List;

public class Patio implements Runnable{
	private int id;
	private float capacidade;
	private String status;
	private int qntPecas;
	private List<Peca> pecas;
	
	public Patio() {
		this.id = 0;
		this.capacidade = 0;
		this.status = "Disponível";
		this.qntPecas = 0;
		this.pecas =  new ArrayList<Peca>();
	}
	
	public Patio(int id, float capacidade) {
		this.id = id;
		this.capacidade = capacidade;
		this.status = "Disponível";
		this.qntPecas = 0;
		this.pecas =  new ArrayList<Peca>();
	}

	public float getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
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
		if(this.pecas == null) {
			this.setStatus("Disponível");
			return true;
		}
		else if(this.getCapacidade() >= (this.getQntPecas() + 1) * this.pecas.get(0).getPeso()) {
			this.setStatus("Carregando");
			return true;
		}
		else
			this.setStatus("Aguardando - Cheia");
			return false;
	}
	
	public void novaEntrada(List<Peca> pacote) {
		System.out.println("Nova entrada de " + pacote.size() + " no Pátio " + this.id + "!\tPeças na Etapa: " + this.getQntPecas());
		if(pacote != null) {
			for(int i = 0; i < pacote.size(); i ++) {
				this.pecas.add(pacote.get(i));
				this.setQntPecas(this.getQntPecas() + 1);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}