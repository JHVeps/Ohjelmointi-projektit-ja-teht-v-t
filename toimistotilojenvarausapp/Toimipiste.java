package toimistotilojenvarausapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Toimipiste luokka sisältää toimipiste olion atribuutit (tärkeimpänä atribuuttinaan olion yksilöivä id-tunnus) ja metodin
 * toimipiste-olion tietojen palauttamiseen
 * 
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
     * Toimipisteen lahiosoite
     */
    private String lahiosoite = null;
    /**
     * Toimipisteen postinro
     */
    private String postinro = null;
    /**
     * Toimipisteen toimipaikka
     */
    private String toimipaikka = null;
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
     * @param toimipisteId toimipisteen ID
     * @param nimi toimipisteen nimi
     * @param lahiosoite toimipisteen lähiosoite
     * @param toimipaikka toimipisteen toimipaikka
     * @param postinro toimipisteen postinumero
     * @param kuvaus toimipisteen kuvaus
     */
    public Toimipiste(Integer toimipisteId, String nimi, String lahiosoite, String postinro, String toimipaikka, String kuvaus) {
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
        this.lahiosoite = lahiosoite;
        this.postinro = postinro;
        this.toimipaikka = toimipaikka;
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

    public String getLahiosoite() {
        return lahiosoite;
    }

    public void setLahiosoite(String lahiosoite) {
        this.lahiosoite = lahiosoite;
    }

    public String getToimipaikka() {
        return toimipaikka;
    }

    public void setToimipaikka(String toimipaikka) {
        this.toimipaikka = toimipaikka;
    }

    public String getPostinro() {
        return postinro;
    }

    public void setPostinro(String postinro) {
        this.postinro = postinro;
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
        return (this.toimipisteId + " " + this.nimi + " " + this.lahiosoite + this.postinro + " " + this.toimipaikka + " " + this.kuvaus);
    }

    /** Metodi toimipisteiden tietojen hakemiseen ja palauttamiseen
     *
     * @param c tietokantayhteys
     * @param toimipisteId toimipisteen identiteetti
     * @return toimipisteen tiedot sisälätävä ToimipisteOlio
     * @throws SQLException SQL-virheet
     * @throws Exception muut virheet
     */

    public static Toimipiste searchToimipiste(Connection c, Integer toimipisteId) throws SQLException, Exception {
        String sql = "SELECT toimipisteId, nimi, lahiosoite, postinro, toimipaikka, kuvaus"
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
                ToimipisteOlio.setLahiosoite(tulos.getString("lahiosoite"));
                ToimipisteOlio.setPostinro(tulos.getString("postinro"));
                ToimipisteOlio.setToimipaikka(tulos.getString("toimipaikka"));
                ToimipisteOlio.setKuvaus(tulos.getString("kuvaus"));
            }

        } catch (SQLException e) {
            throw e;
        }

        return ToimipisteOlio;
    }


}