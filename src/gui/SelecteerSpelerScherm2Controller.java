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

public class SelecteerSpelerScherm2Controller extends GridPane {

	private DomeinController dc;
	SelecteerSpelerScherm1 boven;

	@FXML
	Button btnStart;
	@FXML
	Button btnTerug;
	@FXML
	Button btnVoegSpelerToe;
	@FXML
	Label lblNieuweSpelerGegevens;
	@FXML
	Label lblNieuwSpelerNaam;
	@FXML
	Label lblNieuwSpelerJaar;
	@FXML
	TextField txNaam;
	@FXML
	TextField txJaar;

	public SelecteerSpelerScherm2Controller(DomeinController dc, SelecteerSpelerScherm1 boven) {
		this.dc = dc;
		this.boven = boven;
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SelecteerSpelerScherm2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Het SelecteerScherm kon niet geladen worden");
			System.out.println(e.getMessage());
		}
		vertaal();
	}

	private void vertaal() {
		btnStart.setText(dc.vertaal("start"));
		btnTerug.setText(dc.vertaal("terug"));
		btnVoegSpelerToe.setText(dc.vertaal("voegToe"));
		lblNieuweSpelerGegevens.setText(dc.vertaal("gegevensAndereSpelers"));
		lblNieuwSpelerNaam.setText(dc.vertaal("gebruikersnaam"));
		lblNieuwSpelerJaar.setText(dc.vertaal("geboortejaar"));
	}

	@FXML
	public void start() {
		try {
			dc.controleerAantalGeselecteerdeSpelers();

			SpeelSpelScherm spelScherm = new SpeelSpelScherm(this.dc);
			Stage stage = new Stage();
			stage.setScene(new Scene(spelScherm));
			stage.setTitle("Zatre");
			stage.show();

			getScene().getWindow().hide();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	public void terug() {
		dc.leegGeselecteerdeSpelers();
		WelkomSchermController welkomscherm = new WelkomSchermController(dc);

		Stage stage = new Stage();
		stage.setScene(new Scene(welkomscherm));
		stage.setTitle("Zatre");
		stage.show();
		getScene().getWindow().hide();
	}

	@FXML
	public void spelerToevoegen() {
		try {
			// Beide velden of een van beide is leeg
			if (txNaam.getText().isBlank() || txJaar.getText().isBlank()) {
				throw new IllegalArgumentException(dc.vertaal("foutmelding_DomeinController_selecteerSpeler_2"));
			}
			dc.inloggen(txNaam.getText(), Integer.parseInt(txJaar.getText()));
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(dc.vertaal("foutmelding_DomeinController_selecteerSpeler_1"));
			alert.showAndWait();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		txNaam.clear();
		txJaar.clear();
		boven.update();
	}
}
