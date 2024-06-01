package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import domein.Vakje;

public class VakjeTest {

	@Test
	public void setScore_nietBezet_retourneertTrue() {
		Vakje vak = new Vakje("wit", false);
		vak.setScore(1);
		Assertions.assertTrue(vak.isBezet());
	}

	@Test
	public void setScore_IsAlGelegd_ScoreBlijftGelijk() {
		Vakje vak = new Vakje("wit", false);
		vak.setScore(1);
		vak.setScore(2);
		Assertions.assertEquals(1, vak.getScore());
	}

}
