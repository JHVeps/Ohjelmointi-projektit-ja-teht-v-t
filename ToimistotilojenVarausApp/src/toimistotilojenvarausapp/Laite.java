package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Leevi Meriläinen
 */
public class Laite {

    /**
     * Laitteen nimi
     */
    private String laitteenNimi;
    /**
     * Laite hinta
     */
    private Integer Hinta = null;
    /**
     * Varaus kuvaus
     */
    private String Kuvaus = null;

    /**
     * Olion tyhjä oletus konstruktori
     */
    public Laite() {
    }

    /**
     * @param laitteenNimi olion laitenimi merkkijonona
     * @param Hinta olion hinta numerona
     * @param Kuvaus olion kuvaus merkkijonona
     */
    public Laite(String laitteenNimi, Integer Hinta, String Kuvaus) {
        this.laitteenNimi = laitteenNimi;
        this.Hinta = Hinta;
        this.Kuvaus = Kuvaus;
    }

    //Getterit
    /**
     * Palautetaan laitteen laiteinim
     *
     * @return String laite olion laitenimi
     */
    public String getlaitteenNimi() {
        return laitteenNimi;
    }

    /**
     * Palautetaan Hinta
     *
     * @return Integer Hinta olion hinta
     */
    public Integer getHinta() {
        return Hinta;
    }
    
     /**
     * Palautetaan Kuvaus
     *
     * @return String Laite olion kuvaus
     */
    
    public String getKuvaus() {
        return Kuvaus;
    }
    
    //Setterit
    /**
     * Asetetaan laite olion Hinta tieto
     *
     * @param Hinta 
     */
    public void setHinta(Integer Hinta) {
        this.Hinta = Hinta;
    }

    /**
     * Asetetaan Laite olion Kuvaus tieto
     *
     * @param Kuvaus 
     */
    public void setKuvaus(String Kuvaus) {
        this.Kuvaus = Kuvaus;
    }

    
    //metodit
    /**
     * Palautetaan Laite oliota kuvaava merkkijono
     *
     * @return Laitteen tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.laitteenNimi + " " + this.Hinta + " " + this.Kuvaus);
    }

    /**
     * Haetaan varauksen tiedot SQL - tietokannasta ja palautetaan
     * varausolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param laitteenNimi
     * @throws SQLException
     * @throws Exception
     * @return LaiteOlio
     */
    public static Laite searchLaite(Connection c, String laitteenNimi) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // haetaan tietokannasta varausta, jonka varausId = varausId 
        String sql = "SELECT laitteenNimi, Hinta, Kuvaus"
                + " FROM Laite WHERE laitteenNimi = ?"; // ehdon arvo asetetaan jäljempänä
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            /*
             * luo PreparedStatement-olio sql-lauseelle
             */
            lause = c.prepareStatement(sql);
            lause.setString(1, laitteenNimi); // asetetaan where ehtoon (?) arvo
            /*
             * suorita sql-lause
             */
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Laitetta ei loydy");
            }
        } catch (Exception e) {
            /*
             * JDBC virheet
             */
            throw e;
        }
        /**
         * käsitellään resultset - laitetaan tiedot LaiteOliolle
         */
        Laite LaiteOlio = new Laite();

        try {
            if (tulosjoukko.next() == true) {
                //Hinta, Kuvaus
                LaiteOlio.setHinta(tulosjoukko.getInt("Hinta"));
                LaiteOlio.setKuvaus(tulosjoukko.getString("Kuvaus"));

            }

        } catch (SQLException e) {
            throw e;
        }
        /**
         * palautetaan LaiteOlio
         */

        return LaiteOlio;

    }

    
    
}
