package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
* Het {@code Spel} klasse geeft een representatie van het actieve spel Zatre.
* 
* <p>Elk {@code Spel} houdt verschillende velden bij die nodig zijn voor het verloop van Zatre.
* {@code speleraanbeurt} is de huidige {@code Speler} die aan de beurt is. {@code spelersInSpel}
* is een {@code ArrayList} die de spelers bijhoudt in het huidige spel. Daarnaast houdt {@code beschikbareStenen}
* de {@code Steenpot} bij met de beschikbare stenen, {@code spelbord} een {@code Spelbord} instantie en
* {@code isEindeSpel} houdt bij als het einde van Zatre is bereikt.
* 
* <p>Ten slotte zijn er nog enkele velden met waarden die nodig zijn voor het verloop van stenen leggen, controleren
* van de gelegde stenen en nieuwe stenen kiezen.
*/
public class Spel {

	/**
	 * Houdt de huidige speler bij die aan de beurt is
	 */
	private Speler spelerAanBeurt;
	
	/**
	 * Houdt een {@code ArrayList} van {@code Speler}s bij die actief zijn in het spel.
	 */
	private ArrayList<Speler> spelersInSpel;
	
	/**
	 * Houdt de {@code Steenpot} bij met de overige beschikbare stenen.
	 */
	private Steenpot beschikbareStenen;
	
	/**
	 * Houdt het {@code Spelbord} bij van {@code Spel}.
	 */
	private Spelbord spelbord;
	
	/**
	 * Houdt bij als het einde van het {@code Spel} al is bereikt.
	 */
	private boolean isEindeSpel = false;

	/** 
	 * Houdt een {@code ArrayList} van {@code Integer}s bij.
	 * Deze dient als een back-up om na een fout gelegde steen, terug dezelfde
	 * volgorde van geselecteerde stenen op te vragen.
	 */
	private ArrayList<Integer> spelerStenenBackup;
	
	/**
	 * Houdt een {@code ArrayList} van {@code Integer}s bij.
	 * Dit is een "actieve" representatie van de geselecteerde stenen.
	 */
	private ArrayList<Integer> spelerStenen;
	
	/**
	 * Houdt de waarde van de geselecteerde steen bij.
	 */
	private int gekozenSteen;
	
	/**
	 * Houdt de index van de geselecteerde index bij.
	 */
	private int gekozenSteenIndex = -1;
	
	/**
	 * Houdt een tweedimensionale matrix bij met de stenen die gelegd zijn.
	 * Per rij worden de waarden {@code rij} van het {@code Vakje} waarop de steen is gelegd,
	 * {@code kolom} van het {@code Vakje} waarop de steen is gelegd,
	 * en {@code waarde} van het gelegde steentje bijgehouden.
	 */
	private int[][] beurtTijdelijkeGelegdeStenen;

	/**
	 * Maakt een nieuw {@code Spel} object aan.
	 * 
	 * @param geselecteerdeSpelers een {@code ArrayList} met de {@code Speler}s die deelnemen aan het {@code Spel}.
	 */
	public Spel(ArrayList<Speler> geselecteerdeSpelers) {
		this.spelersInSpel = geselecteerdeSpelers;
		this.spelbord = new Spelbord();
		this.beschikbareStenen = new Steenpot();

		this.spelerStenenBackup = new ArrayList<>();
		this.spelerStenen = new ArrayList<>();
		this.beurtTijdelijkeGelegdeStenen = new int[3][3];

		for (Speler speler : geselecteerdeSpelers) {
			speler.maakScoreBlad();
		}
	}

	/*
	 * Wordt uitgevoerd wanneer het einde van het {@code Spel} is bereikt.
	 * Zo wordt de winnaar van de {@code spelersInSpel} bepaald en wordt de waarde
	 * van {@code isEindeSpel} gezet op {@code true}.
	 */
	private void eindeSpel() {
		int hoogste = -1;
		for (Speler speler : spelersInSpel) {
			int spelerTottaal = speler.getScoreblad().getTotaleScore();
			
			
			if (hoogste < spelerTottaal) {
				hoogste = spelerTottaal;
				spelerAanBeurt = speler;
			}
		}
		this.isEindeSpel = true;
		throw new IllegalArgumentException("winnaar");
	}

