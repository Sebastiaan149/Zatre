package gui;

import java.util.ArrayList;

import domein.DomeinController;
import domein.Speler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SpeelSpelScherm extends BorderPane {
	private SpelbordScherm spelbord;
	private DomeinController dc;
	private GridPane activiteitsMelding;
	private GeselecteerdeSteentjesScherm geselecteerdeSteentjes;
	private ScorebladenScherm scorebladenScherm;
	
	private ArrayList<ScorebladScherm> scorebladen = new ArrayList<ScorebladScherm>();
	
	public SpeelSpelScherm(DomeinController dc) {
		this.dc = dc;
		this.dc.start();
		buildGui();
	}

	public void buildGui() {
		// Titel
		Label titel = new Label("Zatre");
		titel.setPadding(new Insets(10, 0, 10, 0));
		titel.setFont(Font.font("Tahoma", FontWeight.BOLD, 50));
		this.setTop(titel);
		SpeelSpelScherm.setAlignment(titel, Pos.CENTER);
		
		//Spelerslijst schudden voor een willekeurige volgorde
		dc.shuffleSpelerLijst();
		
		// Activiteiten
		this.activiteitsMelding = new GridPane();
		Label spelerAanBeurt = new Label(String.format(dc.vertaal("activiteitsmelding_spelerAanBeurt"), 
				this.dc.getSpelerAanBeurt().getGebruikersnaam()));
		spelerAanBeurt.setFont(Font.font("Tahoma", 14));
		this.activiteitsMelding.add(spelerAanBeurt, 0, 0);
		Label activiteit = new Label(dc.vertaal("activiteitsmelding"));
		activiteit.setFont(Font.font("Tahoma", 14));
		this.activiteitsMelding.add(activiteit, 0, 5);
		
		this.activiteitsMelding.setPrefWidth(320);
		this.activiteitsMelding.setAlignment(Pos.CENTER);
		this.activiteitsMelding.setPadding(new Insets(0, 30, 0, 30));
		this.setLeft(this.activiteitsMelding);

		openScoreBlad();

		// steentjesvak
		this.geselecteerdeSteentjes = new GeselecteerdeSteentjesScherm(this.dc, activiteit);
		HBox bottom = new HBox();
		Region filler1 = new Region();
		HBox.setHgrow(filler1, Priority.ALWAYS);
		Region filler2 = new Region();
		HBox.setHgrow(filler2, Priority.ALWAYS);
		bottom.getChildren().addAll(filler1, this.geselecteerdeSteentjes, filler2);
		SpeelSpelScherm.setMargin(bottom, new Insets(5, 0, 50, 0));
		
		this.setBottom(bottom);
		
		// spelbord tonen
		this.spelbord = new SpelbordScherm(this.dc, this, spelerAanBeurt, this.scorebladen, this.scorebladenScherm, activiteit);
		this.setCenter(spelbord);
		SpeelSpelScherm.setMargin(spelbord, new Insets(10));
	}

	public void openScoreBlad() {
		ArrayList<Speler> spelersInSpel = this.dc.getSpel().getSpelersInSpel();
		for (Speler speler : spelersInSpel) {
			speler.maakScoreBlad();
			scorebladen.add(new ScorebladScherm(speler.getScoreblad(), this.dc, speler.getGebruikersnaam()));
		}
		this.dc.setScorebladen(scorebladen);
		scorebladenScherm = new ScorebladenScherm(scorebladen, this.dc);
		
		this.setRight(scorebladenScherm);
		SpeelSpelScherm.setMargin(scorebladenScherm, new Insets(10));
	}
	
	public GeselecteerdeSteentjesScherm getGeselecteerdeSteentjes() {
		return geselecteerdeSteentjes;
	}

	public GridPane getActiviteitsMelding() {
		return activiteitsMelding;
	}
	
	
}
