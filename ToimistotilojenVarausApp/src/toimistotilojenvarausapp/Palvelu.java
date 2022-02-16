package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
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
    private String nimi = null;
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
     * @param palveluId
     * @param hinta
     * @param nimi
     * @param tyyppi
     * @param kuvaus
     * @param toimipisteId
     */
    public Palvelu(Integer palveluId, String nimi, Double hinta, Integer tyyppi, String kuvaus, Integer toimipisteId) {
        this.palveluId = palveluId;
        this.nimi = nimi;
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

    public Integer getPalveluId() {
        return palveluId;
    }

    public void setPalveluId(Integer palveluId) {
        this.palveluId = palveluId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Double getHinta() {
        return hinta;
    }

    public void setHinta(Double hinta) {
        this.hinta = hinta;
    }

    public Integer getTyyppi() {
        return tyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public void setTyyppi(Integer tyyppi) {
        this.tyyppi = tyyppi;
    }

    public void setToimipisteId(Integer toimipisteId) {
        this.toimipisteId = toimipisteId;
    }

    /**
     * palvelun tietojen palautus merkkijonona
     *
     * @return
     */
    @Override
    public String toString() {
        return (this.palveluId + " " + this.nimi + " " + this.hinta + " " + this.kuvaus);
    }

    /** Metodi palveluiden tietojen hakemiseen ja palauttamiseen
     *
     * @param c tietokantayhteys
     * @param palveluId palvelun identiteetti
     * @return
     * @throws SQLException
     * @throws Exception
     */

    public static Palvelu searchPalvelu(Connection c, Integer palveluId) throws SQLException, Exception {
        String sql = "SELECT palveluId, nimi, hinta, tyyppi, kuvaus, toimipisteId"
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
                PalveluOlio.setNimi(tulos.getString("nimi"));
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
