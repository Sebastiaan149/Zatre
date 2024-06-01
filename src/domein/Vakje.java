package domein;

/**
* Het {@code Vakje} klasse geeft een representatie van een vakje weer op het spelbord.
* 
* <p>Elk {@code Vakje} instantie houdt de {@code kleur}, de {@code score} (indien
* deze er is) en de waarde {@code bezet} bij, die aangeeft
* als een steentje met waarde {@code score} op dit vakje ligt.
*/
public class Vakje {
	/**
	 * Houdt bij als het een muur van een spelbord is. M.a.w. een {@code Vakje} waar je geen steen kan op leggen.
	 */
	private boolean muur;
	
	/**
	 * Houdt de kleur van het {@code Vakje} bij.
	 */
	private String kleur;
	
	/**
	 * Houdt de score van het {@code Vakje} bij. Waarde van 1 tot en met 6 als er een steentje op ligt.
	 */
	private int score;
	
	/**
	 * Houdt bij als het {@code Vakje} bezet is. M.a.w. als er al een steentje op het {@code Vakje} is gelegd.
	 */
	private boolean bezet;

	/**
	 * Maakt een nieuw {@code Vakje} object.
	 * 
	 * @param kleur geeft de kleur van het aan te maken {@code Vakje} mee.
	 * @param muur geeft aan of het {@code Vakje} een muur is.
	 */
	public Vakje(String kleur, boolean muur) {
		setMuur(muur);
		setKleur(kleur);
		this.bezet = false;
	}

	/**
	 * Returns {@code true} als het {@code Vakje} een muur is.
	 * 
	 * @return {@code true} als het {@code Vakje} een muur is.
	 */
	public boolean isMuur() {
		return muur;
	}

	/*
	 * Geef mee als dit {@code Vakje} een muur is.
	 */
	private void setMuur(boolean muur) {
		this.muur = muur;
	}

	/**
	 * Vraagt de kleur van dit {@code Vakje} op.
	 * 
	 * @return de {@code kleur} van het {@code Vakje}.
	 */
	public String getKleur() {
		return kleur;
	}

	/*
	 * Stel de kleur van dit {@code Vakje} in.
	 */
	private void setKleur(String kleur) {
		this.kleur = kleur;
	}

	/**
	 * Stel de {@code score} van dit {@code Vakje} in. M.a.w. er wordt een steentje op dit {@code Vakje} gelegd.
	 * Stelt bovendien in dat dit {@code Vakje} bezet is.
	 * 
	 * @param waarde de waarde van het steentje die op dit {@code Vakje} wordt gelegd.
	 */
	public void setScore(int waarde) {
		if (!this.isBezet()) {
			score = waarde;
			this.bezet = true;
		}
	}
	
	/**
	 * Vraagt de score op van dit {@code Vakje}.
	 * 
	 * @return de {@code score} van het steentje op dit {@code Vakje}.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Vraagt als dit {@code Vakje} bezet is door een steentje.
	 * 
	 * @return {@code true} als dit {@code Vakje} bezet is.
	 */
	public boolean isBezet() {
		return bezet;
	}
}
