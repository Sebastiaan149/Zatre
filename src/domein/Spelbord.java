package domein;

/**
 * Het {@code Spelbord} klasse geeft een representatie van het spelbord tijdens het {@code Spel} weer.
 * 
 * <p>Elk {@code Spelbord} instantie houdt een tweedimensionaal array bij van {@code Vakje} objecten.
 * Zo kan er ook op elk {@code Vakje} een steen worden gelegd en wordt het {@code bord} bijgewerkt.
 */
public class Spelbord {
	/**
	 * Houdt het bord bij in de vorm van een tweedimensionale array van {@code Vakje}s.
	 */
	private Vakje[][] bord;
	
	/**
	 * Initialiseer een {@code Spelbord} object.
	 * 
	 * <p>Hierbij wordt een spelbord gemaakt van 15 rijen en 15 kolommen met telkens een {@code Vakje} object.
	 */
	public Spelbord() {
		// Initialiseer bord
		this.bord = new Vakje[15][15];
		// initialiseer middelste bord met zwarte en witte tegels
		for(int i = 1; i <= 13; i ++) {
			for (int j = 1; j <= 13; j++) {
				//diagonaal van linksboven tot rechtsonder
				if (i == j) {
					this.bord[i][j] = new Vakje("grijs", false);
				// diagonaal van linksonder naar rechtsboven (som van deze x en y waarden is ALTIJD 14)
				} else if (i + j == 14) {
					this.bord[i][j] = new Vakje("grijs", false);
				}
				else {
					this.bord[i][j] = new Vakje("wit", false);
				}
			}
		}
		
		
		//Constante index voor linkermuur en bovenmuur initialiseren
		int linkerEnBovenMuur = 0;
		//Constante index voor rechtermuur en ondermuur initialiseren
		int rechterEnOnderMuur = 14;
		
		for (int j = 0; j <= 14; j++) {
			// randmuren
			if (j > 3 && j < 7 || j > 7 && j < 11) {
				//verticale (linkse) muur
				this.bord[j][linkerEnBovenMuur] = new Vakje((j == 6 || j == 8) ? "grijs" : "wit", false);
				//horizontaal (boven)muur
				this.bord[linkerEnBovenMuur][j] = new Vakje((j == 6 || j == 8) ? "grijs" : "wit", false);
				//verticale (rechtse) muur
				this.bord[j][rechterEnOnderMuur] = new Vakje((j == 6 || j == 8) ? "grijs" : "wit", false);
				//horizontaal (onder)muur
				this.bord[rechterEnOnderMuur][j] = new Vakje((j == 6 || j == 8) ? "grijs" : "wit", false);
			// muren
			} else {
				//verticale (linkse) muur
				this.bord[j][linkerEnBovenMuur] = new Vakje("", true);
				//horizontaal (boven)muur
				this.bord[linkerEnBovenMuur][j] = new Vakje("", true);
				//verticale (rechtse) muur
				this.bord[j][rechterEnOnderMuur] = new Vakje("", true);
				//horizontaal (onder)muur
				this.bord[rechterEnOnderMuur][j] = new Vakje("", true);
			}
		}
	}

	/**
	 * Vraagt het volledige spelbord op.
	 * 
	 * @return huidig spelbord van het {@code Spel}.
	 */
	public Vakje[][] getVakjes() {
		return this.bord;
	}
	
	/**
	 * Legt een steen met een bepaalde waarde op het bord.
	 * 
	 * @param rij de rij waarop het steentje moet worden gelegd.
	 * @param kolom de kolom waarop het steentje moet worden gelegd.
	 * @param waarde de waarde van het steentje.
	 */
	public void legSteen(int rij, int kolom, int waarde) {
		this.bord[rij][kolom].setScore(waarde);
	}

}
