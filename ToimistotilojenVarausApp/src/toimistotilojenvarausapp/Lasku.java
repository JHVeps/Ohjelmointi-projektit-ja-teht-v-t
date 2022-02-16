package toimistotilojenvarausapp;
/*

CREATE TABLE Lasku
(
  summa DOUBLE NOT NULL,
  erapaiva VARCHAR(10) NOT NULL,
  viitenumero INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  varausId INT NOT NULL,
  asiakasId INT NOT NULL,
  FOREIGN KEY (varausId) REFERENCES Varaus(varausId),
  FOREIGN KEY (asiakasId) REFERENCES Asiakas(asiakasId)
);

*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Lasku luokka sisältää lasku olion atribuutit (tärkeimpinä atribuuttinaan olion yksilöivä viitenumero-tunnus, varauksen id-tunnus ja
 * asiakkaan id -tunnus) ja metodin asiakas-olion tietojen palauttamiseen
 * 
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class Lasku {
    /**
     * Laskun summa
     */
    private Double summa = null;
    /**
     * Laskun eräpäivä
     */
    private String erapaiva = null;
    /**
     * Laskun yksilöivä viitenumero
     */
    private Integer viitenumero;
    /**
     * Laskun varauksen ID
     */
    private Integer varausId;
    /**
     * Laskun asiakkaan ID
     */
    private Integer asiakasId;

    public Lasku() {
    }

    /**
     * Olion konstruktori tallentaa lasku olion summan, eräpäivän, viitenumeron
     * varausId:n ja asiakasId:n
     *
     * @param summa olion summa desimaalilukuna
     * @param erapaiva olion eräpäivä merkkijojona
     * @param viitenumero olion viitenumero kokonaislukuna
     * @param varausId olion varauksen ID kokonaislukuna
     * @param asiakasId olion asiakas ID kokonaislukuna
     */
    public Lasku(Double summa, String erapaiva, Integer viitenumero, Integer varausId, Integer asiakasId) {
        this.summa = summa;
        this.erapaiva = erapaiva;
        this.viitenumero = viitenumero;
        this.varausId = varausId;
        this.asiakasId = asiakasId;
    }

    //Getterit
    /**
     * Palautetaan laskun summa
     *
     * @return double lasku olion summa
     */
    public Double getSumma() {
        return summa;
    }

    /**
     * Palautetaan eräpäivä
     *
     * @return String lasku olion eräpäivä
     */
    public String getErapaiva() {
        return erapaiva;
    }

    /**
     * Palautetaan viitenumero
     *
     * @return Integer lasku olion viitenumero
     */
    public Integer getViitenumero() {
        return viitenumero;
    }

    /**
     * Palautetaan laskun varauksen ID
     *
     * @return Integer lasku olion varauksen ID
     */
    public Integer getVarausId() {
        return varausId;
    }
    
    /**
     * Palautetaan laskun asiakkaan ID
     *
     * @return Integer lasku olion asiakas ID
     */
    public Integer getAsiakasId() {
        return asiakasId;
    }    
    
    //Setterit
    /**
     * Asetetaan lasku olion summa tieto
     *
     * @param summa laskun summa
     */
    public void setSumma(Double summa) {
        this.summa = summa;
    }

    /**
     * Asetetaan lasku olion eräpäivä tieto
     *
     * @param erapaiva laskun eräpäivä
     */
    public void setErapaiva(String erapaiva) {
        this.erapaiva = erapaiva;
    }

    /**
     * Asetetaan lasku olion viitenumero tieto
     *
     * @param viitenumero laskun yksilöivä viitenumero
     */
    public void setViitenumero(Integer viitenumero) {
        this.viitenumero = viitenumero;
    }

    /**
     * Asetetaan lasku olion laskun varauksen ID tieto
     *
     * @param varausId varauksen ID
     */
    public void setVarausId(Integer varausId) {
        this.varausId = varausId;
    }
    
    /**
     * Asetetaan lasku olion laskun asiakkaan ID tieto
     *
     * @param asiakasId asiakkaan ID
     */
    public void setAsiakasId(Integer asiakasId) {
        this.asiakasId = asiakasId;
    }
    
    //metodit
    /**
     * Palautetaan lasku oliota kuvaava merkkijono
     *
     * @return laskun tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.summa + " " + this.erapaiva + " " + this.viitenumero + " " + this.varausId + " " + this.asiakasId);
    }

    /**
     * Haetaan laskun tiedot SQL - tietokannasta ja palautetaan
     * laskuolio kutsujalle. Staattinen metodi, ei vaadi fyysisen olion
     * olemassaoloa
     *
     * @param c tietokantayhteys parametri
     * @param viitenumero laskun viitenumero parametri
     * @throws SQLException SQL - virheet
     * @throws Exception muut virheet
     * @return LaskuOlio laskun tiedot sisältävä olio
     */
    public static Lasku searchLasku(Connection c, Integer viitenumero) throws SQLException, Exception { // tietokantayhteys välitetään parametrina
        // haetaan tietokannasta laskua, jonka viitenumero = viitenumero 
        String sql = "SELECT summa, viitenumero, varausId, asiakasId "
                + " FROM Lasku WHERE viitenumero = ?"; // ehdon arvo asetetaan jäljempänä
        ResultSet tulosjoukko = null;
        PreparedStatement lause = null;
        try {
            /*
             * luo PreparedStatement-olio sql-lauseelle
             */
            lause = c.prepareStatement(sql);
            lause.setInt(1, viitenumero); // asetetaan where ehtoon (?) arvo
            /*
             * suorita sql-lause
             */
            tulosjoukko = lause.executeQuery();
            if (tulosjoukko == null) {
                throw new Exception("Laskua ei loydy");
            }
        } catch (Exception e) {
            /*
             * JDBC virheet
             */
            throw e;
        }
        /**
         * käsitellään resultset - laitetaan tiedot LaskuOliolle
         */
        Lasku laskuOlio = new Lasku();

        try {
            if (tulosjoukko.next() == true) {
                //summa, viitenumero, varausId, asiakasId
                laskuOlio.setSumma(tulosjoukko.getDouble("summa"));
                laskuOlio.setViitenumero(tulosjoukko.getInt("viitenumero"));
//                laskuOlio.setVarausId(tulosjoukko.getInt("varausId"));
//                laskuOlio.setAsiakasId(tulosjoukko.getInt("asiaksId"));

            }

        } catch (SQLException e) {
            throw e;
        }
        /**
         * palautetaan laskuOlio
         */

        return laskuOlio;

    }

    
    
    
}
