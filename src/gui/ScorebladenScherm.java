package gui;

import java.util.ArrayList;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class ScorebladenScherm extends GridPane {
	
	private ArrayList<ScorebladScherm> scorebladen;
	private ScorebladScherm[][] blad;
	private DomeinController dc;
	
	public ScorebladenScherm(ArrayList<ScorebladScherm> scorebladen, DomeinController dc) {
		this.dc = dc;
		this.scorebladen = scorebladen;
		buildGui();
	}
	
	public void buildGui() {
		this.getChildren().clear();
		switch(scorebladen.size()) {
		case 2 ->{ this.blad = new ScorebladScherm[2][2];
					blad[0][0] = scorebladen.get(0);
					this.add(blad[0][0], 0, 0);
					blad[0][0].setPadding(new Insets(5));
					blad[0][1] = scorebladen.get(1);
					this.add(blad[0][1], 1, 0);
					blad[0][1].setPadding(new Insets(5));
					}
		case 3 ->{ this.blad = new ScorebladScherm[2][2];
					blad[0][0] = scorebladen.get(0);
					this.add(blad[0][0], 0, 0);
					blad[0][0].setPadding(new Insets(5));
					blad[0][1] = scorebladen.get(1);
					this.add(blad[0][1], 1, 0);
					blad[0][1].setPadding(new Insets(5));
					blad[1][0] = scorebladen.get(2);
					this.add(blad[1][0], 0, 1);
					blad[1][0].setPadding(new Insets(5));
					}
		case 4 ->{ this.blad = new ScorebladScherm[2][2];
					blad[0][0] = scorebladen.get(0);
					this.add(blad[0][0], 0, 0);
					blad[0][0].setPadding(new Insets(5));
					blad[0][1] = scorebladen.get(1);
					this.add(blad[0][1], 1, 0);
					blad[0][1].setPadding(new Insets(5));
					blad[1][0] = scorebladen.get(2);
					this.add(blad[1][0], 0, 1);
					blad[1][0].setPadding(new Insets(5));
					blad[1][1] = scorebladen.get(3);
					this.add(blad[1][1], 1, 1);
					blad[1][1].setPadding(new Insets(5));
					}
		}
		
	}

	
	

}