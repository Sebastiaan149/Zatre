package gui;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SelecteerSpelerScherm1 extends GridPane {

	private DomeinController dc;

	private Label[][] geselecteerdeSpelers;

	public SelecteerSpelerScherm1(DomeinController controller) {
		this.dc = controller;
		buildGui();
	}

	private void buildGui() {
		geselecteerdeSpelers = new Label[4][9];

		// SPELERS (gebruikersnaam, geboortejaar, speelkansen weergeven)
		for (int i = 0; i < 4; i++) {
			if (dc.getGeselecteerdeSpelers().size() < i + 1) {
				geselecteerdeSpelers[i][2] = new Label("");
				geselecteerdeSpelers[i][5] = new Label("");
				geselecteerdeSpelers[i][8] = new Label("");

				this.add(geselecteerdeSpelers[i][2], 2, i, 1, 1);
				this.add(geselecteerdeSpelers[i][5], 5, i, 1, 1);
				this.add(geselecteerdeSpelers[i][8], 8, i, 1, 1);
			} else {
				geselecteerdeSpelers[i][2] = new Label(dc.getGeselecteerdeSpelers().get(i).getGebruikersnaam());
				geselecteerdeSpelers[i][5] = new Label(
						String.format("%d", dc.getGeselecteerdeSpelers().get(i).getGeboortejaar()));
				geselecteerdeSpelers[i][8] = new Label(
						String.format("%d", dc.getGeselecteerdeSpelers().get(i).getSpeelkansen() - 1)); //-1 is zodat je ziet dat er al een beurt van is

				this.add(geselecteerdeSpelers[i][2], 2, i, 1, 1);
				this.add(geselecteerdeSpelers[i][5], 5, i, 1, 1);
				this.add(geselecteerdeSpelers[i][8], 8, i, 1, 1);
			}
		}

		// Labels voor Speler, Gebruikersnaam, Geboortejaar en Speelkansen
		for (int i = 0; i < 4; i++) // <1>
		{
			// "Spelerx: "
			geselecteerdeSpelers[i][0] = new Label(String.format("%s%d:    ", dc.vertaal("speler"), i + 1));
			this.add(geselecteerdeSpelers[i][0], 0, i, 1, 1);

			// "Gebruikersnaam:"
			geselecteerdeSpelers[i][1] = new Label(dc.vertaal("gebruikersnaam") + " ");
			this.add(geselecteerdeSpelers[i][1], 1, i, 1, 1);

			// "Geboortejaar:"
			geselecteerdeSpelers[i][4] = new Label(dc.vertaal("geboortejaar") + " ");
			this.add(geselecteerdeSpelers[i][4], 4, i, 1, 1);

			// "Speelkansen:"
			geselecteerdeSpelers[i][7] = new Label(dc.vertaal("speelkansen") + " ");
			this.add(geselecteerdeSpelers[i][7], 7, i, 1, 1);

			// Ruimte tussen de labels
			geselecteerdeSpelers[i][3] = new Label("	");
			geselecteerdeSpelers[i][6] = new Label("	");
			this.add(geselecteerdeSpelers[i][3], 3, i, 1, 1);
			this.add(geselecteerdeSpelers[i][6], 6, i, 1, 1);
		}
	}

	public void update() {
		// SPELERS (gebruikersnaam, geboortejaar, speelkansen weergeven)
		for (int i = 0; i < dc.getGeselecteerdeSpelers().size(); i++) {
			geselecteerdeSpelers[i][2].setText(dc.getGeselecteerdeSpelers().get(i).getGebruikersnaam());
			geselecteerdeSpelers[i][5]
					.setText(String.format("%d", dc.getGeselecteerdeSpelers().get(i).getGeboortejaar()));
			geselecteerdeSpelers[i][8]
					.setText(String.format("%d", dc.getGeselecteerdeSpelers().get(i).getSpeelkansen() - 1)); //-1 is zodat je ziet dat er al een beurt van is

		}
	}
}
