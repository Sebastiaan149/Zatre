package gui;

import java.util.ArrayList;

import domein.DomeinController;
import domein.Speler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SpelersScorebladenScherm extends VBox {
	private DomeinController dc;
	private HBox spelersNamen;
	private HBox spelerScoreBladen;
	
	public SpelersScorebladenScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		// spelerlijst van namen
		ArrayList<Speler> geselecteerdeSpelers = this.dc.getGeselecteerdeSpelers();
		spelersNamen = new HBox(20);
		spelersNamen.setMinWidth(250);

		for (Speler sp : geselecteerdeSpelers) {
			// Via Labels
			Label lbl = new Label(sp.getGebruikersnaam());
			lbl.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
			spelersNamen.getChildren().add(lbl);
		}
		SpelersScorebladenScherm.setMargin(spelersNamen, new Insets(10));
		spelersNamen.setPadding(new Insets(20));
		this.getChildren().add(spelersNamen);
		
		// scorebladen van spelers
		this.spelerScoreBladen = new HBox();
		for (Speler sp : geselecteerdeSpelers) {
			spelerScoreBladen.getChildren().add(new ScorebladScherm(sp.getScoreblad(), dc, sp.getGebruikersnaam()));
		}
		this.getChildren().add(spelerScoreBladen);
		
	}
}
