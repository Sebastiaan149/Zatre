package domein;

import java.util.ArrayList;

import gui.ScorebladScherm;
import resources.Taal;

/**
 * De {@code DomeinController} klasse ontvangt de operaties uit de UI die moeten worden uitgevoerd en beheert
 * het {@code domein} en dus bijgevolg het verloop van {@code Spel}.
 */
public class DomeinController {

	/**
	 * Houdt een instantie van {@code SpelerRepository} bij die nodig is om te connecteren met de database
	 * en wijzigingen door te voeren of informatie op te vragen.
	 */
	private SpelerRepository spelerrepository;
	
	/**
	 * Houdt de geselecteerde/ingelogde {@code Speler}s bij.
	 */
	private ArrayList<Speler> geselecteerdeSpelers;
	
	/**
	 * Houdt de scorebladen bij.
	 * Foutief...
	 */
	private ArrayList<ScorebladScherm> scorebladen;
	
	/**
	 * Houdt de ingelogde {@code Speler} bij.
	 */
	private Speler ingelogdeSpeler;
	
	/**
	 * Houdt het {@code Spel} bij.
	 */
	private Spel spel;

	/**
	 * Houdt een {@code Taal} instantie bij die nodig is voor de keuze tussen de talen
	 * en het vertalen van het programma. Wordt standaard ingesteld in het Nederlands.
	 */
	private Taal taal = new Taal("resources.nl_BE");

	/**
	 * Maakt een instantie van {@code DomeinController} en initialiseert de nodige velden.
	 */
	public DomeinController() {
		spelerrepository = new SpelerRepository();
		geselecteerdeSpelers = new ArrayList<>();
	}

