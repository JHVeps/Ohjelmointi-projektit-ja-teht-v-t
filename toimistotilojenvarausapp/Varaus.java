package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Varaus luokka sisältää varaus olion atribuutit (tärkeimpänä atribuuttinaan olion yksilöivä id-tunnus) ja metodin
 * varaus-olion tietojen palauttamiseen
 * 
 * @author Leevi Meriläinen
 */
public class Varaus extends Asiakas {
       /**
     * Varauksen id numero
     */
    private Integer varausID;
       /**
     * Varauksen asiakkaan id numero
     */
    private Integer asiakasID;
       /**
     * Varauksen toimipisteen id numero
     */
    private Integer toimipisteID;
    /**
     * Varaus aloitus merkkijonona
     */
    private Date aloitusPvm = null;
    /**
     * Varaus lopetus merkkijonona
     */
    private Date lopetusPvm = null;
   
    /**
     * Olion tyhjä oletus konstruktori
     */
    public Varaus() {
    }
    
    /**
     * @param varausID olion id numero kokonaislukuna
     * @param asiakasID olion asiakas id numero kokonaislukuna
     * @param toimipisteID olion toimipiste id numero kokonaislukuna
     * @param aloitusPvm olion aloitus pvm
     * @param lopetusPvm olion lopetus pvm
     */
    public Varaus(Integer varausID, Integer asiakasID, Integer toimipisteID, Date aloitusPvm, Date lopetusPvm) {
        this.varausID = varausID;
        this.asiakasID = asiakasID;
        this.toimipisteID = toimipisteID;
        this.aloitusPvm = aloitusPvm;
        this.lopetusPvm = lopetusPvm;
    }

    //Getterit
    /**
     * Palautetaan varauksen id numero
     *
     * @return int varaus olion id numero
     */
    public Integer getVarausId() {
        return varausID;
    }
    
    /**
     * Palautetaan varauksen asikaan id numero
     *
     * @return int varaus olion id numero
     */
    public Integer getasiakasId() {
        return asiakasID;
    }
    
    /**
     * Palautetaan varauksen id numero
     *
     * @return int varaus olion id numero
     */
    public Integer gettoimipisteId() {
        return toimipisteID;
    }

    /**
     * Palautetaan alotusPvm
     *
     * @return String varaus olion aloitusPvm
     */
    public Date getaloitusPvm() {
        return aloitusPvm;
    }
    
     /**
     * Palautetaan lopetusPvm 
     *
     * @return String varaus olion lopetusPvm
     */
    
    public Date getlopetusPvm() {
        return lopetusPvm;
    }
    
    //Setterit
    /**
     * Asetetaan varaus olion aloitusPvm tieto
     *
     * @param aloitusPvm varauksen aloitus päivämäärä
     */
    public void setaloitusPvm(Date aloitusPvm) {
        this.aloitusPvm = aloitusPvm;
    }

    /**
     * Asetetaan varaus olion lopetusPvm tieto
     *
     * @param lopetusPvm  varauksen lopetus päivämäärä
     */
    public void setlopetusPvm(Date lopetusPvm) {
        this.lopetusPvm = lopetusPvm;
    }
    
    /**
     * Asetetaan varaus olion asiakasId tieto
     *
     * @param asiakasID asiakkaan ID
     */
    public void setasiakasID(Integer asiakasID) {
        this.asiakasID = asiakasID;
    }
    
    /**
     * Asetetaan varaus olion toimipisteID tieto
     *
     * @param toimipisteID toimipisteen ID
     */
    public void settoimipisteID(Integer toimipisteID) {
        this.toimipisteID = toimipisteID;
    }

    
    //metodit
    /**
     * Palautetaan Varaus oliota kuvaava merkkijono
     *
     * @return Varauksen tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.varausID + " " + this.asiakasID + " " + this.toimipisteID + " " + this.aloitusPvm + " " + this.lopetusPvm);
    }

    /**
     * Haetaan varauksen tiedot SQL - tietokannasta ja palautetaan
     * varausolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param varausID varauksen ID
     * @throws SQLException SQL-virheet
     * @throws Exception muur virheet
     * @return VarausOlio
     */
    public static Varaus searchVaraus(Connection c, Integer varausID) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // haetaan tietokannasta varausta, jonka varausId = varausId 
        String sql = "SELECT varausID, asiakasID, toimipisteID, aloitusPvm, lopetusPvm"
                + " FROM Varaus WHERE varausID = ?"; // ehdon arvo asetetaan jäljempänä
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            /*
             * luo PreparedStatement-olio sql-lauseelle
             */
            lause = c.prepareStatement(sql);
            lause.setInt(1, varausID); // asetetaan where ehtoon (?) arvo
            /*
             * suorita sql-lause
             */
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Varausta ei loydy");
            }
        } catch (Exception e) {
            /*
             * JDBC virheet
             */
            throw e;
        }
        /**
         * käsitellään resultset - laitetaan tiedot VarausOliolle
         */
        Varaus VarausOlio = new Varaus();

        try {
            if (tulosjoukko.next() == true) {
                //aloitusPvm, lopetusPvm, asiakasID, toimipisteID
                VarausOlio.setaloitusPvm(tulosjoukko.getDate("aloitusPvm"));
                VarausOlio.setlopetusPvm(tulosjoukko.getDate("lopetusPvm"));
                VarausOlio.setasiakasID(tulosjoukko.getInt("asiakasID"));
                VarausOlio.settoimipisteID(tulosjoukko.getInt("toimipisteID"));

            }

        } catch (SQLException e) {
            throw e;
        }
        /**
         * palautetaan VarausOlio
         */

        return VarausOlio;

    }
}
