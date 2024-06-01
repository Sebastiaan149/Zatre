package testen;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Spel;
import domein.Speler;

public class SpelTest {

	ArrayList<Speler> spelersInSpel;

	@BeforeEach
	void setUp() throws Exception {
		spelersInSpel = new ArrayList<>();
		spelersInSpel.add(new Speler("testcase", 2001));
		spelersInSpel.add(new Speler("testcase", 2002));
		spelersInSpel.add(new Speler("testcase", 2003));
	}

	@Test
	public void nextSpelerAanBeurt_geeftVolgendeSpeler_retourneertVolgendeSpeler() {
		Spel spel = new Spel(spelersInSpel);
		spel.shuffleSpelerLijst();
		spel.nextSpelerAanBeurt();
		Assertions.assertEquals(spel.getSpelersInSpel().get(1), spel.getSpelerAanBeurt());
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3 })
	public void krijgStenen_controleerAantal_retourneertJuistAantal(int aantal) {
		Spel spel = new Spel(spelersInSpel);
		spel.krijgStenen(aantal);
		ArrayList<Integer> stenen = spel.getSpelerStenen();
		Assertions.assertEquals(aantal, stenen.size());
	}

}
