package toimistotilojenvarausapp;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Tietokantayhteys luokka sisältää metodit tietokantayhteyden avaamiseen,
 * tietokantayhteyden sulkemiseen, tietokannan luomiseen, tietokannan valintaan,
 * tietokantaan taulun luomiseen, tietueiden lisäämiseen, muokkaamiseen ja poistamiseen,
 * sekä tietueiden lista näkymään lisäämiseksi
 *
 * @version 1.0
 * @author Joni Vepsäläinen
 * 
 */
public class Tietokantayhteys {

    /**
     * openConnection metodi tietokantayhteyden luomiseksi
     *
     * @param connString tietokanta yhteyden määrittelevä merkkijono
     * @throws SQLException SQL - virheet
     * @return con tietokantayhteys luotu
     */
    public static Connection openConnection(String connString) throws SQLException {
        Connection con = DriverManager.getConnection(connString);
        System.out.println("\t>> Yhteys ok");
        return con;
    }

    /**
     * closeConnection staattinen metodi tietokantayhteyden sulkemiseksi
     *
     * @param c suljettava tietokantayhteys
     * @throws SQLException SQL - virheet
     *
     */
    public static void closeConnection(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
        System.out.println("\t>> Tietokantayhteys suljettu");
    }

    /**
     * createDatabase staattinen metodi SQL tietokannan luomiseksi sisältää
     * tietokannan luovan DLL lauseen
     *
     * @param c tietokantayhteys
     * @param db käytettävä tietokanta
     * @throws SQLException SQL - virheet
     */
    public static void createDatabase(Connection c, String db) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery("DROP DATABASE IF EXISTS " + db);
        System.out.println("\t>> Tietokanta " + db + " tuhottu");

