package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	private static Stage primaryStage;
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		AnchorPane root = FXMLLoader.load(getClass().getResource("/view/Simulacao_Fabrica.fxml"));
		
		Scene scene = new Scene(root, 1224, 749);
		
		primaryStage.setTitle("Simulação Fábrica");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}