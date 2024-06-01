package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class SelecteerSpelerSchermHoofd extends BorderPane {

	private DomeinController dc;
	SelecteerSpelerScherm2Controller onder;
	SelecteerSpelerScherm1 boven;

	public SelecteerSpelerSchermHoofd(DomeinController dc) {
		this.dc = dc;
		buildGui();
	}

	private void buildGui() {
		boven = new SelecteerSpelerScherm1(dc);
		this.setTop(boven);

		onder = new SelecteerSpelerScherm2Controller(dc, boven);
		this.setBottom(onder);
		SelecteerSpelerSchermHoofd.setMargin(boven, new Insets(20));
	}

}
