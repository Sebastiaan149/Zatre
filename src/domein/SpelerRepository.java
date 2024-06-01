package domein;

import java.util.ArrayList;

import persistentie.SpelerMapper;

/**
 * De {@code SpelerRepository} klasse is verantwoordelijk voor het beheer van de {@code Speler} objecten.
 * Dit houdt in dat deze klasse instaat voor het toevoegen, wijzigen en opvragen van {@code Speler}s uit de database.
 */
public class SpelerRepository {

	/**
	 * Houdt een {@code ArrayList} bij van alle {@code Speler}s uit de database.
	 */
	private ArrayList<Speler> spelers;

	/**
	 * Maakt een instantie aan van {@code SpelerRepository}.
	 */
	public SpelerRepository() {
		spelers = new ArrayList<>();
		vraagSpelersOpAanDatabank();
	}

	/**
	 * Registreert de {@code Speler} in de database.
	 * 
	 * @param speler {@code Speler} object die moet worden opgeslaan in de databank.
	 */
	public void registreer(Speler speler) {
		SpelerMapper mp = new SpelerMapper();
		mp.registreer(speler);
	}

	/**
	 * Vermindert de speelkansen van een {@code Speler} met 1 in de database.
	 * 
	 * @param naam gebruikersnaam van de {@code Speler}.
	 * @param jaar geboortejaar van de {@code Speler}.
	 */
	public void verwijderKans(String naam, int jaar) {
		SpelerMapper mp = new SpelerMapper();
		mp.verwijderSpeelkans(naam, jaar);
	}

	/**
	 * Geeft een {@code Speler} 2 extra speelkansen en wordt geüpdatet in de database.
	 * 
	 * @param naam gebruikersnaam van de {@code Speler}.
	 * @param jaar geboortejaar van de {@code Speler}.
	 */
	public void geefKansen(String naam, int jaar) {
		SpelerMapper mp = new SpelerMapper();
		mp.geefSpeelkansen(naam, jaar);
	}

	/**
	 * Vraagt alle spelers op uit de database en houdt deze bij in het veld {@code spelers}.
	 */
	public void vraagSpelersOpAanDatabank() {
		SpelerMapper mp = new SpelerMapper();
		spelers = mp.geefAlleVelden();
	}

	/**
	 * We refreshen eerst de spelers op van de database (indien er extra spelers zijn geregistreerd).
	 * Nadien geeft deze methode een {@code ArrayList} terug van alle {@code Speler}s die in de database staan.
	 * 
	 * @return {@code ArrayList} met alle {@code Speler}s uit de database.
	 */
	public ArrayList<Speler> geefSpelers() {
		// eerst refreshen we de gegevens uit de databank
		vraagSpelersOpAanDatabank();
		return spelers;
	}

	/**
	 * Geeft een String representatie van alle {@code Speler}s uit de database.
	 * 
	 * @return {@ArrayList} van {@code String} van alle {@code Speler}s uit de database.
	 */
	public ArrayList<String> geefSpelersString() {
		ArrayList<String> spelersString = new ArrayList<>();
		for (Speler s : spelers) {
			spelersString.add(s.toString());
		}
		return spelersString;
	}

}
