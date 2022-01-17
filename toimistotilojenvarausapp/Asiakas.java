package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Asiakas luokka sisältää asiakas olion atribuutit (tärkeimpänä atribuuttinaan olion yksilöivä id-tunnus) ja metodin
 * asiakas-olion tietojen palauttamiseen
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class Asiakas {
    /**
     * Asiakkaan id numero
     */
    private Integer asiakasId;
    /**
     * Asiakkaan etunimi
     */
    private String etunimi = null;
    /**
     * Asiakkaan sukunimi
     */
    private String sukunimi = null;
    /**
     * Asiakkaan organisaatio
     */
    private String organisaatio = null;
    /**
     * Asiakkaan email
     */
    private String email = null;
    /**
     * Asiakkaan lähiosoite
     */
    private String lahiosoite = null;
    /**
     * Asiakkaan postinumero
     */
    private String postinumero = null;
       /**
     * Asiakkaan toimipaikka
     */
    private String toimipaikka = null;
    /**
     * Asiakkaan puhelinnumero
     */
    private String puhelin = null;    
    

    /**
     * Olion tyhjä oletus konstruktori
     */
    public Asiakas() {
    }

    /**
     * Olion konstruktori tallentaa asiakas olion id numeron, etunimen,
     * sukunimen, organisaation ja emailin
     *
     * @param asiakasId olion id numero kokonaislukuna
     * @param etunimi olion etunimi merkkijonona
     * @param sukunimi olion sukunimi merkkijonona
     * @param organisaatio olion organisaatio merkkijonona
     * @param email olion email merkkijonona
     * @param lahiosoite olion lahiosoite merkkijonona
     * @param postinumero olion postinumero merkkijonona
     * @param toimipaikka olion toimipaikka merkkijonona
     * @param puhelin olion puhelinnumero merkkijonona
     */
    public Asiakas(Integer asiakasId, String etunimi, String sukunimi, String organisaatio,
            String email, String lahiosoite, String postinumero, String toimipaikka, String puhelin) {
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.organisaatio = organisaatio;
        this.email = email;
        this.lahiosoite = lahiosoite;
        this.postinumero = postinumero;
        this.toimipaikka = toimipaikka;
        this.puhelin = puhelin;
    }

    //Getterit
    /**
     * Palautetaan asiakkaan id numero
     *
     * @return int asiakas olion id numero
     */
    public Integer getAsiakasId() {
        return asiakasId;
    }

    /**
     * Palautetaan etunimi
     *
     * @return String asiakas olion etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Palautetaan sukunimi
     *
     * @return String asiakas olion sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Palautetaan organisaatio tieto
     *
     * @return String asiakas olion organisaatio
     */
    public String getOrganisaatio() {
        return organisaatio;
    }

    /**
     * Palautetaan email tieto
     *
     * @return String asiakas olion email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Palautetaan lähiosoite tieto
     *
     * @return String asiakas olion lähiosoite
     */
    public String getLahiosoite() {
        return lahiosoite;
    }

    /**
     * Palautetaan postinumero tieto
     *
     * @return String asiakas olion postinumero
     */
    public String getPostinumero() {
        return postinumero;
    }
    /**
     * Palautetaan toimipaikka tieto
     *
     * @return String asiakas olion toimipaikka
     */
    public String getToimipaikka() {
        return toimipaikka;
    }
    /**
     * Palautetaan puhelin tieto
     *
     * @return String asiakas olion puhelinnumero
     */
    public String getPuhelin() {
        return puhelin;
    }    
    //Setterit
    /**
     * Asetetaan asiakas olion etunimi tieto
     *
     * @param etunimi etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    /**
     * Asetetaan asiakas olion sukunimi tieto
     *
     * @param sukunimi sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }   
    
    /**
     * Asetetaan asiakas olion organisaatio tieto
     *
     * @param organisaatio organisaatio
     */
    public void setOrganisaatio(String organisaatio) {
        this.organisaatio = organisaatio;
    }

    /**
     * Asetetaan asiakas olion email tieto
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }      
    
    /**
     * Asetetaan asiakas olion lähiosoite tieto
     *
     * @param lahiosoite lähiosoite
     */
    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }
    
    /**
     * Asetetaan asiakas olion postinumero tieto
     *
     * @param postinumero postinumero
     */
    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }
  /**
     * Asetetaan asiakas olion toimipakka tieto
     *
     * @param toimipaikka toimipaikka
     */
    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }
    /**
     * Asetetaan asiakas olion puhelinnumero tieto
     *
     * @param puhelin puhelinnumero
     */
    public void setPuhelin(String puhelin) {
        this.puhelin = puhelin;
    }

   
    //metodit
    /**
     * Palautetaan Asiakas oliota kuvaava merkkijono
     *
     * @return Asiakkaan tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.asiakasId + " " + this.etunimi + " " + this.sukunimi + " " + this.organisaatio + " " + this.email
                + " " + this.lahiosoite + " " + this.postinumero + " " + this.toimipaikka + " " + this.puhelin);
    }

    /**
     * Haetaan asiakkaan tiedot SQL - tietokannasta ja palautetaan
     * asiakasolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param asiakasId asiakkaan id parametri
     * @throws SQLException SQL-virheer
     * @throws Exception muut virhet
     * @return AsiakasOlio asiakkaan tiedot sisältävä olio
     */
    public static Asiakas searchAsiakas(Connection c, Integer asiakasId) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // haetaan tietokannasta asiakasta, jonka asiakasId = asiakasId 
        String sql = "SELECT asiakasId, etunimi, sukunimi, organisaatio, email, lahiosoite, postinumero, toimipaikka, puhelin"
                + " FROM Asiakas WHERE asiakasId = ?"; // ehdon arvo asetetaan jäljempänä
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            /*
             * luo PreparedStatement-olio sql-lauseelle
             */
            lause = c.prepareStatement(sql);
            lause.setInt(1, asiakasId); // asetetaan where ehtoon (?) arvo
            /*
             * suorita sql-lause
             */
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Asiakasta ei loydy");
            }
        } catch (Exception e) {
            /*
             * JDBC virheet
             */
            throw e;
        }
        /**
         * käsitellään resultset - laitetaan tiedot AsiakasOliolle
         */
        Asiakas AsiakasOlio = new Asiakas();

        try {
            if (tulosjoukko.next() == true) {
                //etunimi, sukunimi, organisaatio, email, lahiosoite, postinumeo, puhelin
                AsiakasOlio.setEtunimi(tulosjoukko.getString("etunimi"));
                AsiakasOlio.setSukunimi(tulosjoukko.getString("sukunimi"));
                AsiakasOlio.setOrganisaatio(tulosjoukko.getString("organisaatio"));
                AsiakasOlio.setEmail(tulosjoukko.getString("email"));
                AsiakasOlio.setLahiosoite(tulosjoukko.getString("lahiosoite"));
                AsiakasOlio.setPostinumero(tulosjoukko.getString("postinumero"));
                AsiakasOlio.setToimipaikka(tulosjoukko.getString("toimipaikka"));
                AsiakasOlio.setPuhelin(tulosjoukko.getString("puhelin"));
                
            }

        } catch (SQLException e) {
            throw e;
        }
        /**
         * palautetaan AsiakasOlio
         */

        return AsiakasOlio;

    }

    
    
}
