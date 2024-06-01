package domein;

import java.util.ArrayList;

/**
 * De {@code Scoreblad} klasse geeft een representatie van een {@code Scoreblad} van een {@code Speler}.
 * 
 * <p>Elke {@code Scoreblad} instantie houdt verschillende waarden bij waaronder: {@code aantalRijen}, {@code aantalx2},
 * {@code aantal10}, {@code aantal11}, {@code aantal12}, {@code totalen} en {@code totaleScore}.
 */
public class Scoreblad {
	
	/**
	 * Houdt het aantal rijen bij van dit {@code Scoreblad}.
	 */
	private int aantalRijen;
	
	/**
	 * Houdt het aantal keer bij waarbij "x2" wordt veroorzaakt.
	 */
	private int aantalx2;
	
	/**
	 * Houdt het aantal keer bij waarop de {@code Speler} 10 heeft als som van gelegde stenen.
	 */
	private ArrayList<String> aantal10;
	
	/**
	 * Houdt het aantal keer bij waarop de {@code Speler} 11 heeft als som van gelegde stenen.
	 */
	private ArrayList<String> aantal11;
	
	/**
	 * Houdt het aantal keer bij waarop de {@code Speler} 12 heeft als som van gelegde stenen.
	 */
	private ArrayList<String> aantal12;
	
	/**
	 * Houdt de totalen bij van dit {@code Scoreblad}.
	 */
	private ArrayList<Integer> totalen;
	
	/**
	 * Houdt de totale score bij van dit {@code Scoreblad}. Deze score bepaalt immers ook de winnaar van het {@code Spel}.
	 */
	private int totaleScore;


	/**
	 * Initialiseert een Scoreblad object en initialiseert de {@code ArrayList}s van de velden voor dit object.
	 */
	public Scoreblad() {
		aantal10 = new ArrayList<>();
		aantal11 = new ArrayList<>();
		aantal12 = new ArrayList<>();
		totalen = new ArrayList<>();
	}
	
	/**
	 * Vult het {@code Scoreblad} aan.
	 * 
	 * @param aantal10 aantal keer dat de som 10 is bereikt.
	 * @param aantal11 aantal keer dat de som 11 is bereikt.
	 * @param aantal12 aantal keer dat de som 12 is bereikt.
	 * @param aantalx2 aantal keer dat "x2" mag gedaan worden.
	 */
	public void aanvullen(int aantal10, int aantal11, int aantal12, int aantalx2) {
		//score van de gespeelde beurt aanvulle
		if (aantal10 != 0)
			this.aantal10.add("X".repeat(aantal10));
		if (aantal11 != 0)
			this.aantal11.add("X".repeat(aantal11));
		if (aantal12 != 0)	
			this.aantal12.add("X".repeat(aantal12));
		if (aantalx2 != 0)
			this.aantalx2 += aantalx2;

	}
	
	/**
	 * Bepaalt aan de hand van de waarden in de velden, het aantal rijen dat nodig is om het {@code Scoreblad} voor te stellen.
	 */
	public void bepaalRijen() {
		int grootste = this.aantal10.size();
		if (aantal11.size() > grootste)
			grootste = aantal11.size();
		if (aantal12.size() > grootste)
			grootste = aantal12.size();
		if (aantalx2 > grootste)
			grootste = aantalx2;
		setAantalRijen(grootste);
		
	}
	
	/**
	 * Berekent de totale score met de waarden die in dit {@code Scoreblad} staat.
	 */
	public void berekenTotaal() {
		totalen.clear();
		for (int i = 0; i < aantalRijen; i++) {
			int tijdelijkx2 = aantalx2;
			int totaal = 0;
			if (aantal10.size() > i)
				totaal += aantal10.get(i).length();
			if (aantal11.size() > i)
				totaal += aantal11.get(i).length() * 2;
			if (aantal12.size() > i)
				totaal += aantal12.get(i).length() * 4;

			
			if(aantal10.size() > i && aantal11.size() > i && aantal12.size() > i && 
					aantal10.get(i).length() > 0 && aantal11.get(i).length() > 0 &&
					aantal12.get(i).length() > 0)					
				totaal += i / 4 + 3;
			
			if(tijdelijkx2 != 0) {
				totaal *= 2;
				tijdelijkx2--;
			}
			totalen.add(totaal);
			int totalescore = 0;
			for (Integer integer : totalen) {
				totalescore += integer;
			}
			setTotaleScore(totalescore);
		}
	}


