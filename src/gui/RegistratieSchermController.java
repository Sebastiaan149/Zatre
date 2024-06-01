package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistratieSchermController extends GridPane {

	private DomeinController dc;

	@FXML
	Label lblTitel;
	@FXML
	Label lblnaam;
	@FXML
	TextField txtnaam;
	@FXML
	TextField txtgeboortejaar;
	@FXML
	Label lbljaar;
	@FXML
	Button btncancel;
	@FXML
	Button btnregistreren;

	public RegistratieSchermController(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("RegistratieScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Het registratieScherm kon niet geladen worden");
			System.out.println(e.getMessage());
		}
		vertaal();

	}

	private void vertaal() {
		lblTitel.setText(dc.vertaal("registratieScherm"));
		lblnaam.setText(dc.vertaal("gebruikersnaam"));
		lbljaar.setText(dc.vertaal("geboortejaar"));
		btncancel.setText(dc.vertaal("terug"));
		btnregistreren.setText(dc.vertaal("registreer"));

	}

	@FXML
	public void terug() {
		WelkomSchermController welkomscherm = new WelkomSchermController(dc);

		Stage stage = new Stage();
		stage.setScene(new Scene(welkomscherm));
		stage.setTitle("Zatre");
		stage.show();
		getScene().getWindow().hide();
	}

	@FXML
	public void nieuweSpelerRegisteren() {
		try {
			// Beide velden zijn leeg
			if (txtnaam.getText().isBlank() && txtgeboortejaar.getText().isBlank()) {
				throw new IllegalArgumentException("foutmelding_Speler_legeVelden");
			}
			String naam = txtnaam.getText().toString();
			int jaar = txtgeboortejaar.getText().equals("") ? 0 : 
				Integer.parseInt(txtgeboortejaar.getText());
	
			int speelkansen = dc.registreerSpeler(naam, jaar);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(String.format(dc.vertaal("bevestiging_Speler_registratie"), naam, speelkansen));
			alert.showAndWait();
			terug();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(dc.vertaal("foutmelding_DomeinController_selecteerSpeler_1"));
			alert.showAndWait();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(dc.vertaal(e.getMessage()));
			alert.showAndWait();
		}
	}

}