	/**
	 * Vraagt de huidige {@code Speler} die aan de beurt is op.
	 * 
	 * @return {@code Speler} aan de beurt.
	 */
	public Speler getSpelerAanBeurt() {
		return spelerAanBeurt;
	}

	/**
	 * Verandert de huidige {@code spelerAanBeurt} naar de volgende {@code Speler} in het {@code Spel}.
	 */
	public void nextSpelerAanBeurt() {
		int index = 0;
		boolean found = false;

		while (!found) {
			if (spelerAanBeurt.equals(spelersInSpel.get(index))) {
				found = true;
				break;
			} else
				index++;
		}

		if (index == spelersInSpel.size() - 1) {
			spelerAanBeurt = spelersInSpel.get(0);
		} else {
			spelerAanBeurt = spelersInSpel.get(index + 1);
		}
	}

	/**
	 * Vraagt de spelers op die meespelen in het {@code Spel}.
	 * 
	 * @return {@code ArrayList} van alle {@code Speler}s in het {@code Spel}.
	 */
	public ArrayList<Speler> getSpelersInSpel() {
		return spelersInSpel;
	}

	/**
	 * Vraagt de index op van de geselecteerde steen.
	 * 
	 * @return index van de geselecteerde steen.
	 */
	public int getGekozenSteenIndex() {
		return gekozenSteenIndex;
	}

	/**
	 * Stelt de index in van de geselecteerde steen.
	 * 
	 * @param gekozenSteenIndex index van de geselecteerde steen.
	 */
	public void setGekozenSteenIndex(int gekozenSteenIndex) {
		this.gekozenSteenIndex = gekozenSteenIndex;
	}

	/**
	 * Schudt de {@code spelersInSpel} door elkaar om een random volgorde te bepalen.
	 * Bepaalt ook de eerste {@code Speler} die aan de beurt is.
	 */
	public void shuffleSpelerLijst() {
		Collections.shuffle(spelersInSpel);
		spelerAanBeurt = spelersInSpel.get(0);
	}

	/**
	 * Vraagt het huidige {@code Spelbord} op.
	 * 
	 * @return {@code Spelbord} van dit huidige {@code Spel}.
	 */
	public Spelbord getSpelbord() {
		return this.spelbord;
	}

	/**
	 * Krijg enkele willekeurige stenen uit de {@code Steenpot}.
	 * Bovendien worden de velden {@code spelerStenenBackup} en {@code spelerStenen}
	 * geüpdatet met de gekozen stenen.
	 * 
	 * @param aantal aantal stenen die uit de {@code Steenpot} moeten worden gekozen.
	 */
	public void krijgStenen(int aantal) {
		// noodzakelijk voor verandering van 3 naar 2 steentjes
		this.spelerStenenBackup = new ArrayList<>();
		this.spelerStenenBackup = this.beschikbareStenen.getStenen(aantal);
		this.spelerStenen = new ArrayList<>(this.spelerStenenBackup);
		if (spelerStenenBackup.isEmpty()) {
			eindeSpel();
		}

	}

	/**
	 * Vraag de stenen op van de {@code Speler} die aan de beurt is.
	 * 
	 * @return {@code ArrayList} met de stenen van de huidige {@code Speler}.
	 */
	public ArrayList<Integer> getSpelerStenen() {
		return spelerStenen;
	}

	/**
	 * Retourneert de waarde van de gekozen steen.
	 * 
	 * @return de waarde van de gekozen steen.
	 */
	public int getGekozenSteen() {
		return gekozenSteen;
	}

	/**
	 * Stel de waarde in van de gekozen steen en stel ook de waarde in van de index van die steen
	 * uit de {@code ArrayList} met de geselecteerde stenen.
	 * 
	 * @param waarde de waarde van de steen.
	 * @param index de index van de geselecteerde steen.
	 */
	public void setGekozenSteen(int waarde, int index) {
		this.gekozenSteen = waarde;
		this.gekozenSteenIndex = index;
	}

	/**
	 * Verwijdert de geselecteerde steen uit de geselecteerde steentjes van de {@code Speler} aan de beurt.
	 * Zet bovendien ook de index van de geselecteerde steen op -1.
	 * @param index de index van de te verwijderen steen.
	 */
	public void verwijderGelegdeSteen(int index) {
		this.gekozenSteenIndex = -1;
		this.spelerStenen.remove(index);
	}

