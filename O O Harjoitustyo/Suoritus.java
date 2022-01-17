/**
 * @version 1.0 
 * @author joni Vepsäläinen
 */
package opiskelija.suoritukset;
import java.util.*;
//Luokka ja atribuutit
/**Luokka Suoritus sisältää opiskelijan kurssin suoritustiedot.   
*/
public class Suoritus {
	/**Opiskelijan id numero*/	
	protected int m_opiskelija_id;
	/**Kurssin id numero*/	
	protected int m_kurssi_id;
	/**Suorituksen arvosana*/	
	protected int m_arvosana;	
	/**Suoritus päivämäärä (vvvvkkpp)*/	
	protected String m_suoritus_pvm;
	
	//konstruktorit
	/**Suoritus olion tyhjä oletus konstruktori
	*/
	public Suoritus() {
	}
	/** Olion konstruktori tallentaa suorituksen opiskelijan id:n, kurssin id:n, arvosanan ja suorituspäivämäärän.
	 *	@param opiskelija_id olion id numero kokonaislukuna
	 *	@param kurssi_id kurssin id numero kokonaislukuna
	 *	@param arvosana suorituksen arvosana kokonaislukuna
	 *	@param suoritus_pvm suorituksen suorituspäivämäärä merkkijonona
	 */	
    public Suoritus(int opiskelija_id, int kurssi_id, int arvosana, String suoritus_pvm) {
		this.m_opiskelija_id = opiskelija_id;		
		this.m_kurssi_id = kurssi_id;
		this.m_arvosana = arvosana;	
		this.m_suoritus_pvm = suoritus_pvm;//(vvvvkkpp)
	}
		//Getterit
		/** Palautetaan id numero
		 *	@return int olion id numero kokonaislukuna
		 */		
		public int getOpiskelija_id() {
			return m_opiskelija_id;
		}		
		/** Palautetaan kurssin id numero
		 *	@return int kurssin id numero kokonaislukuna
		 */			
		public int getKurssi_id() {
			return m_kurssi_id;
		}	
		/** Palautetaan suorituksen arvosana
		 *	@return int suorituksen arvosana kokonaislukuna
		 */			
		public int getArvosana() {
			return m_arvosana;
		}
		/** Palautetaan suorituspäivämäärä
		 *	@return String suorituspäivämäärä merkkijonona
		 */				
		public String getSuoritusPVM() {
			return m_suoritus_pvm;
		}
		//Setterit
		/** Asetetaan olion opiskelija_id.
		 *  @param opiskelija_id kokonaislukuna.
		 */		
		public void setOpiskelija_id(int opiskelija_id) {
			this.m_opiskelija_id = opiskelija_id;
		}
		/** Asetetaan kurssin id numero.
		 *  @param kurssi_id m_kurssi_id kokonaislukuna.
		 */			
		public void setKurssi_id(int kurssi_id) {
			this.m_kurssi_id = kurssi_id;
		}
		/** Asetetaan suorituksen arvosana.
		 *  @param arvosana m_arvosana kokonaislukuna.
		 */		
		public void setArvosana(int arvosana) {
			this.m_arvosana = arvosana;
		}
		/** Asetetaan suorituksen suorituspäivämäärä.
		 *  @param suoritus_pvm m_suoritus_pvm merkkijonona.
		 */			
		public void setSuoritus_pvm(String suoritus_pvm) {
			this.m_suoritus_pvm = suoritus_pvm;
		}	
	@Override
	//Metodit
    /** Palautetaan Suoritus oliota kuvaava merkkijono
     *  @return suorituksen tiedot merkkijonona.
     */		
	public String toString() {
		return m_opiskelija_id + " " + m_kurssi_id + " Arvosana: " + m_arvosana + " Suorituspaiva: " + m_suoritus_pvm;
	}			
	
	/*public void getKurssinSuoritukset() { 
	}*/												//Kesken, en ymmärä miten kuuluu tehdä??
}	