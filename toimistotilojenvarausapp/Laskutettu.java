/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toimistotilojenvarausapp;

import java.sql.Date;

/**
 *
 * LaskutettavaAsiakas luokka sisältää asiakas olion atribuutit ja metodin
 * asiakas-olion tietojen palauttamiseen
 *
 * @author Joni Vepsäläinen, 2006234
 * @version 1.0
 */
public class Laskutettu {

    /**
     * Laskun viitenumero
     */
    private Integer viitenumero;
    /**
     * varaus ID
     */
    private Integer varausId;
    /**
     * Asiakas ID
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
     * Laskun summa
     */
    private Double summa = null;
    /**
     * Laskun erapaiva
     */
    private Date erapaiva = null;

    /**
     * Laskun muoto
     */
    private String laskutustapa = null;

    public Laskutettu() {
    }

    public Laskutettu(Integer viitenumero, Integer varausId, Integer asiakasId, String etunimi, String sukunimi, Double summa,
          Date erapaiva, String laskutustapa) {
        this.viitenumero = viitenumero;
        this.varausId = varausId;
        this.asiakasId = asiakasId;
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.summa = summa;
        this.erapaiva = erapaiva;
        this.summa = summa;
       
    }

    //Getterit
    /**
     * Palautetaan viitenumero
     *
     * @return Integer lasku olion viitenumero
     */
    public Integer getViitenumero() {
        return viitenumero;
    }

    /**
     * Palautetaan varaus ID
     *
     * @return Integer varauksen ID
     */
    public Integer getVarausId() {
        return varausId;
    }

    /**
     * Palautetaan asiakas ID
     *
     * @return Integer asiakkaan ID
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
     * Palautetaan summa
     *
     * @return Double laskutettu summa
     */
    public Double getSumma() {
        return summa;
    }  
     /**
     * Palautetaan laskun eräpäivä
     *
     * @return Date eräpäivä
     */
    public Date getErapaiva() {
        return erapaiva;
    }
      /**
     * Palautetaan laskutustapa
     *
     * @return String laskutustapa
     */
    public String getLaskutustapa() {
        return laskutustapa;
    }
    //Setterit
    /**
     * Asetetaan lasku olion viitenumero tieto
     *
     * @param viitenumero viitenumero
     */
    public void setViitenumero(Integer viitenumero) {
        this.viitenumero = viitenumero;
    }

    /**
     * Asetetaan varaus ID
     *
     * @param varausId varaus ID
     */
    public void setVarausId(Integer varausId) {
        this.varausId = varausId;
    }
   /**
     * Asetetaan asiakas ID
     *
     * @param asiakasId asiakas ID
     */
    public void setAsiakasId(Integer asiakasId) {
        this.asiakasId = asiakasId;
    }
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
     * Asetetaan summa
     *
     * @param summa summa
     */
    public void setSumma(Double summa) {
        this.summa = summa;
    }    
  
    /**
     * Asetetaan olion erapäivä
     *
     * @param erapaiva lopetus päivämäärä
     */
    public void setErapaiva(Date erapaiva) {
        this.erapaiva = erapaiva;
    }
  /**
     * Asetetaan laskutustapa
     *
     * @param laskutustapa laskutustapa
     */
    public void setLaskutustapa(String laskutustapa) {
        this.laskutustapa = laskutustapa;
    }
    //metodit
    /**
     * Palautetaan laskutettua oliota kuvaava merkkijono
     *
     * @return laskutetun tiedot merkkijonona.
     */
    @Override
    public String toString() {
        return (this.viitenumero + " " + this.varausId + " " + this.asiakasId + " " + this.etunimi + " " + 
                this.sukunimi + " " + this.summa + " " + this.erapaiva + " " +this.laskutustapa);
    }

}

