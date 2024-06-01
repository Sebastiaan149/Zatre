package gui;

import java.util.ArrayList;

import domein.DomeinController;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GeselecteerdeSteentjesScherm extends GridPane {
	private DomeinController dc;
	private ArrayList<ImageView> imagesGeselecteerdeSteentjes;
	private Label activiteit;

	public GeselecteerdeSteentjesScherm(DomeinController dc, Label activiteit) {
		this.dc = dc;
		// eerste speler krijgt 3 stenen
		this.activiteit = activiteit;
		this.dc.krijgStenen(3);
		this.imagesGeselecteerdeSteentjes = new ArrayList<>();
		buildGui();
	}

	// Deze methode wordt meerdere keren opgeroepen wanneer geselecteerde steentjes op zijn
	public void buildGui() {
		int kolomIndex = 0;
		for (int steen : this.dc.getSpelerStenen()) {
			ImageView steenImage = new ImageView(new Image(getClass().getResourceAsStream(String.format("/images/steen%d.png", steen))));
			steenImage.setPreserveRatio(true);
			steenImage.setFitWidth(60);
			
			steenImage.setOnMouseClicked(this::selecteerSteen);
			this.add(steenImage, kolomIndex, 0);
			this.imagesGeselecteerdeSteentjes.add(steenImage);
			kolomIndex++;
		}
		
	}
	
	private void selecteerSteen(MouseEvent e) {
		int imageGeselecteerdeSteenIndex = GridPane.getColumnIndex((Node) e.getTarget());
		int waarde = this.dc.getSpelerStenen().get(imageGeselecteerdeSteenIndex);
		this.activiteit.setText(String.format(dc.vertaal("activiteitsmelding_geselecteerd"), waarde));
		this.dc.setGekozenSteen(waarde, imageGeselecteerdeSteenIndex);
	}
	
	public void nieuweStenen() {
		this.getChildren().clear();
		this.imagesGeselecteerdeSteentjes = new ArrayList<ImageView>();
		this.dc.krijgStenen(2);
		buildGui();
	}

	

	public void verwijderGeselecteerdeSteen(int steenIndex) {
		this.getChildren().clear();
		// op zich overbodig?
		this.imagesGeselecteerdeSteentjes.remove(steenIndex);
		buildGui();
	}
}
