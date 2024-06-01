package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DomeinController;
import domein.Spel;
import domein.Spelbord;

class SpeelZatreTest {
	DomeinController dc;

	@BeforeEach
	void setUp() throws Exception {
		dc = new DomeinController();
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 5 })
	public void setSpelbord_aantalGeselecteerdeSpelersNietOk_retourneertFalse(int tel) {

		switch (tel) {
		case 1:
			dc.inloggen("Gunar", 2003);
			break;
		case 5:
			dc.inloggen("Gunar", 2003);
			dc.inloggen("Franky", 1777);
			dc.inloggen("Jarne", 2003);
			dc.inloggen("Sebastiaan", 2002);
			dc.inloggen("Jarne", 2002);
			break;
		}
	}

}
