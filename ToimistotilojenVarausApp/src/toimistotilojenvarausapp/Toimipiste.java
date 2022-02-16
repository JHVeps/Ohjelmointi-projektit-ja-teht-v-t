package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sami Männistö
 * @version 1.0
 */

public class Toimipiste {

    /**
     * Toimipisteen id numero
     */
    private Integer toimipisteId;
    /**
     * Toimipisteen nimi
     */
    private String nimi = null;
    /**
     * Toimipisteen osoite
     */
    private String osoite = null;
    /**
     * Toimipisteen kuvaus
     */
    private String kuvaus = null;


    /**
     * Oletus konstruktori
     */
    public Toimipiste() {

    }

    /**
     * Konstruktori joka tallentaa kaikki oleelliset tiedot toimipisteestä
     *
     * @param toimipisteId
     * @param nimi
     * @param osoite
     * @param kuvaus
     */
    public Toimipiste(Integer toimipisteId, String nimi, String osoite, String kuvaus) {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.osoite = osoite;
        this.kuvaus = kuvaus;
    }

    public Integer getToimipisteId() {
        return toimipisteId;
    }

    /**
     * Alhaalla ovat getterit ja setterit jotka asettavat/palauttavat tietoja toimipisteistä
     */

    public void setToimipisteId(Integer toimipisteId) {
        this.toimipisteId = toimipisteId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    /**
     * toimipisteen kuvauksen palautus merkkijonona
     *
     * @return
     */
    @Override
    public String toString() {
        return (this.toimipisteId + " " + this.nimi + " " + this.osoite + " " + this.kuvaus);
    }

    /** Metodi toimipisteiden tietojen hakemiseen ja palauttamiseen
     *
     * @param c tietokantayhteys
     * @param toimipisteId toimipisteen identiteetti
     * @return
     * @throws SQLException
     * @throws Exception
     */

    public static Toimipiste searchToimipiste(Connection c, Integer toimipisteId) throws SQLException, Exception {
        String sql = "SELECT toimipisteId, nimi, osoite, kuvaus"
                + " FROM Toimipiste WHERE toimipisteId = ?";
        ResultSet tulos = null;
        PreparedStatement lause = null;
        try {
            lause = c.prepareStatement(sql);
            lause.setInt(1, toimipisteId);
            tulos = lause.executeQuery();
            if (tulos == null) {
                throw new Exception("Toimipistettä ei löytynyt tietokannasta");
            }
        } catch (Exception e) {
            throw e;
        }

        Toimipiste ToimipisteOlio = new Toimipiste();

        try {
            if (tulos.next() == true) {
                ToimipisteOlio.setNimi(tulos.getString("nimi"));
                ToimipisteOlio.setOsoite(tulos.getString("osoite"));
                ToimipisteOlio.setKuvaus(tulos.getString("kuvaus"));
            }

        } catch (SQLException e) {
            throw e;
        }

        return ToimipisteOlio;
    }


}