	/**
	 * Wanneer een steentje wordt gelegd, wordt deze tijdelijk bijgehouden in een aparte {@code ArrayList}.
	 * De bedoeling hiervan is dat wanneer een steentje foutief wordt gelegd, we terug naar een backup kunnen
	 * gaan van de geselecteerde steentjes.
	 * 
	 * @param rij de rij van het {@code Spelbord} waarop het steentje wordt gelegd.
	 * @param kolom de kolom van het {@code Spelbord} waarop het steentje wordt gelegd.
	 */
	public void houGelegdeSteenBij(int rij, int kolom) {
		int indexRij = 0;
		while (indexRij < this.beurtTijdelijkeGelegdeStenen.length) {
			int[] steen = this.beurtTijdelijkeGelegdeStenen[indexRij];

			if (Arrays.equals(steen, new int[] { 0, 0, 0 })) {
				this.beurtTijdelijkeGelegdeStenen[indexRij] = new int[] { rij, kolom, getGekozenSteen() };
				break;
			} else {
				indexRij++;
			}
		}

	}

	/**
	 * Verifieert als de gelegde stenen in de huidige beurt correct zijn gelegd.
	 * 
	 * @throws IllegalArgumentException indien een steen foutief is gelegd of als de berekende score niet correct is.
	 */
	public void controleerGelegdeStenen() {
		// Controle of eerste steentje in het midden wordt gelegd
		if (beurtTijdelijkeGelegdeStenen.length == 3 && beurtTijdelijkeGelegdeStenen[1][0] == 0) {
			if (!(beurtTijdelijkeGelegdeStenen[0][0] == 7 && beurtTijdelijkeGelegdeStenen[0][1] == 7)) {
				geefStenenTerug();
				System.out.println("fout");
				throw new IllegalArgumentException("foutmelding_steenFoutGelegd_1");
			}

			// controle of bij de eerste beurt het steentje grenst aan dezelfde speler zijn
			// stenen
		} else if (beurtTijdelijkeGelegdeStenen.length == 3) {
			// i stelt de index voor van de laatste gelegde steen in
			// beurtTijdelijkeGelegdeStenen
			int laatstGelegdeSteenIndex;
			if (beurtTijdelijkeGelegdeStenen[2][2] == 0)
				laatstGelegdeSteenIndex = 1;
			else
				laatstGelegdeSteenIndex = 2;

			// variabele j dient om gemakkelijk te rekenen naar de naast liggende vakjes
			int j = laatstGelegdeSteenIndex;
			while (laatstGelegdeSteenIndex != 0) {
				// checkt of de steen op dezelfde rij ligt maar in een aangrenzende kolom
				if (beurtTijdelijkeGelegdeStenen[j - laatstGelegdeSteenIndex][0] == beurtTijdelijkeGelegdeStenen[j][0]
						// rechts van gelegde steen
						&& (beurtTijdelijkeGelegdeStenen[j - laatstGelegdeSteenIndex][1]
								- 1 == beurtTijdelijkeGelegdeStenen[j][1]
								// links van gelegde steen
								|| beurtTijdelijkeGelegdeStenen[j - laatstGelegdeSteenIndex][1]
										+ 1 == beurtTijdelijkeGelegdeStenen[j][1])) {
					break;
					// checkt of steen in de zelfde kolom ligt maar in een aangrenzende rij ligt
				} else if (beurtTijdelijkeGelegdeStenen[j
						- laatstGelegdeSteenIndex][1] == beurtTijdelijkeGelegdeStenen[j][1]
						// rij lager van gelegde steen
						&& (beurtTijdelijkeGelegdeStenen[j - laatstGelegdeSteenIndex][0]
								- 1 == beurtTijdelijkeGelegdeStenen[j][0]
								// rij hoger van gelegde steen
								|| beurtTijdelijkeGelegdeStenen[j - laatstGelegdeSteenIndex][0]
										+ 1 == beurtTijdelijkeGelegdeStenen[j][0])) {
					break;
				}
				laatstGelegdeSteenIndex--;
				// als steen geen andere steen raakt
				if (laatstGelegdeSteenIndex == 0) {
					geefStenenTerug();
					throw new IllegalArgumentException("foutmelding_steenFoutGelegd_2");
				}
			}
		}

		// checks voor alle andere beurten (wanneer de speler max 2 steentjes kan
		// leggen)
		else if (beurtTijdelijkeGelegdeStenen.length == 2) {
			int laatstGelegdeSteenIndex;
			// nog maar 1 steen gelegd
			if (beurtTijdelijkeGelegdeStenen[1][2] == 0)
				laatstGelegdeSteenIndex = 0;
			// tweede steentje is ook gelegd
			else
				laatstGelegdeSteenIndex = 1;

			int rij = beurtTijdelijkeGelegdeStenen[laatstGelegdeSteenIndex][0];
			int kolom = beurtTijdelijkeGelegdeStenen[laatstGelegdeSteenIndex][1];

			// wanneer 2 steentjes zijn gelegd
			if (laatstGelegdeSteenIndex == 1) {
				// steentje mag niet naast je vorig gelegde steentje liggen

				// vorige beurt ligt niet in een kolom naar links of naar rechts
				if (this.beurtTijdelijkeGelegdeStenen[1][1] == this.beurtTijdelijkeGelegdeStenen[0][1]) {
					// Vakje ONDER steen is vorige zet
					if ((this.beurtTijdelijkeGelegdeStenen[1][0] == this.beurtTijdelijkeGelegdeStenen[0][0] - 1) ||
					// Vakje BOVEN steen is vorige zet
							(this.beurtTijdelijkeGelegdeStenen[1][0] == this.beurtTijdelijkeGelegdeStenen[0][0] + 1)) {
						geefStenenTerug();
						throw new IllegalArgumentException("foutmelding_steenFoutGelegd_3");
					}

					// vorige beurt licht niet in een rij naar boven of onder
				} else if (this.beurtTijdelijkeGelegdeStenen[1][0] == this.beurtTijdelijkeGelegdeStenen[0][0]) {
					// Vakje RECHTS van steen is vorige zet
					if ((this.beurtTijdelijkeGelegdeStenen[1][1] == this.beurtTijdelijkeGelegdeStenen[0][1] - 1) ||
					// Vakje LINKS van steen is vorige zet
							(this.beurtTijdelijkeGelegdeStenen[1][1] == this.beurtTijdelijkeGelegdeStenen[0][1] + 1)) {
						geefStenenTerug();
						throw new IllegalArgumentException("foutmelding_steenFoutGelegd_3");
					}
				}
			}
			// Vakje ONDER van steen is bezet
			if (!(this.spelbord.getVakjes()
			// wanneer index out of bounds, pak dan middelste steen van bord (deze zal vanaf
			// de tweede beurt altijd bezet zijn)
			// idem voor andere posities
			[(rij + 1 == 15) ? 7 : rij + 1][(rij + 1 == 15) ? 7 : kolom].isBezet() ||
			// Vakje BOVEN van steen is bezet
					this.spelbord.getVakjes()[(rij - 1 == -1) ? 7 : rij - 1][(rij - 1 == -1) ? 7 : kolom].isBezet() ||
					// Vakje RECHTS van steen is bezet
					this.spelbord.getVakjes()[(kolom + 1 == 15) ? 7 : rij][(kolom + 1 == 15) ? 7 : kolom + 1].isBezet()
					||
					// Vakje LINKS van steen is bezet
					this.spelbord.getVakjes()[(kolom - 1 == -1) ? 7 : rij][(kolom - 1 == -1) ? 7 : kolom - 1]
							.isBezet())) {

				// wanneer steentje niet grenst aan een al gelegde steen
				geefStenenTerug();
				throw new IllegalArgumentException("foutmelding_steenFoutGelegd_2");
			}
		}

		controleerScore();
	}

