package toimistotilojenvarausapp;

import java.sql.Date;

/**
 * VarausTieto luokka minkä atribuuteiksi haetaan tblview komponenttiin
 * haluttavat tiedot niiden esitystä varten
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class VarausTieto {

    /**
     * Varauksen id numero
     */
    private Integer varausId;
    /**
     * Asiakkaan id numero
     */
    private Integer asiakasId;
    /**
     * Asiakkaan etunimi
     */
    private String etunimi;
    /**
     * Asiakkaan sukunimi
     */
    private String sukunimi;
    /**
     * Toimipisteen id numero
     */
    private Integer toimipisteId;
    /**
     * Toimipisteen nimi
     */
    private String nimi;
    /**
     * varauksen aloitus päivämäärä
     */
    private Date aloitusPvm = null;
    /**
     * varauksen lopteus päivämäärä
     */
    private Date lopetusPvm = null;

    /**
     * Olion tyhjä oletus konstruktori
     */
    public VarausTieto() {
    }

    /**
     * Olion konstruktori tallentaa VarausTieto olion id numeron, asiakkaan id
     * numeron, toimipisteen id numeron, aloitus ja lopetus päivämäärän
     *
     * @param varausId varaus olion id numero kokonaislukuna
     * @param asiakasId asiakas olion id numero kokonaislukuna
     * @param etunimi asiakas olion etunimi merkkijono
     * @param sukunimi asiakas olion sukunimi merkkijono
     * @param toimipisteId toimipiste olion id numero kokonaislukuna
     * @param nimi toimipisteen nimi merkkijono
     * @param aloitusPvm varaus olion aloituspvm Date tyyppi
     * @param lopetusPvm varaus olion lopetusPvm Date tyyppi
     */
    public VarausTieto(Integer varausId, Integer asiakasId, String etunimi, String sukunimi, Integer toimipisteId, String nimi, Date aloitusPvm, Date lopetusPvm) {
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.toimipisteId = toimipisteId;
        this.nimi = nimi;
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
        return varausId;
    }

    /**
     * Palautetaan asiakkaan id numero
     *
     * @return int asiakas olion id numero
     */
    public Integer getAsiakasId() {
        return asiakasId;
    }

    /**
     * Palautetaan asiakkaan etunimi
     *
     * @return string asiakas olion etunimi
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     * Palautetaan asiakkaan sukunimi
     *
     * @return string asiakas olion sukunimi
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     * Palautetaan toimipisteen id
     *
     * @return int toimipiste olion id numero
     */
    public Integer getToimipisteId() {
        return toimipisteId;
    }

    /**
     * Palautetaan toimipisteen nimi
     *
     * @return string toimipiste olion nimi
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * Palautetaan varauksen aloitus päivämäärä
     *
     * @return Date varaus olion aloituspvm
     */
    public Date getAloitusPvm() {
        return aloitusPvm;
    }

    /**
     * Palautetaan varaus olion lopetus päivämäärä
     *
     * @return Date varaus olion lopetuspvm
     */
    public Date getLopetusPvm() {
        return lopetusPvm;
    }

    /**
     * Asetetaan varaus olion aloitus päivämäärä
     *
     * @param aloitusPvm aloitusPvm
     */
    public void setAloitusPvm(Date aloitusPvm) {
        this.aloitusPvm = aloitusPvm;
    }

    /**
     * Asetetaan varaus olion lopetus päivämäärä
     *
     * @param lopetusPvm lopetusPvm
     */
    public void setLopetusPvm(Date lopetusPvm) {
        this.lopetusPvm = lopetusPvm;
    }

    //metodit
    /**
     * Palautetaan VarausTieto oliota kuvaava merkkijono
     *
     * @return VarausTieto tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.varausId + " " + this.asiakasId + " " + this.etunimi + " " + this.sukunimi + " " + this.toimipisteId + " " + this.nimi + " " + this.aloitusPvm + " " + this.lopetusPvm);
    }

}
