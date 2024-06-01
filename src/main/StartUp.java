package main;

import domein.DomeinController;
// import gui.SpeelSpelScherm;
// import gui.SpelbordScherm;
import gui.WelkomSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

//  APPLICATIE OPSTARTEN VIA UI
	
//	public static void main(String[] args) {
//
//		new UseCase1Applicatie(new DomeinController()).start();
//
//	}

	
//  APPLICATIE OPSTARTEN VIA GUI
	
	@Override
	public void start(Stage stage) {
		WelkomSchermController root = new WelkomSchermController(new DomeinController());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Zatre");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}