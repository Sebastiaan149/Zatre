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

public class LoginSchermController extends GridPane {

	private DomeinController dc;

	@FXML
	Label lblTitel;
	@FXML
	Label lblnaam;
	@FXML
	TextField txtnaam;
	@FXML
	TextField txtjaar;
	@FXML
	Label lbljaar;
	@FXML
	Button btncancel;
	@FXML
	Button btninloggen;

	public LoginSchermController(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LoginScherm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			System.out.println("Het loginScherm kon niet geladen worden");
			System.out.println(e.getMessage());
		}
		vertaal();
	}

	private void vertaal() {
		lblTitel.setText(dc.vertaal("inlogScherm"));
		lblnaam.setText(dc.vertaal("gebruikersnaam"));
		lbljaar.setText(dc.vertaal("geboortejaar"));
		btncancel.setText(dc.vertaal("terug"));
		btninloggen.setText(dc.vertaal("inloggen"));

	}

	@FXML
	public void terug() {
		// leegt de huidige geselecteerde spelers (m.a.w. annuleert inloggen)
		this.dc.leegGeselecteerdeSpelers();
		WelkomSchermController welkomscherm = new WelkomSchermController(dc);

		Stage stage = new Stage();
		stage.setScene(new Scene(welkomscherm));
		stage.setTitle("Zatre");
		stage.show();
		getScene().getWindow().hide();
	}

	@FXML
	public void inloggen() {
		try {
			// Beide velden of een van beide is leeg
			if (txtnaam.getText().isBlank() || txtjaar.getText().isBlank()) {
				throw new IllegalArgumentException(dc.vertaal("foutmelding_DomeinController_selecteerSpeler_2"));
			}
			dc.inloggen(txtnaam.getText(), Integer.parseInt(txtjaar.getText()));

			SelecteerSpelerSchermHoofd selectieScherm = new SelecteerSpelerSchermHoofd(dc);
			Stage stage = new Stage();
			stage.setScene(new Scene(selectieScherm));
			stage.setTitle(dc.vertaal("selectieScherm"));
			stage.show();
			getScene().getWindow().hide();
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
	}

}
