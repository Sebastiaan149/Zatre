package domein;

import java.util.ArrayList;
import java.util.Collections;

/**
 * De {@code Steenpot} klasse geeft een representatie weer van alle steentjes (waarde 1-6) die initieel aanwezig
 * zijn in het spel.
 * 
 * <p>De {@code Steenpot} instantie houdt een {@code ArrayList} bij van alle niet gelegde steentjes.
 * Voor deze klasse is het mogelijk om een aantal steentjes, die willekeurig worden gekozen,
 * op te vragen, zolang de {@code Steenpot} nog steentjes heeft.
 */
public class Steenpot {
	/**
	 * Houdt de volledige pot met beschikbare steentjes bij in een {@code ArrayList} van {@code Integer}.
	 */
	private ArrayList<Integer> beschikbareStenen;

	/**
	 * Maakt een nieuw {@code Steenpot} instantie.
	 * 
	 * <p>Vult de pot met steentjes: 21 steentjes met waarde 1 en 20 steentjes met waarde 1 t.e.m.
	 * 6, om een totaal te bekomen van 121.
	 */
	public Steenpot() {
		beschikbareStenen = new ArrayList<>();
		maakPot();
	}

	/*
	 * Vult de pot met 121 steentjes
	 */
	private void maakPot() {
		// 20 met waarde 2 t.e.m 6
		for (int i = 2; i < 7; i++) {
			for (int j = 0; j < 20; j++) {
				beschikbareStenen.add(i);
			}
		}

		// 21 met de waarde 1
		for (int i = 0; i < 21; i++) {
			beschikbareStenen.add(1);
		}
		Collections.shuffle(beschikbareStenen);
	}

	/**
	 * Kiest willekeurig aantal uit de pot en verwijdert deze nadien ook uit de pot.
	 * Zolang dat de pot niet leeg is.
	 * 
	 * @param aantal het aantal steentjes die je uit de pot wilt nemen.
	 * @return een {@code ArrayList} met de random gekozen steentjes van het type {@code Integer}.
	 */
	public ArrayList<Integer> getStenen(int aantal) {
		ArrayList<Integer> stenen = new ArrayList<>();
		// als er niet genoeg stenen meer zijn
		if (beschikbareStenen.size() < aantal) {
			if (beschikbareStenen.size() == 1) {
				stenen.add(beschikbareStenen.get(0));
				beschikbareStenen.remove(0);
				return stenen;
			} else // spel is gedaan
				return new ArrayList<>();
		} else { // normaal verloop
			for (int i = 1; i <= aantal; i++) {
				int index = (int) (Math.random() * beschikbareStenen.size());
				stenen.add(beschikbareStenen.get(index));
				beschikbareStenen.remove(index);
			}
		}
		return stenen;
	}

	/**
	 * Vraagt hoeveel steentjes er nog in de {@code Steenpot} zitten op.
	 * 
	 * @return aantal steentjes in de pot.
	 */
	public int getAantalStenen() {
		return beschikbareStenen.size();
	}
	
	/**
	 * Geeft mee als de {@code Steenpot} leeg is.
	 * 
	 * @return {@code true} indien de pot leeg is.
	 */
	public boolean isLeeg() {
		return this.beschikbareStenen.size() == 0;
	}
}
