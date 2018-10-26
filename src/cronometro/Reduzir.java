package cronometro;

import java.io.IOException;

public class Reduzir extends Cronometro{
	
	public Reduzir() {
		super();
	}
	
	public Reduzir(int dd, int hr, int mn, int sg){
		super(dd, hr, mn, sg);
	}

	public void run() {
		while(!super.getStatus().equals("Parado")) {
			try {
				
				Thread.sleep ((long) (1000 * (Double) super.getTempo()));
				super.modificarSegundo();
				super.atualizaTempo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}