	/*
	 * Verifieert of het steentje correct is gelegd op basis van de score.
	 */
	private void controleerScore() {
		boolean aangrenzendBezet = false;
		int i;
		if (beurtTijdelijkeGelegdeStenen[1][2] == 0)
			i = 0;
		else if (beurtTijdelijkeGelegdeStenen.length == 2 || beurtTijdelijkeGelegdeStenen[2][2] == 0)
			i = 1;
		else
			i = 2;
		int hoogsteTotaal = 0;
		for (int j = 0; j < 4; j++) {
			int initieelRijIndex = beurtTijdelijkeGelegdeStenen[i][0];
			int initieelKolomIndex = beurtTijdelijkeGelegdeStenen[i][1];
			int totaal = beurtTijdelijkeGelegdeStenen[i][2];
			// Deze variabelen gebruiken we als een steen ingesloten is zodat we 2 kanten
			// uit kunnen bekijken
			int rijIndex = initieelRijIndex;
			int kolomIndex = initieelKolomIndex;
			boolean doorlaat = false;
			boolean ingesloten = false;
			do {
				aangrenzendBezet = false;
				switch (j) {
				case 0 -> rijIndex = ingesloten ? rijIndex - 1 : rijIndex + 1;
				case 1 -> rijIndex = ingesloten ? rijIndex + 1 : rijIndex - 1;
				case 2 -> kolomIndex = ingesloten ? kolomIndex - 1 : kolomIndex + 1;
				case 3 -> kolomIndex = ingesloten ? kolomIndex + 1 : kolomIndex - 1;
				}
				if ((kolomIndex < 15 && kolomIndex >= 0) && (rijIndex < 15 && rijIndex >= 0)) {
					if ((!(this.spelbord.getVakjes()[rijIndex][kolomIndex].isMuur())
							&& this.spelbord.getVakjes()[rijIndex][kolomIndex].isBezet())) {
						aangrenzendBezet = true;
						totaal += this.spelbord.getVakjes()[rijIndex][kolomIndex].getScore();
					} else
						for (int[] ks : beurtTijdelijkeGelegdeStenen) {
							if (ks[0] == rijIndex && ks[1] == kolomIndex) {
								aangrenzendBezet = true;
								totaal += ks[2];
							}

						}
					if (totaal > 12) {
						geefStenenTerug();
						throw new IllegalArgumentException("foutmelding_steenFoutGelegd_4");
					}
					if (totaal > hoogsteTotaal)
						hoogsteTotaal = totaal;

					if (aangrenzendBezet == false && totaal > beurtTijdelijkeGelegdeStenen[i][2] && doorlaat == false) {
						ingesloten = true;
						doorlaat = true;
						rijIndex = initieelRijIndex;
						kolomIndex = initieelKolomIndex;
						aangrenzendBezet = true;
					}

				}

			} while ((aangrenzendBezet == true && totaal <= 12));

		}
		if (hoogsteTotaal < 10
				&& this.spelbord.getVakjes()[beurtTijdelijkeGelegdeStenen[i][0]][beurtTijdelijkeGelegdeStenen[i][1]]
						.getKleur().equals("grijs")
				&& beurtTijdelijkeGelegdeStenen[i][0] != 7 && beurtTijdelijkeGelegdeStenen[i][1] != 7) {
			geefStenenTerug();
			throw new IllegalArgumentException("foutmelding_steenFoutGelegd_5");
		}
		// return true;
	}

