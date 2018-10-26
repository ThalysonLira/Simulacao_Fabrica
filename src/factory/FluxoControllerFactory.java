package factory;

import java.io.IOException;

import controller.FluxoController;
import javafx.fxml.FXMLLoader;

public class FluxoControllerFactory {
	public static FluxoController getInstance() throws IOException{
		FXMLLoader loader = new FXMLLoader(FluxoController.class.getResource("/view/Simulacao_Fabrica.fxml"));
		
		//Obtendo o Controlador
		FluxoController fc = loader.getController();
		
		return fc;
	}
}
