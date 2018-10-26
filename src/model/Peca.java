package model;

public class Peca {
	private int peso;
	private int segEntrada;
	
	public Peca(int peso, int seg) {
		this.peso = peso;
		this.segEntrada = seg;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getSegEntrada() {
		return segEntrada;
	}

	public void setSegEntrada(int segEntrada) {
		this.segEntrada = segEntrada;
	}
}
