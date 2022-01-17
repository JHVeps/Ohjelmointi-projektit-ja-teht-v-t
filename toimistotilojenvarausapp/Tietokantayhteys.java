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
 * @author Joni Vepsäläinen, 2006234
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
     * @param toimipaikka asiakkaan toimipaikka merkkijonona
     * @param puhelin asiakkaan puhelinnumero merkkijonona
     * @throws SQLException SQL - virheet
     */
    public static void addAsiakas(Connection c, String etunimi, String sukunimi, String organisaatio, String email, String lahiosoite, 
            String postinumero, String toimipaikka, String puhelin) throws SQLException {
        
        //asiakkaan tiedot lisäävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Asiakas (etunimi, sukunimi, organisaatio, email, lahiosoite, postinumero, toimipaikka, puhelin) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );

        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, organisaatio);
        ps.setString(4, email);
        ps.setString(5, lahiosoite);
        ps.setString(6, postinumero);
        ps.setString(7, toimipaikka);       
        ps.setString(8, puhelin);        
        
        
        ps.execute();
        System.out.println("\t>> Lisätty " + etunimi + " " + sukunimi + " tietokantaan");

    }

    /**
     * 
     * @param c tietokantayhteys
     * @param etunimi asiakkaan etunimi tieto merkkijonona
     * @param sukunimi asiakkaan sukunimi merkkijonona
     * @param organisaatio asiakkaan organisaatio merkkijonona
     * @param email asiakkaan email merkkijonona
     * @param lahiosoite asiakkaan lähiosoite merkkijonona
     * @param postinumero asiakkaan postinumero merkkijonona
     * @param toimipaikka asiakkaan toimipaika merkkijonona
     * @param puhelin asiakkaan puhelinnumero merkkijonona
     * @param asiakasId asiakkaan ID
     * @throws SQLException SQL-virheet
     */
    public static void modAsiakas(Connection c, String etunimi, String sukunimi, String organisaatio, String email, String lahiosoite, 
            String postinumero, String toimipaikka, String puhelin, Integer asiakasId) throws SQLException {
        
        //asiakkaan tiedot päivittävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Asiakas SET etunimi = ?, sukunimi = ?, organisaatio = ?, email = ?, lahiosoite = ?, postinumero = ?, toimipaikka = ?, puhelin = ?"
                + "WHERE asiakasId = ?"
        );
        // 
        ps.setString(1, etunimi);
        ps.setString(2, sukunimi);
        ps.setString(3, organisaatio);
        ps.setString(4, email);
        ps.setString(5, lahiosoite);
        ps.setString(6, postinumero);
        ps.setString(7, toimipaikka);        
        ps.setString(8, puhelin);
        ps.setInt(9, asiakasId);
        ps.executeUpdate();
        System.out.println("\t>> Asiakkaan " + etunimi + " " + sukunimi + " tiedot päivitetty");

    }

    /**
     * deleteAsiakas staattinen metodi asiakkaan tietojen poistamiseksi
     * tietokannasta
     *
     * @param c tietokantayhteys
     * @param asiakasId asiakkaan id parametri kokonaislukuna
     * @throws SQLException SQL - virheet
     */
    public static void deleteAsiakas(Connection c, Integer asiakasId) throws SQLException {
        
        //asiakkaan tiedot poistava SQL DML lause
        
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
                "SELECT asiakasId, etunimi, sukunimi, organisaatio, email, lahiosoite, postinumero, toimipaikka, puhelin FROM Asiakas ORDER BY sukunimi"
        );

        return rs;

    }
    /**
     * 
     * @param c tietokantayhteys
     * @param nimi toimipisteen nimi
     * @param lahiosoite toimipisteen osoite
     * @param postinro toimipisteen postinumero
     * @param toimipaikka toimipisteen toimipaikka
     * @param kuvaus toimipisteen kuvaus
     * @throws SQLException SQL-virheet
     */
    public static void addToimispiste(Connection c, String nimi, String lahiosoite, String postinro, String toimipaikka, String kuvaus) throws SQLException {
        
        //toimipisteen tiedot lisäävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Toimipiste (nimi, lahiosoite, postinro, toimipaikka, kuvaus) "
                        + " VALUES (?, ?, ?, ?, ?)"
        );

        ps.setString(1, nimi);
        ps.setString(2, lahiosoite);
        ps.setString(3, postinro);
        ps.setString(4, toimipaikka);
        ps.setString(5, kuvaus);
        ps.execute();
        System.out.println("\t>> Lisätty " + "toimipiste " + nimi + " tietokantaan");
    }
    /**
     * 
    * @param c tietokantayhteys
     * @param nimi toimipisteen nimi
     * @param lahiosoite toimipisteen osoite
     * @param postinro toimipisteen postinumero
     * @param toimipaikka toimipisteen toimipaikka
     * @param kuvaus toimipisteen kuvaus
     * @param toimipisteId toimipisteen ID
     * @throws SQLException SQL-virheet
     */
    public static void modToimipiste(Connection c, String nimi, String lahiosoite, String postinro, String toimipaikka, String kuvaus, Integer toimipisteId) throws SQLException {
        
        //toimipisteen tiedot päivittävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Toimipiste SET nimi = ?, lahiosoite = ?, postinro = ?, toimipaikka = ?, kuvaus = ?"
                        + "WHERE toimipisteId = ?"
        );
        //
        ps.setString(1, nimi);
        ps.setString(2, lahiosoite);
        ps.setString(3, postinro);
        ps.setString(4, toimipaikka);
        ps.setString(5, kuvaus);
        ps.setInt(6, toimipisteId);
        ps.executeUpdate();
        System.out.println("\t>> Toimipisteen " + nimi + " tiedot päivitetty");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param toimipisteId toimipisteen ID
     * @throws SQLException SQL-virheet
     */
    public static void deleteToimipiste(Connection c, Integer toimipisteId) throws SQLException {
        
        //asiakkaan tiedot poistava SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Toimipiste WHERE toimipisteId = ?"
        );
        ps.setInt(1, toimipisteId);
        ps.executeUpdate();
        System.out.println("\t>> Toimipiste ID " + toimipisteId + " tiedot poistettu");

    }
    /**
     * 
     * @param c tietokantayhteys
     * @return lista toimipisteistä
     * @throws SQLException SQL-virheet
     */
    public static ResultSet selectToimipiste(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT toimipisteId, nimi, lahiosoite, postinro, toimipaikka, kuvaus FROM Toimipiste ORDER BY toimipisteid"
        );
        return rs;
    }

    /**
     * 
     * @param c tietokantayheys
     * @param asiakasID asiakkaan ID
     * @param toimipisteID toimipisteen ID
     * @param aloitusPvm varauksen aloitus päivämäärä
     * @param lopetusPvm varauksen lopetus päivämäärä
     * @throws SQLException SQL-virheet
     */
    public static void addVaraus(Connection c, Integer asiakasID, Integer toimipisteID, Date aloitusPvm, Date lopetusPvm) throws SQLException {
        
        //Varauksen tiedot lisäävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Varaus (asiakasId, toimipisteId, aloitusPvm, lopetusPvm) "
                + " VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, asiakasID);
        ps.setInt(2, toimipisteID);
        ps.setDate(3, aloitusPvm);
        ps.setDate(4, lopetusPvm);
        ps.execute();
        System.out.println("\t>> Lisätty varaus tietokantaan");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param lkm palveluiden lukumäärä
     * @param varausId varauksen ID
     * @param asiakasId asiakkaan ID
     * @param palveluId palveluiden ID
     * @throws SQLException SQL-virheet
     */
    public static void addVarauksenPalvelu(Connection c, Integer lkm, Integer varausId, Integer asiakasId, Integer palveluId) throws SQLException {
        
        //Varauksen tiedot lisäävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO VarauksenPalvelu (lkm, varausId, asiakasId, palveluId) "
                + " VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, lkm);
        ps.setInt(2, varausId);
        ps.setInt(3, asiakasId);
        ps.setInt(4, palveluId);
        ps.execute();
        System.out.println("\t>> Lisätty varauksen palvelu tietokantaan");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param lkm laitteiden lukumäärä
     * @param varausId varauksen ID
     * @param asiakasId asiakkaan ID
     * @param laiteId laitteen ID
     * @throws SQLException SQL-virheet
     */
    public static void addVarauksenLaite(Connection c, Integer lkm, Integer varausId, Integer asiakasId, Integer laiteId) throws SQLException {
        
        //Varauksen tiedot lisäävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO VarauksenLaite (lkm, varausId, asiakasId, laiteId) "
                + " VALUES (?, ?, ?, ?)"
        );

        ps.setInt(1, lkm);
        ps.setInt(2, varausId);
        ps.setInt(3, asiakasId);
        ps.setInt(4, laiteId);
        ps.execute();
        System.out.println("\t>> Lisätty varauksen laite tietokantaan");
    }    
    /**
     * 
     * @param c tietokantayhteys
     * @param asiakasID asiakkaan ID
     * @param toimipisteID toimipisteen ID
     * @param aloitusPvm varauksen aloitus päivämäärä
     * @param lopetusPvm varauksen lopetus päivämäärä
     * @param varausId varauksen ID
     * @throws SQLException SQL-virheet
     */ 
    public static void modVaraus(Connection c, Integer asiakasID, Integer toimipisteID, Date aloitusPvm, Date lopetusPvm, Integer varausId) throws SQLException {
        
        //toimipisteen tiedot päivittävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Varaus SET asiakasId = ?, toimipisteId = ?, aloitusPvm = ?, lopetusPvm = ?"
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
    /**
     * 
     * @param c tietokantayhteys
     * @param varausId varauksen ID
     * @throws SQLException SQL-virheet
     */
    public static void deleteVaraus(Connection c, Integer varausId) throws SQLException {
        
        //Varauksen tiedot poistava SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Varaus WHERE varausId = ?"
        );
        ps.setInt(1, varausId);
        ps.executeUpdate();
        System.out.println("\t>> Varaus ID " + varausId + " tiedot poistettu");

    }
    /**
     * 
     * @param c tietokantayhteys
     * @return lista varauksista
     * @throws SQLException SQL-virheet
     */
    public static ResultSet selectVaraukset(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT DISTINCT Varaus.varausID, Asiakas.asiakasId, Asiakas.etunimi, Asiakas.sukunimi, Toimipiste.toimipisteId, Toimipiste.nimi, Varaus.aloitusPvm, Varaus.lopetusPvm "
                + " FROM Varaus, Asiakas, Toimipiste "
                + " WHERE Varaus.asiakasId = Asiakas.asiakasId AND Varaus.toimipisteId = Toimipiste.toimipisteId "
                + " ORDER BY Asiakas.sukunimi"
        );
        return rs;
    }
    /**
     * 
     * @param c tietokantayhteys
     * @return lista lisäpalveluista
     * @throws SQLException SQL-virheet
     */
    public static ResultSet selectVarauksenLisapalvelut(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT DISTINCT Varaus.varausId, Asiakas.asiakasId, Asiakas.etunimi, Asiakas.sukunimi, VarauksenPalvelu.palveluId, Palvelu.palvelunNimi, "
                + " Varaus.AloitusPvm, Varaus.LopetusPvm "
                + " FROM Varaus, Asiakas, VarauksenPalvelu, Palvelu "
                + " WHERE ((Varaus.asiakasId = Asiakas.asiakasId) AND (Varaus.varausId = VarauksenPalvelu.varausId) AND "
                + "(Palvelu.palveluId = VarauksenPalvelu.palveluId)) "
                + " ORDER BY varausId "
        );

        return rs;
    }
    /**
     * 
     * @param c tietokantayhteys
     * @return lista toimipisteiden laitteista
     * @throws SQLException SQL-virheet
     */
    public static ResultSet selectVarauksenLisalaitteet(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT DISTINCT Varaus.varausId, Asiakas.asiakasId, Asiakas.etunimi, Asiakas.sukunimi, VarauksenLaite.laiteId, Laite.laitteenNimi, "
                + " Varaus.AloitusPvm, Varaus.LopetusPvm "
                + " FROM Varaus, Asiakas, VarauksenLaite, Laite "
                + " WHERE ((Varaus.asiakasId = Asiakas.asiakasId) AND (Varaus.varausId = VarauksenLaite.varausId) AND "
                + "(Laite.laiteId = VarauksenLaite.laiteId)) "
                + " ORDER BY varausId "
        );

        return rs;
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param laitteenNimi laitteen nimi
     * @param Hinta laitteen hinta (ei tarvita)
     * @param Kuvaus laitteen kuvaus
     * @param toimipisteId laitteen toimipiste
     * @throws SQLException SQL-virheet
     */
    public static void addLaite(Connection c, String laitteenNimi, Double Hinta, String Kuvaus, Integer toimipisteId) throws SQLException {
        /**
         * Laitteen tiedot lisäävä SQL DML lause
         */
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Laite (laitteenNimi, hinta, kuvaus, toimipisteId) "
                        + " VALUES (?, ?, ?, ?)"
        );

        ps.setString(1, laitteenNimi);
        ps.setDouble(2, Hinta);
        ps.setString(3, Kuvaus);
        ps.setInt(4, toimipisteId);
        ps.execute();
        System.out.println("\t>> Lisätty laite tietokantaan");
    }
    /**
     *
     * @param c tietokantayhteys
     * @param laitteenNimi laitteen nimi
     * @param Hinta laitteen hinta (ei tarvita)
     * @param Kuvaus laitteen kuvaus
     * @param toimipisteId laitteen toimipiste
     * @param laiteId laitteen ID
     * @throws SQLException SQL-virheet
     */
    public static void modLaite(Connection c, String laitteenNimi, Double Hinta, String Kuvaus, Integer toimipisteId, Integer laiteId) throws SQLException {
        
        //toimipisteen tiedot päivittävä SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Laite SET laitteenNimi = ?, hinta = ?, kuvaus = ?, toimipisteId = ? "
                + " WHERE laiteId = ?"
        );
        //
        ps.setString(1, laitteenNimi);
        ps.setDouble(2, Hinta);
        ps.setString(3, Kuvaus);
        ps.setInt(4, toimipisteId);
        ps.setInt(5, laiteId);
        ps.execute();
        System.out.println("\t>> Laitteen tiedot päivitetty");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param laiteId laitteen ID
     * @throws SQLException SQL-virheet
     */
    public static void deleteLaite(Connection c, Integer laiteId) throws SQLException {
        
        // laitteen tiedot poistava SQL DML lause
       
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Laite WHERE laiteId = ?"
        );
        ps.setInt(1, laiteId);
        ps.executeUpdate();
        System.out.println("\t>> Laite " + laiteId + " tiedot poistettu");

    }
    /**
     * 
     * @param c tietokantayhteys
     * @return lista laitteista
     * @throws SQLException SQL-virheet
     */
    public static ResultSet selectLaitteet(Connection c) throws SQLException {
        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(
                "SELECT laiteId, laitteenNimi, hinta, kuvaus, toimipisteId FROM Laite ORDER BY laitteenNimi"
        );
        return rs;
    }
    public static void addPalvelu(Connection c, String palvelunNimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId) throws SQLException {
        
        //palvelun tiedot lisäävä SQL DML lause
         
        PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Palvelu (palvelunNimi, hinta, tyyppi, kuvaus, toimipisteId) "
                        + " VALUES (?, ?, ?, ?, ?)"
        );

        ps.setString(1, palvelunNimi);
        ps.setDouble(2, hinta);
        ps.setInt(3, tyyppi);
        ps.setString(4, kuvaus);
        ps.setInt(5, toimipisteId);

        ps.execute();
        System.out.println("\t>> Lisätty palvelu " + palvelunNimi + " tietokantaan");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param palvelunNimi palvelun nimi
     * @param hinta palvelun hinta
     * @param tyyppi palvelun tyyppi
     * @param kuvaus palvleun kuvaus
     * @param toimipisteId toimipisteen ID
     * @throws SQLException SQL-virheet
     */
    public static void modPalvelu (Connection c, String palvelunNimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId, Integer palveluId) throws SQLException {
        
        //palvelun tiedot päivittävä SQL DML lause
         
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Palvelu SET palvelunNimi = ?, hinta = ?, tyyppi = ?, kuvaus = ?, toimipisteId = ?"
                        + " WHERE palveluId = ?"
        );
        //
        ps.setString(1, palvelunNimi);
        ps.setDouble(2, hinta);
        ps.setInt(3, tyyppi);
        ps.setString(4, kuvaus);
        ps.setInt(5, toimipisteId);
        ps.setInt(6, palveluId);
        ps.executeUpdate();
        System.out.println("\t>> Palvelun " + palvelunNimi + " tiedot päivitetty");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param palveluId palvelun ID
     * @throws SQLException SQL-virheet
     */
    public static void deletePalvelu(Connection c, Integer palveluId) throws SQLException {
        
        //palvelun tiedot poistava SQL DML lause
        
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
        
        //palvelu näkymään valitseva SQL SELECT lause
         
        ResultSet rs = stmt.executeQuery(
                "SELECT palveluId, palvelunNimi, hinta, tyyppi, kuvaus, toimipisteId FROM Palvelu ORDER BY palveluId"
        );

        return rs;
    }
    /**
     * 
     * @param c tietokanta yhetys
     * @param varausID varauksen ID
     * @param asiakasID asiakkaan ID
     * @param summa laskun summa
     * @param erapaiva laskun eräpäivä
     * @param laskutustapa laskutustapa
     * @throws SQLException SQL-virheet
        */
    public static void addLasku(Connection c, Integer varausID, Integer asiakasID, Double summa, Date erapaiva, String laskutustapa) throws SQLException {

           //Varauksen tiedot lisäävä SQL DML lause

           PreparedStatement ps = c.prepareStatement(
                   "INSERT INTO Lasku (varausId, asiakasId, summa, erapaiva, laskutustapa) "
                   + " VALUES (?, ?, ?, ?, ?)"
           );

           ps.setInt(1, varausID);
           ps.setInt(2, asiakasID);
           ps.setDouble(3, summa);
           ps.setDate(4, erapaiva);
           ps.setString(5, laskutustapa);
           ps.execute();
           System.out.println("\t>> Lisätty lasku tietokantaan");
    }
    /**
     * 
     * @param c tietokanta yhetys
     * @param varausId varauksen ID
     * @param asiakasId asiakkaan ID
     * @param summa laskun summa
     * @param erapaiva laskun eräpäivä
     * @param laskutustapa laskutustapa
     * @param viitenumero laskun viitenumero
     * @throws SQLException SQL-virheet
     */
    public static void modLasku (Connection c, Integer varausId, Integer asiakasId, Double summa, Date erapaiva, String laskutustapa, Integer viitenumero) throws SQLException {
        
        //palvelun tiedot päivittävä SQL DML lause
         
        PreparedStatement ps = c.prepareStatement(
                "UPDATE Lasku SET varausId = ?, asiakasId = ?, summa = ?, erapaiva = ?, laskutustapa = ?"
                + "WHERE viitenumero = ?"
        );
        //
        ps.setInt(1, varausId);
        ps.setInt(2, asiakasId);
        ps.setDouble(3, summa);
        ps.setDate(4, erapaiva);
        ps.setString(5, laskutustapa);
        ps.setInt(6, viitenumero);
        ps.executeUpdate();
        System.out.println("\t>> Laskun jonka varaus ID " + varausId + " tiedot päivitetty");
    }
    /**
     * 
     * @param c tietokantayhteys
     * @param viitenumero laskun viitenumero
     * @throws SQLException SQL-virheet
     */
    public static void deleteLasku(Connection c, Integer viitenumero) throws SQLException {
        
        //palvelun tiedot poistava SQL DML lause
        
        PreparedStatement ps = c.prepareStatement(
                "DELETE FROM Lasku WHERE viitenumero = ?"
        );
        ps.setInt(1, viitenumero);
        ps.executeUpdate();
        System.out.println("\t>> Laskun Viitenumero: " + viitenumero + " tiedot poistettu");
    }
       public static ResultSet selectLaskutetut(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        
        //laskutetut näkymään valitseva SQL SELECT lause
        ResultSet rs = stmt.executeQuery(
                " SELECT Lasku.viitenumero, Lasku.varausId, Lasku.asiakasId, Asiakas.etunimi, Asiakas.sukunimi, Lasku.summa, Lasku.erapaiva, Lasku.laskutustapa "
                + " FROM Lasku, Asiakas "
                + " WHERE Lasku.asiakasId = Asiakas.asiakasId "       
                + " ORDER BY Lasku.erapaiva"
        );

        return rs;

    }
}
