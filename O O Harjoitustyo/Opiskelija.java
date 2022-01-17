/**
 * 
 * @version 1.0
 * @author Joni Vepsäläinen
 */
package opiskelija.ominaisuudet;
import java.util.*;
//import java.io.*;
import java.io.Serializable;
//Luokka ja atribuutit
/** Luokka Opiskelija sisältää tarvittavat atribuutit ja metodin opiskelija-olion tietojen palauttamiseen*/
public class Opiskelija implements Serializable {	
	/**Opiskelijan id numero*/
	protected int m_opiskelija_id;
	/**Opiskelijan etunimi*/
	protected String m_etunimi;
	/**Opiskelijan sukunimi*/
	protected String m_sukunimi;	
	/**Opiskelijan lähiosoite*/
	protected String m_lahiosoite;
	/**Opiskelijan postitoimipaikka*/
	protected String m_postitoimipaikka;
	/**Opiskelijan postinumero*/
	protected String m_postinro;
	/**Opiskelijan sähköpostiosoite*/	
	protected String m_email;
	/**Opiskelijan puhelinnumero*/
	protected String m_puhelinnro;
	/**Opiskelijan opiskelujen suoritustiedot*/
	//protected LinkedList<Suoritus> m_suoritukset = new LinkedList<Suoritus>();
	
	//konstruktorit
	/** Olion tyhjä oletus konstruktori   
	 */
	public Opiskelija() {
	}
	/** Olion konstruktori tallentaa olion id numeron, etunimen, sukunimen, lähiosoitteen, postitoimipaikan, postinumeron, sähköpostiosoitteen ja puhelinnumeron.
	 *	@param opiskelija_id olion id numero kokonaislukuna
	 *	@param etunimi olion etunimi merkkijonona
	 *	@param sukunimi olion sukunimi merkkijonona
	 *	@param lahiosoite olion lähiosoite merkkijonona
	 *	@param postitoimipaikka olion postitoimipaikka merkkijonona
	 *	@param postinro olion postinumero merkkijonona
	 *	@param email olion sähköpostiosoite merkkijonona
	 *	@param puhelinnro olion puhelinnumero merkkijonona
	 */
    public Opiskelija(int opiskelija_id, String etunimi, String sukunimi, String lahiosoite, 
						String postitoimipaikka, String postinro, String email, String puhelinnro ) {
		
		this.m_opiskelija_id = opiskelija_id;
		this.m_etunimi = etunimi;
		this.m_sukunimi = sukunimi;	
		this.m_lahiosoite = lahiosoite;
		this.m_postitoimipaikka = postitoimipaikka;
		this.m_postinro = postinro;	
		this.m_email = email;	
		this.m_puhelinnro = puhelinnro;
		//this.m_suoritukset = null;
	}
		//Getterit
		/** Palautetaan id numero
		 *	@return int olion id numero kokonaislukuna
		 */
		public int getOpiskelija_id() {
			return m_opiskelija_id;
		}
		/** Palautetaan etunimi
		 *	@return String olion etunimi merkkijonona
		 */		
		public String getEtunimi() {
			return m_etunimi;
		}
		/** Palautetaan sukunimi
		 *	@return String opiskelija olion sukunimi merkkijonona
		 */		
		public String getSukunimi() {
			return m_sukunimi;
		}
		/** Palautetaan lähiosoite
		 *	@return String opiskelija olion lähiosoite merkkijonona
		 */			
		public String getLahiosoite() {
			return m_lahiosoite;
		}
		/** Palautetaan postitoimipaikka
		 *	@return String opiskelija olion postitoimipaikka merkkijonona
		 */			
		public String getPostitoimipaikka() {
			return m_postitoimipaikka;
		}
		/** Palautetaan postinumero
		 *	@return String opiskelija olion postinumero merkkijonona
		 */			
		public String getPostinro() {
			return m_postinro;
		}
		/** Palautetaan sähköpostiosoite
		 *	@return String opiskelija olion sähköpostiosoite merkkijonona
		 */			
		public String getEmail() {
			return m_email;
		}
		/** Palautetaan puhelinnumero
		 *	@return String opiskelija olion puhelinnumero merkkijonona
		 */			
		public String getPuhelinnro() {
			return m_puhelinnro;
		}
		/** Palautetaan kurssisuoritukset
		 *	@return opiskelija olion kurssisuoritukset suoritus luokasta 
		 */			
		/*public LinkedList<Suoritus>getSuoritukset() {
			return m_suoritukset;
		}*/			
		//Setterit
		/** Asetetaan olion opiskelija_id tieto.
		 *  @param opiskelija_id kokonaislukuna.
		 */		
		public void setOpiskelija_id(int opiskelija_id) {
			this.m_opiskelija_id = opiskelija_id;
		}
		/** Asetetaan olion etunimi tieto.
		 *  @param etunimi m_etunimi merkkijonona.
		 */		
		public void setEtunimi(String etunimi) {
			this.m_etunimi = etunimi;
		}
		/** Asetetaan olion sukunimi tieto.
		 *  @param sukunimi m_sukunimi merkkijonona.
		 */		
		public void setSukunimi(String sukunimi) {
			this.m_sukunimi = sukunimi;
		}
		/** Asetetaan olion lähiosoite tieto.
		 *  @param lahiosoite m_lahiosoite merkkijonona.
		 */		
		public void setLahiosoite(String lahiosoite) {
			this.m_lahiosoite = lahiosoite;
		}
			/** Asetetaan olion postitoimipaikka.
		 *  @param postitoimipaikka m_postitoimipaikka merkkijonona.
		 */		
		public void setPostitoimipaikka(String postitoimipaikka) {
			this.m_postitoimipaikka = postitoimipaikka;
		}	
		/** Asetetaan olion postinumero.
		 *  @param postinro m_postinro merkkijonona.
		 */		
		public void setPostinro(String postinro) {
			this.m_puhelinnro = postinro;
		}
		/** Asetetaan olion sähköpostiosoite.
		 *  @param email m_email merkkijonona.
		 */		
		public void setEmail(String email) {
			this.m_email = email;
		}
		/** Asetetaan olion puhelinnumero.
		 *  @param puhelinnro m_puhelinnro merkkijonona.
		 */		
		public void setPuhelinnro(String puhelinnro) {
			this.m_puhelinnro = puhelinnro;
		}			
	//metodit
    /** Palautetaan Opikelija oliota kuvaava merkkijono
     *  @return Opiskelijan tiedot merkkijonona.
     */	
	@Override
	public String toString() {
		return"ID: " + m_opiskelija_id + " Nimi: " + m_etunimi + " " + m_sukunimi + " ";
	}
	
	/*public static Opiskelija getOpiskelijanSuoritukset(int id) {
		return Kurssi. /**Tämä kesken -	Opiskelija-luokkaan getOpiskelijanSuoritukset, joka palauttaa  //Kesken, en ymmärrä miten kuuluu tehdä??
						kokoelman opiskelijan suorituksista (Suoritus-olioista)
	}*/
}
		
	

