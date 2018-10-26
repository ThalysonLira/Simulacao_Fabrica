package model;

import java.util.ArrayList;
import java.util.List;

public class Empacotadora implements Runnable{
	private int id;
	private float capacidade;
	private int tempo;
	private float consumo;
	private String status;
	private int qntPecas;
	private List<Peca> pecas;
	
	public Empacotadora() {
		this.id = 0;
		this.capacidade = 0;
		this.tempo = 0;
		this.consumo = 0;
		this.status = "Disponível";
		this.qntPecas = 0;
		this.pecas =  new ArrayList<Peca>();
	}
	
	public Empacotadora(int id, float capacidade, int tempo, float consumo) {
		this.id = id;
		this.capacidade = capacidade;
		this.tempo = tempo;
		this.consumo = consumo;
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
	
	public void novaEntrada(List<Peca> pecas) {
		for(int i = 0; i < pecas.size(); i++) {
			this.pecas.add(pecas.get(i));
			this.setQntPecas(this.getQntPecas() + 1);
		}
		System.out.println("Nova entrada na Empacotadora " + this.id + "!\tPeças na Etapa: " + this.getQntPecas());
	}

	public List<Peca> novaSaida() {
		if(this.getQntPecas() >= 50) {
			int saida = 50;
			List<Peca> pacote = new ArrayList<Peca>();
		
			this.setQntPecas(this.getQntPecas() - saida);
			
			for(int i = 0; i < saida; i++) {
				pacote.add(this.pecas.get(0));
				this.pecas.remove(this.pecas.get(0));
			}
		
			System.out.println("Saída de " + saida + " peças na Empacotadora " + this.id + "!\tRestante: " + this.getQntPecas());
			return pacote;
		}
		else
			return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(this.getTempo());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}