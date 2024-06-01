package gui;

import java.util.ArrayList;

import domein.DomeinController;
import domein.Speler;
import domein.Vakje;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpelbordScherm extends GridPane {
	private DomeinController dc;
	private ImageView[][] bord;
	private SpeelSpelScherm spelscherm;
	private ArrayList<ScorebladScherm> scorebladen = new ArrayList<ScorebladScherm>();
	private Label spelerAanBeurt;
	private Label activiteit;
	private ScorebladenScherm scorebladenscherm;

	public SpelbordScherm(DomeinController dc, SpeelSpelScherm spelScherm, Label spelerAanBeurt, ArrayList<ScorebladScherm> scorebladen, ScorebladenScherm scorebladenScherm, Label activiteit) {
		this.dc = dc;
		this.bord = new ImageView[15][15];
		this.spelscherm = spelScherm;
		this.spelerAanBeurt = spelerAanBeurt;
		this.scorebladen = scorebladen;
		this.activiteit = activiteit;
		this.scorebladenscherm = scorebladenScherm;
		buildGui();
	}

	private void buildGui() {
		this.setHgap(0);
		this.setVgap(0);
		this.setAlignment(Pos.CENTER);
		this.setMaxWidth(800);
		this.setMaxHeight(800);
		
		// Spelbord aanmaken
		for (int i = 0; i <= 14; i++) {
			for (int j = 0; j <= 14; j++) {
				ImageView vakje = new ImageView();
				Vakje vak = this.dc.getVakjes()[i][j];
				// veranderen met CSS?
				if (vak.isMuur()) {
                    vakje.setImage(new Image(getClass().getResourceAsStream("/images/muurVakje.png")));
                } else if (vak.isBezet()) {
                    vakje.setImage(new Image(getClass().getResourceAsStream(String.format("/images/steen%d.png", vak.getScore()))));
                } else if (vak.getKleur().equals("grijs")) {
                    vakje.setImage(new Image(getClass().getResourceAsStream("/images/grijsVakje.png")));
                } else if (vak.getKleur().equals("wit")) {
                    vakje.setImage(new Image(getClass().getResourceAsStream("/images/zwartVakje.png")));
                } else {
                    // throw fout?
                }

				vakje.setPreserveRatio(true);
				vakje.setFitHeight(35);
				// klik voor vakje
				vakje.setOnMouseClicked(this::mouseClicked);
				
				this.add(vakje, j, i);
				this.setPadding(new Insets(20));
				this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

				// voegt vakje (ImageView) toe aan 2d-array bord
				this.bord[i][j] = vakje;
			}
		}
	}
	
	private void mouseClicked(MouseEvent e) {
		try {
			int kolomIndex = GridPane.getColumnIndex((Node) e.getTarget());
			int rijIndex = GridPane.getRowIndex((Node) e.getTarget());
			
			// als vakje niet bezet is EN als er een gekozen steen is
			if (!this.dc.getSpel().isAlGelegd(rijIndex, kolomIndex) && this.dc.getSpel().getGekozenSteenIndex() != -1) {
				// hou de gelegde steen bij in het domein, maar registreer het nog niet op het bord van het domein
				int steenIndex = this.dc.getSpel().getGekozenSteenIndex();
				this.dc.getSpel().houGelegdeSteenBij(rijIndex, kolomIndex);
				
				// steen is correct gelegd
				this.dc.getSpel().controleerGelegdeStenen();
				// gui aanpassen (steen leggen op bord)
				this.bord[rijIndex][kolomIndex].setImage(new Image(getClass()
						.getResourceAsStream(String.format("/images/steen%d.png", this.dc.getSpel().getGekozenSteen()))));
					
				// verwijder steen uit geselecteerde steentjes + GUI selectiesteentjes aanpassen;
				this.dc.getSpel().verwijderGelegdeSteen(steenIndex);
				this.spelscherm.getGeselecteerdeSteentjes().verwijderGeselecteerdeSteen(steenIndex);
					
				// als ALLE stenen correct zijn gelegd
				if (this.dc.getSpelerStenen().size() == 0) {
					//bereken de score
					this.dc.getSpel().berekenScore();
					// registreren in domein
					this.dc.getSpel().legStenen();
					// Scorebladen updaten
					scorebladenscherm.buildGui();
                    for (ScorebladScherm scorebladScherm : dc.getScorenbladen()) {
                            scorebladScherm.buildGui();
                    }
					this.spelscherm.getGeselecteerdeSteentjes().nieuweStenen();
					this.dc.getSpel().nextSpelerAanBeurt();
					this.spelerAanBeurt.setText(String.format(dc.vertaal("activiteitsmelding_spelerAanBeurt"), this.dc.getSpel().getSpelerAanBeurt().getGebruikersnaam()));
					this.activiteit.setText(dc.vertaal("activiteitsmelding"));
				}
			}
		} // steen is niet correct gelegd
		catch (IllegalArgumentException ex) {
	        if (this.dc.isEindeSpel()) {
		        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setHeaderText(null);
		        alert.setContentText(String.format(dc.vertaal(ex.getMessage()), this.dc.getSpel().getSpelerAanBeurt().getGebruikersnaam()));
		        alert.showAndWait();
		        
	        	this.dc.geefSpelerKansen(this.dc.getSpelerAanBeurt().getGebruikersnaam(), this.dc.getSpelerAanBeurt().getGeboortejaar());
	        	this.dc.stopSpel();
	    		Stage stage = new Stage();
	    		stage.setScene(new Scene(new WelkomSchermController(this.dc)));
	    		stage.show();
	    		getScene().getWindow().hide();
	        } else {
		        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setHeaderText(null);
		        alert.setContentText(dc.vertaal(ex.getMessage()));
		        alert.showAndWait();
		        
		        
	        	this.spelscherm.getGeselecteerdeSteentjes().buildGui();
	        	this.buildGui();
	        }
		}
	}

	public void openScoreBlad(Speler speler) {
		ScorebladScherm scoreblad = new ScorebladScherm(speler.getScoreblad(), dc, speler.getGebruikersnaam());
		Stage stage = new Stage();
		stage.setScene(new Scene(scoreblad));
		stage.setTitle(String.format("Scoreblad van %s", this.dc.getSpelerAanBeurt().getGebruikersnaam()));
		stage.show();
	}
}


