package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domein.Speler;

public class SpelerMapper {

	private static final String INSERT_SPELER = "INSERT INTO ID372964_sdpzatre63.Speler (gebruikersnaam, geboortejaar)"
			+ "VALUES (?, ?)";

	private static final String GET_ID = "SELECT spelerID FROM ID372964_sdpzatre63.Speler "
			+ "where Speler.gebruikersnaam = ? && Speler.geboortejaar = ?";

	private static final String VERWIJDER_KANSEN = "UPDATE ID372964_sdpzatre63.Speler SET speelkansen = speelkansen -1 "
			+ "where Speler.spelerID = ?";

	private static final String GEEF_KANSEN = "UPDATE ID372964_sdpzatre63.Speler SET speelkansen = speelkansen +2 "
			+ "where Speler.spelerID = ?";

	public void registreer(Speler speler) {
		if (uniek(speler)) {
			try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
					PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
				query.setString(1, speler.getGebruikersnaam());
				query.setInt(2, speler.getGeboortejaar());
				query.executeUpdate();

			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	private boolean uniek(Speler speler1) {
		boolean uniek = true;

		List<Speler> spelers = geefUniekeVelden();
		for (Speler speler2 : spelers) {
			if (speler2.equals(speler1)) {
				uniek = false;
			}
		}
		return uniek;
	}

	public ArrayList<Speler> geefUniekeVelden() {
		ArrayList<Speler> spelers = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement(
						"SELECT gebruikersnaam, geboortejaar, speelkansen  FROM ID372964_sdpzatre63.Speler");
				ResultSet rs = query.executeQuery()) {

			while (rs.next()) {
				String gebruikersnaam = rs.getString("gebruikersnaam");
				int geboortejaar = rs.getInt("geboortejaar");
				int speelkansen = rs.getInt("speelkansen");

				spelers.add(new Speler(gebruikersnaam, geboortejaar, speelkansen));
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return spelers;
	}

	public ArrayList<Speler> geefAlleVelden() {
		ArrayList<Speler> spelers = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				PreparedStatement query = conn.prepareStatement("SELECT * FROM ID372964_sdpzatre63.Speler");
				ResultSet rs = query.executeQuery()) {

			while (rs.next()) {
				String gebruikersnaam = rs.getString("gebruikersnaam");
				int geboortejaar = rs.getInt("geboortejaar");
				int speelkansen = rs.getInt("speelkansen");

				spelers.add(new Speler(gebruikersnaam, geboortejaar, speelkansen));
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

		return spelers;
	}

	public void verwijderSpeelkans(String naam, int jaar) {
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				// Vragen id
				PreparedStatement query = conn.prepareStatement(GET_ID)) {
			query.setString(1, naam);
			query.setInt(2, jaar);

			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("spelerID");
				// aanpassen kansen
				PreparedStatement query2 = conn.prepareStatement(VERWIJDER_KANSEN);
				query2.setInt(1, id);
				query2.executeUpdate();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public void geefSpeelkansen(String naam, int jaar) {
		try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
				// Vragen id
				PreparedStatement query = conn.prepareStatement(GET_ID)) {
			query.setString(1, naam);
			query.setInt(2, jaar);

			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("spelerID");
				// aanpassen kansen
				PreparedStatement query2 = conn.prepareStatement(GEEF_KANSEN);
				query2.setInt(1, id);
				query2.executeUpdate();
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
}
