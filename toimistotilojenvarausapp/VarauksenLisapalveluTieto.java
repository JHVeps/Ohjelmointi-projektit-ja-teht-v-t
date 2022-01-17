package toimistotilojenvarausapp;

import java.sql.Date;

/**
 * Luokka mitä käytetään varauksen lisäpalveluiden tietojen esittämiseen
 *
 * @author Joni Vepsäläinen
 * @version 1.0
 */
public class VarauksenLisapalveluTieto {

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
     * Palvelun id numero
     */
    private Integer palveluId;
    /**
     * Palvelun nimi
     */
    private String palvelunNimi;
    /**
     * varauksen aloitus päivämäärä
     */
    private Date aloitusPvm = null;
    /**
     * varauksen lopetus päivämäärä
     */
    private Date lopetusPvm = null;

    /**
     * Olion tyhjä oletus konstruktori
     */
    public VarauksenLisapalveluTieto() {
    }

    /**
     * Olion konstruktori tallentaa VarauksenLisaPalveluTieto olion varauksen id
     * numeron, asiakkaan id numeron, etunimi, sukunimen, palvelun id numeron ja
     * nimen, laitteen Id numeron ja nimen sekä varauksen aloitus ja lopetus
     * päivämäärän
     *
     * @param varausId varaus olion id numero kokonaislukuna
     * @param asiakasId asiakas olion id numero kokonaislukuna
     * @param etunimi asiakas olion etunimi merkkijono
     * @param sukunimi asiakas olion sukunimi merkkijono
     * @param palveluId palvelu olion id numero kokonaislukuna
     * @param palvelunNimi palvelun nimi merkkijono
     * @param aloitusPvm varaus olion aloituspvm Date tyyppi
     * @param lopetusPvm varaus olion lopetusPvm Date tyyppi
     */
    public VarauksenLisapalveluTieto(Integer varausId, Integer asiakasId, String etunimi, String sukunimi, Integer palveluId,
            String palvelunNimi, Date aloitusPvm, Date lopetusPvm) {
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.palveluId = palveluId;
        this.palvelunNimi = palvelunNimi;
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
     * Palautetaan palvelun id
     *
     * @return int palvelu olion id numero
     */
    public Integer getPalveluId() {
        return palveluId;
    }

    /**
     * Palautetaan toimipisteen nimi
     *
     * @return string toimipiste olion nimi
     */
    public String getPalvelunNimi() {
        return palvelunNimi;
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

    //Setterit
    /**
     * Asetetaan varaus olion varauksen ID
     *
     * @param varausId varausId
     */
    public void setVarausId(Integer varausId) {
        this.varausId = varausId;
    }

    /**
     * Asetetaan asiakas olion asiakas ID
     *
     * @param asiakasId asiakasId
     */
    public void setAsiakasId(Integer asiakasId) {
        this.asiakasId = asiakasId;
    }

    /**
     * Asetetaan asiakas olion etunimi
     *
     * @param etunimi etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    /**
     * Asetetaan asiakas olion sukunimi
     *
     * @param sukunimi sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    /**
     * Asetetaan palvelu olion palvelun ID
     *
     * @param palveluId palveluId
     */
    public void setPalveluId(Integer palveluId) {
        this.palveluId = palveluId;
    }

    /**
     * Asetetaan palvelu olion nimi
     *
     * @param palvelunNimi palvelunNimi
     */
    public void setPalvelunNimi(String palvelunNimi) {
        this.palvelunNimi = palvelunNimi;
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
        return (this.varausId + " " + this.asiakasId + " " + this.etunimi + " " + this.sukunimi + " " + this.palveluId + " "
                + this.palvelunNimi + " " + this.aloitusPvm + " " + this.lopetusPvm);
    }


}
