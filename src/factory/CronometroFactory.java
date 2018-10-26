package factory;

import java.io.IOException;

import controller.CronometroController;
import cronometro.Cronometro;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CronometroFactory {
	public static CronometroController getInstance() throws IOException{
		FXMLLoader loader = new FXMLLoader(CronometroFactory.class.getResource("/view/Tela_Tempo.fxml"));
		Parent root = loader.load();
		
		//Obtendo o Controlador
		CronometroController cc = loader.getController();
		cc.setParent(root);
		
		return cc;
	}
}