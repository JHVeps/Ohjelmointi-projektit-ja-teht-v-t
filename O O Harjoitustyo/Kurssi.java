/**
 * @version 1.0
 * @author Joni Vepsäläinen 
 */
package opiskelija.kurssitiedot;
import java.util.*;
//Luokka ja atribuutit
/**Luokka Kurssi sisältää Opiskelija luokan olion kurssin tiedot ja metodit kurssin suorituksien sekä opiskelijatietojen palauttamiseeen
*/
public class Kurssi {
	/**Kurssin id numero*/
	protected int m_kurssi_id;
	/**Kurssin nimi*/
	protected String m_nimi;
	/**Kurssin opintopisteet*/
	protected int m_opintopisteet;	
	/**Kurssin kuvaus*/
	protected String m_kuvaus;
	//konstruktorit
	/** Olion tyhjä oletus konstruktori   
	 */	
	public Kurssi() {
	}
	/** Olion konstruktori tallentaa kurssin id:n, nimen, opintopisteet ja kuvauksen.
	 *	@param kurssi_id kurssin id numero kokonaislukuna
	 *	@param nimi kurssin nimi merkkijonona
	 *	@param opintopisteet kurssin opintopisteet kokonaislukuna
	 *	@param kuvaus kurssin kuvaus merkkijonona
	 */
    public Kurssi(int kurssi_id, String nimi, int opintopisteet, String kuvaus) {
		this.m_kurssi_id = kurssi_id;
		this.m_nimi = nimi;
		this.m_opintopisteet = opintopisteet;	
		this.m_kuvaus = kuvaus;
	}
			//Getterit
			/** Palautetaan kurssin id numero
			 *	@return int kurssin id numero kokonaislukuna
			 */		
			public int getKurssi_id() {
				return m_kurssi_id;
			}
			/** Palautetaan kurssin nimi
			 *	@return String kurssin nimi merkkijonona
			 */			
			public String getNimi() {
				return m_nimi;
			}
			/** Palautetaan opintopisteet
			 *	@return int kurssin opintopisteet kokonaislukuna
			 */		
			public int getOpintopisteet() {
				return m_opintopisteet;
			}
			/** Palautetaan kurssin kuvaus
			 *	@return String kurssin kuvaus merkkijonona
			 */			
			public String getKuvaus() {
				return m_kuvaus;
			}
			//Setterit
			/** Asetetaan kurssin id numero tieto.
			 *  @param kurssi_id m_kurssi_id kokonaislukuna.
			 */		
			public void setKurssi_id(int kurssi_id) {
				this.m_kurssi_id = kurssi_id;
			}
			/** Asetetaan kurssin nimi tieto.
			 *  @param nimi m_nimi  merkkijonona.
			 */				
			public void setNimi(String nimi) {
				this.m_nimi = nimi;
			}
			/** Asetetaan kurssin opintopisteet tieto.
			 *  @param opintopisteet m_opintopisteet kokonaislukuna.
			 */		
			public void setOpintopisteet(int opintopisteet) {
				this.m_opintopisteet = opintopisteet;
			}
			/** Asetetaan kurssin kuvaus tieto.
			 *  @param kuvaus m_kuvaus merkkijonona.
			 */			
			public void setKuvaus(String kuvaus) {
				this.m_kuvaus = kuvaus;
			}	
	//Metodit
    /** Palautetaan Kurssi oliota kuvaava merkkijono
     *  @return Kurssin tiedot merkkijonona.
     */		
	@Override
	public String toString() {
		return "Kurssin ID:" + m_kurssi_id + " " + m_nimi + " " + " laajuus " + m_opintopisteet + " opintopistetta. " + m_kuvaus;
	}			
	/*public String getKurssinSuoritukset() {
			/**-Kurssi-luokkaan getKurssinSuoritukset, joka palauttaa
						kokoelman kurssin suorituksista (Suoritus-olioista)  //kesken, en ymmärrä miten kuuluu tehdä??
	}*/
	/*public static Opiskelija getKurssinOpiskelijat() {
			/**-Kurssi-luokkaan getKurssinOpiskelijat, joka palauttaa 
						kokoelman (Opiskelija-olioita) kurssilla olevista opiskelijoista   //kesken, en ymmärrä miten kuuluu tehdä??
	}*/
}