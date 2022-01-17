package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Palvelu luokka sisältää palvelu olion atribuutit (tärkeimpänä atribuuttinaan olion yksilöivä id-tunnus) ja metodin
 * palvelu-olion tietojen palauttamiseen
 * 
 * @author Sami Männistö
 * @version 1.0
 */

public class Palvelu {

    /**
     * Palvelun id numero
     */
    private Integer palveluId;
    /**
     * Palvelun nimi
     */
    private String palvelunNimi = null;
    /**
     * Palvelun hinta
     */
    private Double hinta = null;
    /**
     * Palvelun tyyppi
     */
    private Integer tyyppi = null;
    /**
     * Palvelun kuvaus
     */
    private String kuvaus = null;
    /**
     * Palvelun toimipiste
     */
    private Integer toimipisteId = null;

    /**
     * Oletus konstruktori
     */
    public Palvelu() {

    }

    /**
     * Konstruktori joka tallentaa kaikki oleelliset tiedot palveluista
     *
     * @param palveluId palvelun ID
     * @param hinta palvelun hinta
     * @param palvelunNimi palvelun nimi
     * @param tyyppi palvelun tyyppi
     * @param kuvaus palvelun kuvaus
     * @param toimipisteId palvelun toimipiste
     */
    public Palvelu(Integer palveluId, String palvelunNimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId) {
        this.palveluId = palveluId;
        this.palvelunNimi = palvelunNimi;
        this.hinta = hinta;
        this.tyyppi = tyyppi;
        this.kuvaus = kuvaus;
        this.toimipisteId = toimipisteId;
    }

    public Integer getToimipisteId() {
        return toimipisteId;
    }

    /**
     * Alhaalla ovat getterit ja setterit jotka asettavat/palauttavat tietoja palveluista
     */
    /**
     * 
     * @return palvelun ID tunnus
     */
    public Integer getPalveluId() {
        return palveluId;
    }
    /**
     * 
     * @param palveluId palvelun ID tunnus
     */
    public void setPalveluId(Integer palveluId) {
        this.palveluId = palveluId;
    }
    /**
     * 
     * @return palvelun nimi
     */
    public String getNimi() {
        return palvelunNimi;
    }
    /**
     * 
     * @param palvelunNimi palvelun nimi
     */
    public void setNimi(String palvelunNimi) {
        this.palvelunNimi = palvelunNimi;
    }
    /**
     * 
     * @return palvelun hinta
     */
    public Double getHinta() {
        return hinta;
    }
    /**
     * 
     * @param hinta palvelun hinta
     */
    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }
    /**
     * 
     * @return palvelun tyyppi
     */
    public Integer getTyyppi() {
        return tyyppi;
    }
    /**
     * 
     * @return palvelun kuvaus
     */
    public String getKuvaus() {
        return kuvaus;
    }
    /**
     * 
     * @param kuvaus palvelun kuvaus
     */
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    /**
     * 
     * @param tyyppi palvelun tyyppi
     */
    public void setTyyppi(Integer tyyppi) {
        this.tyyppi = tyyppi;
    }
    /**
     * 
     * @param toimipisteId palvelun toimipisteen ID
     */
    public void setToimipisteId(Integer toimipisteId) {
        this.toimipisteId = toimipisteId;
    }

    /**
     * palvelun tietojen palautus merkkijonona
     *
     * @return palvelun tiedot merkkijonona
     */
    @Override
    public String toString() {
        return (this.palveluId + " " + this.palvelunNimi + " " + this.hinta + " " + this.kuvaus);
    }

    /** Metodi palveluiden tietojen hakemiseen ja palauttamiseen
     *
     * @param c tietokantayhteys
     * @param palveluId palvelun identiteetti
     * @return palvelun tiedot sisältävä olio
     * @throws SQLException SQL virheet
     * @throws Exception muut virheet
     */

    public static Palvelu searchPalvelu(Connection c, Integer palveluId) throws SQLException, Exception {
        String sql = "SELECT palveluId, palvelunNimi, hinta, tyyppi, kuvaus, toimipisteId"
                + " FROM Palvelu WHERE palveluId = ?";
        ResultSet tulos = null;
        PreparedStatement lause = null;
        try {
            lause = c.prepareStatement(sql);
            lause.setInt(1, palveluId);
            tulos = lause.executeQuery();
            if (tulos == null) {
                throw new Exception("Palvelua ei löytynyt tietokannasta");
            }
        } catch (Exception e) {
            throw e;
        }

        Palvelu PalveluOlio = new Palvelu();

        try {
            if (tulos.next() == true) {
                PalveluOlio.setNimi(tulos.getString("palveluId"));
                PalveluOlio.setNimi(tulos.getString("palvelunNimi"));
                PalveluOlio.setHinta(Double.valueOf(tulos.getString("hinta")));
                PalveluOlio.setTyyppi(Integer.valueOf(tulos.getString("tyyppi")));
                PalveluOlio.setKuvaus(tulos.getString("kuvaus"));
                PalveluOlio.setToimipisteId(Integer.valueOf(tulos.getString("toimipisteId")));
            }

        } catch (SQLException e) {
            throw e;
        }

        return PalveluOlio;
    }

}
