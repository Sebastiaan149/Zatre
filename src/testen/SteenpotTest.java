package testen;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Steenpot;

public class SteenpotTest {

	Steenpot pot;

	@BeforeEach
	void setUp() throws Exception {
		pot = new Steenpot();
	}

	@Test
	public void maakPot_controleerSize_retourneert121() {
		Assertions.assertEquals(121, pot.getAantalStenen());
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3 })
	public void getStenen_controleerAantal_retourneertAantal(int aantal) {
		ArrayList<Integer> stenen = pot.getStenen(aantal);
		Assertions.assertEquals(aantal, stenen.size());
	}
}
