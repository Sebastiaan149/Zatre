package testen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import domein.Speler;

class SpelerSelectieTest {
	DomeinController dc = new DomeinController();

	@ParameterizedTest
	@ValueSource(ints = { 2001, 2002, 2003 })
	void selecteerSpeler_correcteGeselecteerdeSpeler_returnTrue(int geboortejaar) {
		dc.registreerSpeler("testcase", geboortejaar);
		dc.inloggen("testcase", geboortejaar);

		ArrayList<Speler> geselecteerdeSpelers = dc.getGeselecteerdeSpelers();
		assertTrue(geselecteerdeSpelers.get(0).getGebruikersnaam().equals("testcase")
				&& geselecteerdeSpelers.get(0).getGeboortejaar() == geboortejaar);
	}
}
