package domein;

import java.time.LocalDate;
import java.util.Objects;

/**
* De {@code Speler} klasse geeft een representatie van een speler in het {@code Spel}.
* 
* <p>Elke {@code Speler} instantie houdt de {@code gebruikersnaam}, het {@code geboortejaar},
* de {@code speelkansen} en zijn/haar {@code scoreblad} bij.
*/
public class Speler {

	/**
	 * Houdt de gebruikersnaam bij van de speler.
	 */
	private String gebruikersnaam;
	
	/**
	 * Houdt het geboortejaar bij van de speler.
	 */
	private int geboortejaar;
	
	/**
	 * Houdt de resterende speelkansen bij van de speler.
	 */
	private int speelkansen;
	
	/**
	 * Houdt het scoreblad bij van de speler en wordt aangevuld gedurende het spel.
	 */
	private Scoreblad scoreblad;

	
	// CONSTRUCTORS
	/**
	 * Maakt een nieuw {@code Speler} object.
	 * 
	 * @param gebruikersnaam de gebruikersnaam van de speler.
	 * @param geboortejaar het geboortejaar van de speler.
	 * @param speelkansen de resterende speelkansen van de speler.
	 */
	public Speler(String gebruikersnaam, int geboortejaar, int speelkansen) {
		controleerSpeler(gebruikersnaam, geboortejaar);
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setSpeelkansen(speelkansen);
	}
	
	/**
	 * Maakt een nieuw {@code Speler} object met 5 speelkansen.
	 * 
	 * @param gebruikersnaam de gebruikersnaam van de speler.
	 * @param geboortejaar het geboortejaar van de speler.
	 */
	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 5);
	}
	
	// METHODES
	/*
	 * Verifieert als de gebruikersnaam en het geboortejaar geldige waarden hebben.
	 */
	private void controleerSpeler(String naam, int jaar) {
		// gebruikersnaam moet minstens 5 karakters hebben
		if (naam.length() < 5 || naam.isBlank() || naam.isEmpty())
			throw new IllegalArgumentException("foutmelding_Speler_setGebruikersnaam");
		// ongeldige geboortejaar
		if (jaar <= 0)
			throw new IllegalArgumentException("foutmelding_Speler_setGeboortejaar_1");
		// speler moet minstens 6 jaar zijn of 6 worden
		if (jaar > (LocalDate.now().getYear() - 6))
			throw new IllegalArgumentException("foutmelding_Speler_setGeboortejaar_2");
	}
	
	/**
	 * Vraagt de gebruikersnaam van dit {@code Speler} object op.
	 * 
	 * @return gebruikersnaam van deze {@code Speler}.
	 */
	public String getGebruikersnaam() {
		return this.gebruikersnaam;
	}

	/*
	 * Stelt de gebruikersnaam in.
	 */
	private void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * Vraagt het geboortejaar van dit {@code Speler} object op.
	 * 
	 * @return geboortejaar van deze {@code Speler}.
	 */
	public int getGeboortejaar() {
		return this.geboortejaar;
	}

	/*
	 * Stelt het geboortejaar in.
	 */
	private void setGeboortejaar(int geboortejaar) {
		this.geboortejaar = geboortejaar;
	}

	/**
	 * Vraagt de resterende speelkansen van dit {@code Speler} object op.
	 * 
	 * @return speelkansen van deze {@code Speler}.
	 */
	public int getSpeelkansen() {
		return this.speelkansen;
	}

	/**
	 * Stelt de resterende speelkansen in.
	 * 
	 * @param speelkansen resterende speelkansen van deze {@code Speler}.
	 */
	public void setSpeelkansen(int speelkansen) {
		this.speelkansen = speelkansen;
	}

	/**
	 * Maakt een nieuw {@code Scoreblad} instantie aan voor deze {@code Speler}.
	 */
	public void maakScoreBlad() {
		scoreblad = new Scoreblad();
	}

	/**
	 * Vraagt het {@code Scoreblad} op van deze {@code Speler}.
	 * 
	 * @return {@code Scoreblad} van deze {@code Speler}.
	 */
	public Scoreblad getScoreblad() {
		return scoreblad;
	}

	/**
	 * Retourneert de hash code van dit {@code Speler} object.
	 * 
	 * @return hash code van deze {@code Speler}.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(geboortejaar, gebruikersnaam);
	}

	/**
	 * Vergelijkt dit {@code Speler} object met een ander {@code Speler} {@code obj}.
	 * 
	 * @param obj een ander {@code Speler} object.
	 * @return {@code true} als deze {@code Speler} gelijk is aan {@code Speler} {@code obj.}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speler other = (Speler) obj;
		return geboortejaar == other.geboortejaar && Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}
}