        stmt.executeQuery("CREATE DATABASE " + db);
        System.out.println("\t>> Tietokanta " + db + " luotu");

        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokantaa " + db);

    }

    /**
     * useDatabase staattinen metodi käytettävän tietokannan määrittämiseksi
     *
     * @param c tietokantayhteys
     * @param db käytettävä tietokanta merkkijonona
     * @throws SQLException SQL - virheet
     *
     */
    public static void useDatabase(Connection c, String db) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokantaa " + db);
    }

    /**
     * createTable staattinen metodi taulun luomiseksi SQL tietokantaan
     *
     * @param c tietokantayhteys
     * @param sql suoritettava SQL DLL lause
     * @throws SQLException SQL - virheet
     */
    public static void createTable(Connection c, String sql) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery(sql);
        System.out.println("\t>> Taulu luotu");

    }
    /**
     * addAsiakas staattinen metodi asiakkaan tietojen lisäämiseksi
     * tietokantaan
     *
     * @param c tietokanatayhteys
     * @param etunimi asiakkaan etunimi tieto merkkijonona
     * @param sukunimi asiakkaan sukunimi merkkijonona
     * @param organisaatio asiakkaan organisaatio merkkijonona
     * @param email asiakkaan email merkkijonona
     * @param lahiosoite asiakkaan lähiosoite merkkijonona
     * @param postinumero asiakkaan postinumero merkkijonona
     * @param puhelin asiakkaan puhelinnumero merkkijonona
     * @throws SQLException SQL - virheet
     */
    public static void addAsiakas(Connection c, String etunimi, String sukunimi, String organisaatio, String email, String lahiosoite, 
            String postinumero, String puhelin) throws SQLException {
        /**
         * asiakkaan tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Asiakas (etunimi, sukunimi, organisaatio, email, lahiosoite, postinumero, puhelin) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)"
        );

        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, organisaatio);
        ps.setString(4, email);
        ps.setString(5, lahiosoite);
        ps.setString(6, postinumero);
        ps.setString(7, puhelin);        
        
        
        ps.execute();
        System.out.println("\t>> Lisätty " + etunimi + " " + sukunimi + " tietokantaan");

    }

    /**
     * modAsiakas staattinen metodi asiakkaan tietokanta tietojen
     * muokkaamiseksi
     *
     * @param c tietokantayhteys
     * @param etunimi asiakkaan muokattu etunimi merkkijonona
     * @param sukunimi asiakkaan muokattu sukunimi merkkijonona
     * @param organisaatio asiakkaan muokattu organisaatio merkkijonona
     * @param asiakasId asiakkaan id parametri kokonaislukuna
     * @throws SQLException SQL - virheet
     */
    public static void modAsiakas(Connection c, String etunimi, String sukunimi, String organisaatio, String email, String lahiosoite, 
            String postinumero, String puhelin, Integer asiakasId) throws SQLException {
        /*
         * asiakkaan tiedot päivittävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Asiakas SET etunimi = ?, sukunimi = ?, organisaatio = ?, email = ?, lahiosoite = ?, postinumero = ?, puhelin = ?"
                + "WHERE asiakasId = ?"
        );
        // 
        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, organisaatio);
        ps.setString(4, email);
        ps.setString(5, lahiosoite);
        ps.setString(6, postinumero);
        ps.setString(7, puhelin);
        ps.setInt(8, asiakasId);
        ps.executeUpdate();
        System.out.println("\t>> Asiakkaan " + etunimi + " " + sukunimi + " tiedot päivitetty");

    }

    /**
     * deleteAsiakas staattinen metodi asiakkaan tietojen poistamiseksi
     * tietokannasta
     *
     * @param c tietokantyhteys
     * @param asiakasId asiakkaan id parametri kokonaislukuna
     * @throws SQLException SQL - virheet
     */
    public static void deleteAsiakas(Connection c, Integer asiakasId) throws SQLException {
        /*
         * asiakkaan tiedot poistava SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Asiakas WHERE asiakasId = ?"
        );
        ps.setInt(1, asiakasId);
        ps.executeUpdate();
        System.out.println("\t>> Asiakas ID " + asiakasId + " tiedot poistettu");

    }

    /**
     * selectAsiakkaat staattinen metodi asiakkaiden tietojen lisäämiseksi
     * asiakaslistaan
     *
     * @param c tietokantayhteys
     * @throws SQLException SQL - virheet
     * @return rs lista asiakkaista
     */
    public static ResultSet selectAsiakkaat(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        /*
         * asiakkaat näkymään valitseva SQL SELECT lause
         */
        ResultSet rs = stmt.executeQuery(
                "SELECT asiakasId, etunimi, sukunimi, organisaatio, email, lahiosoite, postinumero, puhelin FROM Asiakas ORDER BY sukunimi"
        );

        return rs;

    }

    public static void addToimispiste(Connection c, String nimi, String osoite, String kuvaus) throws SQLException {
        /**
         * toimipisteen tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Toimipiste (nimi, osoite, kuvaus) "
                        + " VALUES (?, ?, ?)"
        );

        ps.setString(1, nimi);
        ps.setString(2, osoite);
        ps.setString(3, kuvaus);
        ps.execute();
        System.out.println("\t>> Lisätty " + "toimipiste " + nimi + " tietokantaan");
    }

    public static void modToimipiste(Connection c, String nimi, String osoite, String kuvaus, Integer toimipisteId) throws SQLException {
        /*
         * toimipisteen tiedot päivittävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Toimipiste SET nimi = ?, osoite = ?, kuvaus = ?"
                        + "WHERE toimipisteId = ?"
        );
        //
        ps.setString(1, nimi);
        ps.setString(2, osoite);
        ps.setString(3, kuvaus);
        ps.setInt(4, toimipisteId);
        ps.executeUpdate();
        System.out.println("\t>> Toimipisteen " + nimi + " tiedot päivitetty");
    }

    public static void deleteToimipiste(Connection c, Integer toimipisteId) throws SQLException {
        /*
         * asiakkaan tiedot poistava SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Toimipiste WHERE toimipisteId = ?"
        );
        ps.setInt(1, toimipisteId);
        ps.executeUpdate();
        System.out.println("\t>> Toimipiste ID " + toimipisteId + " tiedot poistettu");

    }

    public static ResultSet selectToimipiste(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT toimipisteId, nimi, osoite, kuvaus FROM Toimipiste ORDER BY nimi"
        );
        return rs;
    }

    /**
     * Lisätty vähän myöhemmin, niin jos ohjelma ei toimi niin kattokkaa etten mitään rikkonu.
     */

       public static void addVaraus(Connection c, Integer asiakasID, Integer toimipisteID, Date aloitusPvm, Date lopetusPvm) throws SQLException {
        /**
         * Varauksen tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Varaus (asiakasID, toimipisteID, aloitusPvm, lopetusPvm) "
                        + " VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, asiakasID);
        ps.setInt(2, toimipisteID);
        ps.setDate(3, aloitusPvm);
        ps.setDate(4, lopetusPvm);
        ps.execute();
        System.out.println("\t>> Lisätty varaus tietokantaan");
    }

    public static void modVaraus(Connection c, Integer asiakasID, Integer toimipisteID, Date aloitusPvm, Date lopetusPvm, Integer varausId) throws SQLException {
        /*
         * toimipisteen tiedot päivittävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Varaus SET asiakasID = ?, toimipisteID = ?, aloitusPvm = ?, lopetusPvm = ?"
                        + "WHERE varausId = ?"
        );
        //
        ps.setInt(1, asiakasID);
        ps.setInt(2, toimipisteID);
        ps.setDate(3, aloitusPvm);
        ps.setDate(4, lopetusPvm);
        ps.setInt(5, varausId);
        ps.executeUpdate();
        System.out.println("\t>> Varauksen tiedot päivitetty");
    }

    public static void deleteVaraus(Connection c, Integer varausId) throws SQLException {
        /*
         * Varauksen tiedot poistava SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Varaus WHERE varausId = ?"
        );
        ps.setInt(1, varausId);
        ps.executeUpdate();
        System.out.println("\t>> Varaus ID " + varausId + " tiedot poistettu");

    }

    public static ResultSet selectVaraukset(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT varausId, aloitusPvm, lopetusPvm FROM Varaus ORDER BY varausId"
        );
        return rs;
    }

    public static ResultSet selectLaitteet(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT laitteenNimi, Hinta, Kuvaus FROM Laite ORDER BY laitteenNimi"
        );
        return rs;
    }

    public static void addLaite(Connection c, String laitteenNimi, Integer Hinta, String Kuvaus) throws SQLException {
        /**
         * Laitteen tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Laite (laitteenNimi, Hinta, Kuvaus) "
                        + " VALUES (?, ?, ?)"
        );

        ps.setString(1, laitteenNimi);
        ps.setInt(2, Hinta);
        ps.setString(3, Kuvaus);
        ps.execute();
        System.out.println("\t>> Lisätty laite tietokantaan");
    }

    public static void modLaite(Connection c, String laitteenNimi, Integer Hinta, String Kuvaus) throws SQLException {
        /*
         * toimipisteen tiedot päivittävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Laite SET Hinta = ?, Kuvaus = ?"
                        + "WHERE laitteenNimi = ?"
        );
        //
        ps.setInt(1, Hinta);
        ps.setString(2, Kuvaus);
        ps.setString(3, laitteenNimi);
        ps.execute();
        System.out.println("\t>> Laitteen tiedot päivitetty");
    }

    public static void deleteLaite(Connection c, String laitteenNimi) throws SQLException {
        /*
         * laitteen tiedot poistava SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Laite WHERE laitteenNimi = ?"
        );
        ps.setString(1, laitteenNimi);
        ps.executeUpdate();
        System.out.println("\t>> Laite " + laitteenNimi + " tiedot poistettu");

    }

    public static void addPalvelu(Connection c, String nimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId) throws SQLException {
        /**
         * palvelun tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Palvelu (nimi, hinta, tyyppi, kuvaus, toimipisteId) "
                        + " VALUES (?, ?, ?, ?, ?)"
        );

        ps.setString(1, nimi);
        ps.setDouble(2, hinta);
        ps.setInt(3, tyyppi);
        ps.setString(4, kuvaus);
        ps.setInt(5, toimipisteId);

        ps.execute();
        System.out.println("\t>> Lisätty palvelu " + nimi + " tietokantaan");
    }

    public static void modPalvelu (Connection c, String nimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId) throws SQLException {
        /**
         * palvelun tiedot päivittävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Palvelu SET nimi = ?, hinta = ?, tyyppi = ?, kuvaus = ?, toimipisteId = ?"
                        + "WHERE palveluId = ?"
        );
        //
        ps.setString(1, nimi);
        ps.setDouble(2, hinta);
        ps.setInt(3, tyyppi);
        ps.setString(4, kuvaus);
        ps.setInt(5, toimipisteId);
        ps.executeUpdate();
        System.out.println("\t>> Palvelun " + nimi + " tiedot päivitetty");
    }

    public static void deletePalvelu(Connection c, Integer palveluId) throws SQLException {
        /*
         * palvelun tiedot poistava SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Palvelu WHERE palveluId = ?"
        );
        ps.setInt(1, palveluId);
        ps.executeUpdate();
        System.out.println("\t>> Palvelu ID " + palveluId + " tiedot poistettu");
    }

    /**
     * Metodi lisää palveluita listaan
     *
     * @param c tietokantayhteys
     * @throws SQLException SQL - virheet
     * @return rs lista asiakkaista
     */

    public static ResultSet selectPalvelu(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        /*
         * palvelu näkymään valitseva SQL SELECT lause
         */
        ResultSet rs = stmt.executeQuery(
                "SELECT palveluId, nimi, hinta, tyyppi, kuvaus, toimipisteId FROM Palvelu ORDER BY palveluId"
        );

        return rs;
    }

}