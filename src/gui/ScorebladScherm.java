package gui;

import domein.DomeinController;
import domein.Scoreblad;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ScorebladScherm extends GridPane {
	private DomeinController dc;
	private Label[][] blad;
	private String gebruikersnaam;
	private Scoreblad scoreblad;

	public ScorebladScherm(Scoreblad scoreblad, DomeinController dc, String gebruikersnaam) {
		this.dc = dc;
		this.scoreblad = scoreblad;
		this.gebruikersnaam = gebruikersnaam;
		buildGui();
	}

	public void buildGui() {
		this.getChildren().clear();
		scoreblad.bepaalRijen();
		scoreblad.berekenTotaal();
		// Initialisatie van scoreblad voor aantal rijen met kruisje in
		this.blad = new Label[scoreblad.getAantalRijen() + 4][6];

		// Layout van de tabel bepalen
		this.setPadding(new Insets(10));
		this.setStyle("-fx-background-color: white");

		blad[0][0] = new Label(this.gebruikersnaam);
		this.add(blad[0][0], 0, 0, 5, 1);
		GridPane.setColumnSpan(blad[0][0], 5);
		
		blad[0][0].setPadding(new Insets(5));
		// rij met titels aanmaken
		blad[1][0] = new Label("x2");
		this.add(blad[1][0], 0, 1);
		
		blad[1][0].setPadding(new Insets(5));
		blad[1][1] = new Label("10 (1pt)");
		this.add(blad[1][1], 1, 1);
		
		blad[1][1].setPadding(new Insets(5));
		blad[1][2] = new Label("11 (2pt)");
		this.add(blad[1][2], 2, 1);
	
		blad[1][2].setPadding(new Insets(5));
		blad[1][3] = new Label("12 (4pt)");
		this.add(blad[1][3], 3, 1);

		blad[1][3].setPadding(new Insets(5));
		blad[1][4] = new Label("Bonus");
		this.add(blad[1][4], 4, 1);

		blad[1][4].setPadding(new Insets(5));
		blad[1][5] = new Label("totaal");
		this.add(blad[1][5], 5, 1);

		blad[1][5].setPadding(new Insets(5));

		for (int x = 6; x > 0; x--) {
			// kruisjes aanvullen bij x2
			for (int i = scoreblad.getAantalx2(); i > 0; i--) {
				blad[i+1][0] = new Label("X");
				this.add(blad[i+1][0], 0, i +1);

				blad[i+1][0].setPadding(new Insets(0));
			}	
			// Kruisjes voor 10 aanvullen
			for (int i = 0; i < scoreblad.getAantal10().size(); i++) {
				blad[i+1][1] = new Label(scoreblad.getAantal10().get(i));
				this.add(blad[i+1][1], 1, i + 2);

				blad[i+1][1].setPadding(new Insets(0));
			}
			// Kruisjes voor 11 aanvullen
			for (int i = 0; i < scoreblad.getAantal11().size(); i++) {
				blad[i+1][2] = new Label(scoreblad.getAantal11().get(i));
				this.add(blad[i+1][2], 2, i + 2);

				blad[i+1][2].setPadding(new Insets(0));
			}
			// Kruisjes voor 12 aanvullen
			for (int i = 0; i < scoreblad.getAantal12().size(); i++) {
				blad[i+1][3] = new Label(scoreblad.getAantal12().get(i));
				this.add(blad[i+1][3], 3, i + 2);

				blad[i+1][3].setPadding(new Insets(0));
			}
			// Bonus aanvullen
			for (int i = 0; i < scoreblad.getAantalRijen(); i++) {
				blad[i+1][4] = new Label(Integer.toString(i / 4 + 3));
				this.add(blad[i+1][4], 4, i + 2);

				blad[i+1][4].setPadding(new Insets(0));
			}
			for (int i = 0; i < scoreblad.getTotalen().size(); i++) {
				if(blad[i+2][5] != null)	
					blad[i+2][5].setText("");
				blad[i+2][5] = new Label(Integer.toString(scoreblad.getTotalen().get(i)));
				this.add(blad[i+2][5], 5, i + 2);
				blad[i+2][5].setPadding(new Insets(0));
			
				blad[scoreblad.getTotalen().size() + 3][5] = new Label(Integer.toString(scoreblad.getTotaleScore()));
				this.add(blad[scoreblad.getTotalen().size() + 3][5], 5 , scoreblad.getTotalen().size() + 3);
				blad[scoreblad.getTotalen().size() + 3][4] = new Label("score");
				this.add(blad[scoreblad.getTotalen().size() + 3][4], 4 , scoreblad.getTotalen().size() + 3);
				
			}
		}

	}

}