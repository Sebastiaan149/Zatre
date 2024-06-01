package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import resources.Taal;

public class WelkomSchermController extends GridPane {

	private DomeinController dc;

	@FXML
	Label lblTaal;
	@FXML
	Button btnTaal;
	@FXML
	Button btnLogin;
	@FXML
	Button btnRegistreer;
	@FXML
	Label lblWelkom;

	public WelkomSchermController(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("WelkomScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Het welkomscherm kon niet geladen worden");
			System.out.println(e.getMessage());
		}
		vertaal();
	}

	@FXML
	private void loginStarten() {
		LoginSchermController loginScherm = new LoginSchermController(dc);

		Stage stage = new Stage();
		stage.setScene(new Scene(loginScherm));
		stage.setTitle(dc.vertaal("inloggen"));
		stage.show();
		getScene().getWindow().hide();
	}

	@FXML
	private void registererenStarten() {
		RegistratieSchermController registratieScherm = new RegistratieSchermController(dc);

		Stage stage = new Stage();
		stage.setScene(new Scene(registratieScherm));
		stage.setTitle(dc.vertaal("registreer"));
		stage.show();
		getScene().getWindow().hide();
	}

	@FXML
	public void veranderTaal() {
		if (btnTaal.getText().equals("Nederlands")) {
			dc.veranderTaal("resources.en_US");
		} else {
			dc.veranderTaal("resources.nl_BE");

		}
		vertaal();
	}

	private void vertaal() {
		lblTaal.setText(dc.vertaal("taal_woord"));
		btnTaal.setText(dc.vertaal("taal"));
		btnLogin.setText(dc.vertaal("inloggen"));
		btnRegistreer.setText(dc.vertaal("registreer"));
		lblWelkom.setText(dc.vertaal("begroeting"));
	}

}