	/**
	 * Hierbij worden alle steentjes die in deze beurt zijn gelegd,
	 * uiteindelijk officieel bijgehouden in het domein op het {@code Spelbord} zelf.
	 * Bovendien wordt van {@code beurtTijdelijkeGelegdeStenen} een nieuwe tweedimensionale array gemaakt
	 * voor de volgende {@code Speler}.
	 */
	public void legStenen() {
		for (int[] steen : this.beurtTijdelijkeGelegdeStenen) {
			this.spelbord.legSteen(steen[0], steen[1], steen[2]);

		}
		// na de eerste beurt slechts 2 mogelijk genomen steentjes
		this.beurtTijdelijkeGelegdeStenen = new int[2][3];
	}

	/**
	 * Controleert als er al geen steentje is gelegd op het huidige {@code Vakje}.
	 * 
	 * @param rijIndex de index van de rij van het {@code Spelbord} waarop het steentje wordt gelegd.
	 * @param kolomIndex de index van de kolom van het {@code Spelbord} waarop het steentje wordt gelegd.
	 * @return {@code true} indien dit {@code Vakje} nog geen steentje bevat.
	 */
	public boolean isAlGelegd(int rijIndex, int kolomIndex) {
		// is al gelegd in een van de vorige beurten
		if (getSpelbord().getVakjes()[rijIndex][kolomIndex].isBezet())
			return true;
		// is al gelegd tijdens huidige beurt
		for (int[] steen : this.beurtTijdelijkeGelegdeStenen) {
			if (steen[0] == rijIndex && steen[1] == kolomIndex)
				return true;
		}
		// nog niet gelegd op deze rij en kolom
		return false;
	}