	/**
	 * Vraagt de totale score op
	 * @return de totale score van dit {@code Scoreblad}.
	 */
	public int getTotaleScore() {
		return totaleScore;
	}

	/**
	 * Stelt de totale score in.
	 * @param totaleScore de totale score van dit {@code Scoreblad}.
	 */
	public void setTotaleScore(int totaleScore) {
		this.totaleScore = totaleScore;
	}

	/**
	 * Vraag het aantal rijen op van dit {@code Scoreblad}.
	 * @return aantal rijen van dit {@code Scoreblad}.
	 */
	public int getAantalRijen() {
		return aantalRijen;
	}

	/**
	 * Stel het aantal rijen in van dit {@code Scoreblad}.
	 * @param aantalRijen het aantal rijen van dit {@code Scoreblad}.
	 */
	public void setAantalRijen(int aantalRijen) {
		this.aantalRijen = aantalRijen;
	}
	
	/**
	 * Vraag het aantal keer wanneer de som 10 is bereikt op en stel deze voor in een {@code ArrayList} van {@code String}.
	 * @return {@code ArrayList} met {@code String}s die het aantal voorkomens weergeeft van de som 10.
	 */
	public ArrayList<String> getAantal10() {
		return aantal10;
	}

	/**
	 * Stel het aantal keer in dat de som 10 wordt bereikt.
	 * @param aantal10 aantal keer dat de som 10 voorkomt.
	 */
	public void setAantal10(ArrayList<String> aantal10) {
		this.aantal10 = aantal10;
	}

	/**
	 * Vraag het aantal keer wanneer de som 11 is bereikt op en stel deze voor in een {@code ArrayList} van {@code String}.
	 * @return {@code ArrayList} met {@code String}s die het aantal voorkomens weergeeft van de som 11.
	 */
	public ArrayList<String> getAantal11() {
		return aantal11;
	}

	/**
	 * Stel het aantal keer in dat de som 11 wordt bereikt.
	 * @param aantal11 aantal keer dat de som 11 voorkomt.
	 */
	public void setAantal11(ArrayList<String> aantal11) {
		this.aantal11 = aantal11;
	}

	/**
	 * Vraag het aantal keer wanneer de som 12 is bereikt op en stel deze voor in een {@code ArrayList} van {@code String}.
	 * @return {@code ArrayList} met {@code String}s die het aantal voorkomens weergeeft van de som 12.
	 */
	public ArrayList<String> getAantal12() {
		return aantal12;
	}

	/**
	 * Stel het aantal keer in dat de som 12 wordt bereikt.
	 * @param aantal12 aantal keer dat de som 12 voorkomt.
	 */
	public void setAantal12(ArrayList<String> aantal12) {
		this.aantal12 = aantal12;
	}

	/**
	 * Vraag het aantal keer wanneer "x2" wordt bereikt op.
	 * @return aantal keer dat "x2" wordt bereikt.
	 */
	public int getAantalx2() {
		return aantalx2;
	}

	/**
	 * Stel het aantal keer in wanneer "x2" wordt bereikt.
	 * @param aantalx2 aantal keer wanneer "x2" wordt bereikt.
	 */
	public void setAantalx2(int aantalx2) {
		this.aantalx2 = aantalx2 ; 
	}
	
	/**
	 * Vraagt alle totalen op van dit {@code Scoreblad}.
	 * @return {@code ArrayList} van de totalen.
	 */
	public ArrayList<Integer> getTotalen() {
		return totalen;
	}

}
