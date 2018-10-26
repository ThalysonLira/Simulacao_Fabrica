package cronometro;


import java.io.IOException;
import java.util.Observable;

import controller.FluxoController;

public class Cronometro extends Observable implements Runnable{
	private int dd, hr, mn, sg;
	private double tempo;
	private String status;
	
	public Cronometro(){
		this.dd = 0;
		this.hr = 0;
		this.mn = 0;
		this.sg = 0;
		this.status = "Parado";
	}
	
	public Cronometro(int dd, int hr, int mn, int sg){
		this.dd = dd;
		this.hr = hr;
		this.mn = mn;
		this.sg = sg;
		this.status = "Parado";
	}
	
	public void indicarTempo(double tempo){	
		this.setTempo(tempo);
	}
	
	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private void modificarDia () {
		this.dd++;
	}
	
	private void modificarHora () {
		if(this.hr < 23)
			this.hr++;
		else {
			this.hr = 0;
			this.modificarDia();
		}
	}

	private void modificarMinuto () {
		if(this.mn < 59)
			this.mn++;
		else {
			this.mn = 0;
			this.modificarHora();
		}
	}

	protected void modificarSegundo () {
		if(this.getStatus().equals("Executando")) {
			if(this.sg < 59)
				this.sg++;
			else {
				this.sg = 0;
				this.modificarMinuto();
			}
		}
	}
	
	private String mostrarTempoDias() {
		if(this.dd < 10)
			return (this.acrescentarZero() + this.dd + " : " + this.mostrarTempoHoras());
		else
			return (this.dd + " : " + this.mostrarTempoHoras());
	}
	
	private String mostrarTempoHoras() {
		if(this.hr < 10)
			return (this.acrescentarZero() + this.hr + " : " + this.mostrarTempoMinutos());
		else
			return (this.hr + " : " + this.mostrarTempoMinutos());
	}
	
	private String mostrarTempoMinutos() {
		if(this.mn < 10)
			return (this.acrescentarZero() + this.mn + " : " + this.mostrarTempoSegundos());
		else
			return (this.mn + " : " + this.mostrarTempoSegundos());
	}
	
	private String mostrarTempoSegundos() {
		if(this.sg < 10)
			return (this.acrescentarZero() + this.sg);
		else
			return (this.sg + "");
	}
	
	private String acrescentarZero() {
		return "0";
	}
	
	public String informarTempo() {
		if(this.dd > 0) {
			return this.mostrarTempoDias();	
		}
		else	
		if(this.hr > 0) {
			return this.mostrarTempoHoras();	
		}
		else		
		if(this.mn > 0) {
			return this.mostrarTempoMinutos();
		}
		else				
		if(this.sg > 0) {
			return this.mostrarTempoSegundos();
		}
		
		else
		return "00";
	}
	
	public int tempoTotal() {
		int tempoTotal = this.sg;
		tempoTotal += this.mn * 60;
		tempoTotal += this.hr * 3600;
		tempoTotal += this.dd * 86400;
		
		return tempoTotal;
	}
	
	public void atualizaTempo() {
        setChanged();
        notifyObservers();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}