	/*
	 * Deze methode wordt opgeroepen als een steentje fout is geleegd.
	 * Deze methode dient om geselecteerde steentjes opnieuw in te stellen vanuit de backup.
	 */
	private void geefStenenTerug() {
		int aantalStenen = this.spelerStenenBackup.size();
		this.spelerStenen.clear();
		this.spelerStenen = new ArrayList<>(spelerStenenBackup);
		this.beurtTijdelijkeGelegdeStenen = new int[aantalStenen][3];
	}

	/**
	 * Berekent de score van de huidige beurt.
	 */
	public void berekenScore() {
		// Initieren van counters om de scores door te geven
		int counter10 = 0, counter11 = 0, counter12 = 0, counterx2 = 0;

		int[][] copy = Arrays.stream(beurtTijdelijkeGelegdeStenen).map(int[]::clone).toArray(int[][]::new);
		// For-loop om het aantal punten te berekenen
		for (int[] is : beurtTijdelijkeGelegdeStenen) {
			for (int j = 0; j < 4; j++) {
				int initieelRijIndex = is[0];
				int initieelKolomIndex = is[1];
				int totaal = is[2];
				boolean aangrenzendBezet = false;
				// Deze variabelen gebruiken we als een steen ingesloten is zodat we 2 kanten
				// uit kunnen bekijken
				int rijIndex = initieelRijIndex;
				int kolomIndex = initieelKolomIndex;
				boolean kantencheck = false;
				boolean ingesloten = false;
				do {
					aangrenzendBezet = false;
					switch (j) {
					case 0 -> rijIndex = ingesloten ? rijIndex - 1 : rijIndex + 1;
					case 1 -> rijIndex = ingesloten ? rijIndex + 1 : rijIndex - 1;
					case 2 -> kolomIndex = ingesloten ? kolomIndex - 1 : kolomIndex + 1;
					case 3 -> kolomIndex = ingesloten ? kolomIndex + 1 : kolomIndex - 1;
					}
					ingesloten = false;

					if (!(this.spelbord.getVakjes()[rijIndex][kolomIndex].isMuur())
							&& this.spelbord.getVakjes()[rijIndex][kolomIndex].isBezet()) {
						aangrenzendBezet = true;
						totaal += this.spelbord.getVakjes()[rijIndex][kolomIndex].getScore();
					} else
						for (int[] ks : beurtTijdelijkeGelegdeStenen) {
							if (ks[0] == rijIndex && ks[1] == kolomIndex) {
								aangrenzendBezet = true;
								totaal += ks[2];

							}
						}
					if (aangrenzendBezet == false && totaal > is[2] && kantencheck == false) {
						ingesloten = true;
						kantencheck = true;
						rijIndex = initieelRijIndex;
						kolomIndex = initieelKolomIndex;
					}
					if (!aangrenzendBezet) {
						switch (totaal) {
						case 10 -> counter10 += 1;
						case 11 -> counter11 += 1;
						case 12 -> counter12 += 1;
						}
					}

				} while ((aangrenzendBezet == true && totaal <= 12));

			}
			// Kijken of een steen op een grijs vakje ligt
			if (this.spelbord.getVakjes()[is[0]][is[1]].getKleur().equals("grijs"))
				counterx2 += 1;
			is[0] = 0;
			is[1] = 0;
		}
		// Scoreblad aanvullen
		beurtTijdelijkeGelegdeStenen = copy;
		this.spelerAanBeurt.getScoreblad().aanvullen(counter10, counter11, counter12, counterx2);

	}

	/**
	 * Geeft het {@code Spelbord} terug van dit {@code Spel}.
	 * 
	 * @return een tweedimensionale array van {@code Vakje}s die het {@code Spelbord} voorstellen.
	 */
	public Vakje[][] getVakjes() {
		return this.spelbord.getVakjes();
	}

	/**
	 * Vraagt het {@code Scoreblad} op van de huidige {@code Speler} aan de beurt.
	 * 
	 * @return {@code Scoreblad} van de {@code Speler} aan de beurt.
	 */
	public Scoreblad getScoreBlad() {
		return this.spelerAanBeurt.getScoreblad();
	}

	/**
	 * Vraagt als het einde van het {@code Spel} als is bereikt.
	 * 
	 * @return {@code true} als het {@code Spel} gedaan is.
	 */
	public boolean isEindeSpel() {
		return isEindeSpel;
	}

}
