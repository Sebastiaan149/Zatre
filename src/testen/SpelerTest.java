package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;

class SpelerTest {

	@ParameterizedTest
	@ValueSource(ints = { 2002, 2000, 1990, 2016 })
	public void setGeboortejaar_ouderDan6_returnTrue(int geboortejaar) {
		Speler speler = new Speler("Testnaam", geboortejaar);
		Assertions.assertEquals(geboortejaar, speler.getGeboortejaar());
	}

	@ParameterizedTest
	@ValueSource(ints = { 2020, 2021, 2019, 2017 })
	public void setGeboortejaar_jongerDan6_returnFalse(int geboortejaar) {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Speler("Testnaam", geboortejaar));
	}

	@Test
	public void setGebruikersnaam_4chars_returnFalse() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Speler("naam", 2002));
	}

	@Test
	public void setGebruikersnaam_5chars_returnTrue() {
		Speler speler = new Speler("namen", 2002);
		Assertions.assertEquals("namen", speler.getGebruikersnaam());
	}

	@Test
	public void setGebruikersnaam_6chars_returnTrue() {
		Speler speler = new Speler("nammen", 2002);
		Assertions.assertEquals("nammen", speler.getGebruikersnaam());
	}

}
