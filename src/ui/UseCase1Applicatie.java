package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;
import domein.Speler;
import resources.Taal;

public class UseCase1Applicatie {

	private DomeinController dc;
	private Taal t;
	Scanner invoer = new Scanner(System.in);

	public UseCase1Applicatie(DomeinController dc) {
		this.dc = dc;
		t = new Taal(selecteerTaal());

		System.out.println("\n" + t.vertaal("begroeting"));
	}

	public String selecteerTaal() {
		System.out.println("1. Speel in het nederlands.");
		System.out.println("2. Continue in English.");
		System.out.print("Maak uw keuze / Make your selection > ");

		int keuze = invoer.nextInt();
		String taal = "nl_BE";

		if (keuze < 1 || keuze > 2)
			throw new IllegalArgumentException("U hebt geen geldig getal opgegeven.");

		switch (keuze) {
		case 1 -> taal = "resources.nl_BE";
		case 2 -> taal = "resources.en_US";
		}

		return taal;
	}

	public void start() {

		int keuze = 0;

		do {
			do {
				try {

					toonMenu();
					System.out.print(t.vertaal("maak_keuze"));

					keuze = invoer.nextInt();
					System.out.println();

					switch (keuze) {
					case 1 -> registreerNieuweSpeler();
//					case 2 -> toonAlleSpelers();
					case 3 -> selecteerSpeler();
//					case 4 -> toonGeselecteerdeSpelers();
					case 5 -> System.out.println(t.vertaal("afsluiting"));
					}

				} catch (InputMismatchException e) {
					System.out.println("Geef een geldig getal in.");
					invoer.nextLine();
				}
			} while (!(keuze <= 5 && keuze > 0));

		} while (keuze < 5 && keuze > 0);

		invoer.close();

	}

	public void toonMenu() {
		String menu = "\n=== MENU ===";
		menu += "\n1. " + t.vertaal("menu_item_1");
		menu += "\n2. " + t.vertaal("menu_item_2");
		menu += "\n3. " + t.vertaal("menu_item_3");
		menu += "\n4. " + t.vertaal("menu_item_4");
		menu += "\n5. " + t.vertaal("menu_item_5");
		System.out.println(menu);
	}

	public void registreerNieuweSpeler() {

		Speler speler = null;

		try {
			System.out.print("Geef je gebruikersnaam: ");
			String gebruikersnaam = invoer.next();
			System.out.print("Geef je geboortejaar: ");
			int geboortejaar = invoer.nextInt();

			speler = new Speler(gebruikersnaam, geboortejaar);

			dc.registreerSpeler(gebruikersnaam, geboortejaar);

		} catch (IllegalArgumentException e) {
			System.out.println(e);
			registreerNieuweSpeler();
		}
		System.out.printf("%s met geboortejaar %d is succesvol geregistreerd.\n\n", speler.getGebruikersnaam(),
				speler.getGeboortejaar());

	}

//	public void toonAlleSpelers() {
//		String spelers = "=== Lijst van alle spelers uit de databank ===\n";
//		for (String s : dc.geefSpelersInString()) {
//			spelers += s + "\n";
//		}
//		System.out.println(spelers);
//	}

	public void selecteerSpeler() {
		try {
			dc.controleerAantalGeselecteerdeSpelers();
		} catch (IllegalArgumentException e) {
			System.out.println("U heeft het maximale aantal spelers geselecteerd");
		}
		int aantalSpelers = dc.getGeselecteerdeSpelers().size();

		System.out.print("Geef de gebruikersnaam van de speler: ");
		String gebruikersnaam = invoer.next();
		System.out.print("Geef de geboortedatum van de speler: ");
		int geboortejaar = invoer.nextInt();

		dc.inloggen(gebruikersnaam, geboortejaar);

		if (dc.getGeselecteerdeSpelers().size() > aantalSpelers) {
			System.out.println("\nU heeft succesvol de speler toegevoegd\n");
		}

	}

//	public void toonGeselecteerdeSpelers() {
//		System.out.println("=== Lijst van geselecteerde spelers ===");
//		System.out.println(dc.geefGeselecteerdeSpelersString());
//	}

}