	/**
	 * Registreert de {@code Speler} in de database.
	 * 
	 * @param gebruikersnaam de gebruikersnaam van de {@code Speler}.
	 * @param geboortejaar het geboortejaar van de {@code Speler}.
	 * @return de speelkansen van deze aangemaakte {@code Speler}.
	 */
	public int registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler speler = new Speler(gebruikersnaam, geboortejaar);
		spelerrepository.registreer(speler);
		return speler.getSpeelkansen();
	}

	/**
	 * Deze maakt de lijst met geselecteerde spelers weer leeg.
	 */
	public void leegGeselecteerdeSpelers() {
		this.geselecteerdeSpelers.clear();
	}

	/**
	 * Laat een {@code Speler} inloggen. Past bovendien de speelkansen aan.
	 * 
	 * @param gebruikersnaam het gebruikersnaam van de {@code Speler}.
	 * @param geboortejaar het geboortejaar van de {@code Speler}.
	 * @throws 	IllegalArgumentException wanneer het maximaal aantal toegelaten {@code Speler}s al is bereikt
	 * 			of de {@code Speler} al is ingelogd of als de {@code Speler} niet bestaat in de database
	 * 			of als de {@code Speler} geen speelkansen meer heeft.
	 */
	public void inloggen(String gebruikersnaam, int geboortejaar) {
		// Maximum aantal spelers is al bereikt
		if (this.geselecteerdeSpelers.size() == 4)
			throw new IllegalArgumentException(taal.vertaal("DomeinController_controleerAantalGeselecteerdeSpelers"));

		// Controle of deze speler al niet geselecteerd is (indien er al spelers zijn
		// geselecteerd)
		if (this.geselecteerdeSpelers.size() > 0) {
			for (Speler s : geselecteerdeSpelers) {
				if (s.getGebruikersnaam().equals(gebruikersnaam) && s.getGeboortejaar() == geboortejaar) {
					throw new IllegalArgumentException(taal.vertaal("foutmelding_DomeinController_selecteerSpeler_3"));
				}
			}
		}

		// Controle of de speler effectief bestaat in de databank
		ArrayList<Speler> spelers = spelerrepository.geefSpelers();
		Speler sp = null;

		for (Speler s : spelers) {
			if (s.getGebruikersnaam().equals(gebruikersnaam) && s.getGeboortejaar() == geboortejaar) {
				sp = s;
				break;
			}
		}

		// Controle als de speler bestaat in de databank
		if (sp == null)
			throw new IllegalArgumentException(taal.vertaal("foutmelding_DomeinController_selecteerSpeler_4"));
		// Controle als de speler nog speelkansen heeft
		if (sp.getSpeelkansen() < 1)
			throw new IllegalArgumentException(taal.vertaal("foutmelding_DomeinController_selecteerSpeler_5"));

		// verwijder speelkansen
		verwijderSpelerKansen(gebruikersnaam, geboortejaar);
		geselecteerdeSpelers.add(sp);
	}

	/**
	 * Controleert als het aantal ingelogde spelers kleiner is dan 2.
	 * 
	 * @throws IllegalArgumentException indien het aantal ingelogde {@code Speler}s kleiner is dan 2.
	 */
	public void controleerAantalGeselecteerdeSpelers() { //te klein
		if (geselecteerdeSpelers.size() < 2) {
			throw new IllegalArgumentException(vertaal("foutmelding_DomeinController_selecteerSpeler_6"));
		}
	}

	/**
	 * Vraagt de {@code String} representatie van alle {@code Speler}s op uit de database.
	 * 
	 * @return {@code String} van alle {@code Speler}s in de database.
	 */
	public String geefSpelersString() {
		ArrayList<String> spelersString = spelerrepository.geefSpelersString();
		String lijstSpelers = "";
		for (String s : spelersString) {
			lijstSpelers += s + "\n";
		}
		lijstSpelers += "\n";
		return lijstSpelers;
	}


	/**
	 * Vraag de geselecteerde {@code Speler}s op.
	 * 
	 * @return {@code ArrayList} van alle geselecteerde/ingelogde {@code Speler}s.
	 */
	public ArrayList<Speler> getGeselecteerdeSpelers() {
		return geselecteerdeSpelers;
	}

	/**
	 * Start een nieuw Zatre {@code Spel}.
	 */
	public void start() {
		this.spel = new Spel(this.getGeselecteerdeSpelers());
	}
	
	/**
	 * Verander de taal van het programma.
	 * 
	 * @param taal de taal waarin het programma moet worden ingesteld.
	 */
	public void veranderTaal(String taal) {
		this.taal.setTaal(taal);
	}

	/**
	 * Vertaal een stuk tekst in een specifieke taal.
	 * 
	 * @param tekst de parameter die moet worden gebruikt op een tekst te vertalen.
	 * @return de vertaalde tekst.
	 */
	public String vertaal(String tekst) {
		return this.taal.vertaal(tekst);
	}

	/**
	 * Vraagt de ingelogde {@code Speler} op.
	 * 
	 * @return de ingelogde {@code Speler}.
	 */
	public Speler geefIngelogdeSpeler() {
		return ingelogdeSpeler;
	}

	/**
	 * Vraag het huidige {@code Spel} object op.
	 * 
	 * @return het huidige {@code Spel}.
	 */
	public Spel getSpel() {
		return this.spel;
	}

	/**
	 * Vraag de stenen van de {@code Speler} op die aan de beurt is.
	 * 
	 * @return {@code ArrayList} van de steentjes van de {@code Speler} die aan de beurt is.
	 */
	public ArrayList<Integer> getSpelerStenen() {
		return this.spel.getSpelerStenen();
	}

	/**
	 * Schud de lijst van {@code Speler}s door elkaar.
	 */
	public void shuffleSpelerLijst() {
		this.spel.shuffleSpelerLijst();
	}

	/**
	 * Vraag alle {@code Speler}s op die meespelen in het {@code Spel}.
	 * 
	 * @return {@code ArrayList} van alle ingelogde {@code Speler}s.
	 */
	public ArrayList<Speler> getSpelersInSpel() {
		return this.spel.getSpelersInSpel();
	}

	/**
	 * Vraag de {@code Speler} op die aan de beurt is.
	 * 
	 * @return {@code Speler} die aan de beurt is.
	 */
	public Speler getSpelerAanBeurt() {
		return this.spel.getSpelerAanBeurt();
	}

	/**
	 * Selecteer een steen uit de steentjes van de huidige {@code Speler}.
	 * 
	 * @param waarde de waarde van de steen die is geselecteerd.
	 * @param index de index van de geselecteerde steen.
	 */
	public void setGekozenSteen(int waarde, int index) {
		this.spel.setGekozenSteen(waarde, index);
	}

	/**
	 * Kies een aantal willekeurige stenen uit {@code Steenpot}.
	 * 
	 * @param aantal het aantal steentjes die je uit de {@code Steenpot} wilt halen.
	 */
	public void krijgStenen(int aantal) {
		this.spel.krijgStenen(aantal);
	}

	/**
	 * Vraag het {@code Spelbord} op van {@code Spel}.
	 * 
	 * @return een tweedimensionale array van {@code Vakje}s die het {@code Spelbord} voorstelt.
	 */
	public Vakje[][] getVakjes() {
		return this.spel.getVakjes();
	}

	/**
	 * Controleert als het {@code Vakje} nog niet bezet is door een steen.
	 * 
	 * @param rijIndex de rij van het {@code Spelbord} waarop het steentje moet worden gelegd.
	 * @param kolomIndex de kolom van het {@code Spelbord} waarop het steentje moet worden gelegd.
	 * @return {@code true} indien dit steentje al bezet is.
	 */
	public boolean isAlGelegd(int rijIndex, int kolomIndex) {
		return this.spel.isAlGelegd(rijIndex, kolomIndex);
	}

	/**
	 * Vraag de index op van de geselecteerde steen.
	 * 
	 * @return index van geselecteerde steen.
	 */
	public int getGekozenSteenIndex() {
		return this.spel.getGekozenSteenIndex();
	}

	/**
	 * Vraag de waarde op van de geselecteerde steen.
	 * 
	 * @return waarde van geselecteerde steen.
	 */
	public int getGekozenSteen() {
		return this.spel.getGekozenSteen();
	}

	/**
	 * Leg de gekozen steen op het {@code Spelbord}. Maar nog niet officieel vastleggen in het domein (m.a.w. tijdelijk bijhouden).
	 * 
	 * @param rijIndex de rij van het {@code Spelbord} waarop het steentje moet worden gelegd.
	 * @param kolomIndex de kolom van het {@code Spelbord} waarop het steentje moet worden gelegd.
	 */
	public void houGelegdeSteenBij(int rijIndex, int kolomIndex) {
		this.spel.houGelegdeSteenBij(rijIndex, kolomIndex);
	}

	/**
	 * Verifieer als de gelegde stenen, correct zijn gelegd.
	 * 
	 * @throws IllegalArgumentException indien de steentjes foutief zijn gelegd.
	 */
	public void controleerGelegdeStenen() {
		this.spel.controleerGelegdeStenen();
	}

	/**
	 * Verwijder een speelkans van een {@code Speler}.
	 * 
	 * @param naam de gebruikersnaam van de {@code Speler}.
	 * @param jaar het geboortejaar van de {@code Speler}.
	 */
	public void verwijderSpelerKansen(String naam, int jaar) {
		spelerrepository.verwijderKans(naam, jaar);
	}

	/**
	 * Verwijder de gelegde steen uit de geselecteerde steentje van de {@code Speler}.
	 * 
	 * @param steenIndex de index van de te verwijderen steen.
	 */
	public void verwijderGelegdeSteen(int steenIndex) {
		this.spel.verwijderGelegdeSteen(steenIndex);
	}

	/**
	 * Bereken de score van de {@code Speler} die aan de beurt is.
	 */
	public void berekenScore() {
		this.spel.berekenScore();
	}

	/**
	 * Leg de stenen officieel vast op het {@code Spelbord}.
	 */
	public void legStenen() {
		this.spel.legStenen();
	}

	/**
	 * Stel de volgende {@code Speler} die aan de beurt is in.
	 */
	public void nextSpelerAanBeurt() {
		this.spel.nextSpelerAanBeurt();
	}

	/**
	 * Vraag het {@code Scoreblad} op.
	 * 
	 * @return {@code Scoreblad} van de {@code Speler} die aan de beurt is.
	 */
	public Scoreblad getScoreblad() {
		return this.spel.getScoreBlad();
	}

	/**
	 * Vraag de {@code Scorebladen} op van alle {@code Speler}s.
	 * 
	 * @return {@code ArrayList} van alle {@code Scoreblad}en van alle ingelogde {@code Speler}s.
	 */
	public ArrayList<Scoreblad> getScorebladen() {
		ArrayList<Scoreblad> scorebladen = new ArrayList<Scoreblad>();
		for (Speler speler : geselecteerdeSpelers) {
			scorebladen.add(speler.getScoreblad());
		}
		return scorebladen;
	}

	/**
	 * Vraag het aantal rijen van de geselecteerde {@code Speler} zijn/haar {@code Scoreblad} op.
	 * 
	 * @return aantal rijen van de geselecteerde {@code Speler} zijn/haar {@code Scoreblad}.
	 */
	public int getAantalRijenScoreblad() {
		return this.spel.getScoreBlad().getAantalRijen();
	}

	/**
	 * Vraag het aantal keer dat "x2" voorkomt bij de huidige {@code Speler} op.
	 * 
	 * @return aantal keer dat "x2" voorkomt bij de huidige {@code Speler}.
	 */
	public int getAantalx2() {
		return this.spel.getScoreBlad().getAantalx2();
	}

	/**
	 * Vraag het aantal keer op dat de som 10 voorkomt bij de huidige {@code Speler}.
	 * 
	 * @return aantal keer dat de som 10 voorkomt bij de huidige {@code Speler}.
	 */
	public ArrayList<String> getAantal10() {
		return this.spel.getScoreBlad().getAantal10();
	}

	/**
	 * Vraag het aantal keer op dat de som 11 voorkomt bij de huidige {@code Speler}.
	 * 
	 * @return aantal keer dat de som 11 voorkomt bij de huidige {@code Speler}.
	 */
	public ArrayList<String> getAantal11() {
		return this.spel.getScoreBlad().getAantal11();
	}

	/**
	 * Vraag het aantal keer op dat de som 12 voorkomt bij de huidige {@code Speler}.
	 * 
	 * @return aantal keer dat de som 12 voorkomt bij de huidige {@code Speler}.
	 */
	public ArrayList<String> getAantal12() {
		return this.spel.getScoreBlad().getAantal12();
	}

	/**
	 * Stel de {@code Scoreblad}en in.
	 * 
	 * @param scorebladen de scorebladen van de {@code Speler}s.
	 */
	public void setScorebladen(ArrayList<ScorebladScherm> scorebladen) {
		this.scorebladen = scorebladen;
	}

	/**
	 * Vraag de {@code Scoreblad}en op.
	 * 
	 * @return de {@code Scorebladen} van de {@code Speler}s.
	 */
	public ArrayList<ScorebladScherm> getScorenbladen() {
		return this.scorebladen;
	}

	/**
	 * Vraag of het einde van het {@code Spel} is bereikt.
	 * 
	 * @return {@code true} als het s{@code Spel} gedaan is.
	 */
	public boolean isEindeSpel() {
		return this.spel.isEindeSpel();
	}

	/**
	 * Beëindig het huidige {@code Spel}.
	 * Reset de gebruikte velden van het {@code Spel}.
	 */
	public void stopSpel() {
		this.spel = null;
		spelerrepository = new SpelerRepository();
		geselecteerdeSpelers = new ArrayList<>();
	}
	
	/**
	 * Geef de {@code Speler} (de winnaar) extra speelkansen.
	 * 
	 * @param naam gebruikersnaam van de winnaar.
	 * @param jaar geboortejaar van de winnaar.
	 */
	public void geefSpelerKansen(String naam, int jaar) {
		spelerrepository.geefKansen(naam, jaar);
	}
}
