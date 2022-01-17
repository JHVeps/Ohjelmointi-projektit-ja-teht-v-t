package opiskelija.testi;
/** 
 * @version 1.0
 * @author Joni Vepsäläinen
 */
import opiskelija.ominaisuudet.Opiskelija;
import opiskelija.kurssitiedot.Kurssi;
import opiskelija.suoritukset.Suoritus;
import java.util.*;
/** Main ohjelma missä testataan Opiskelija-, Kurssi- ja Suoritus -luokkien olioiden toimintaa.
 */
public class OpiskelijatMain {
	/** Opiskelija - olioperhettä testaava ohjelma
	 * @param args Käynnistyksen yhteydessä annettavat parametrit merkkijonona
	 * - näitä ei käsitellä mitenkään tässä ohjelmassa 
	 */
	public static void main ( String [] args ) {
		/** Opiskelija olioiden atribuuttien määrittely.
		 *	@param Opiskelija (int, String, String, String, String, String, String, String)
		 */
		Opiskelija oOlio1 = new Opiskelija(226234, "Joni", "Vepsalainen", "Laavukatu 6", "Joensuu", "80140", "joni.vepsalainen@edu.karelia.fi", "+358503633659");
		Opiskelija oOlio2 = new Opiskelija(226235, "Esko", "Pekanpoika", "Jokukatu 7", "JokuCity", "00001", "esko.pekanpoika@mail.fi", "5552100100");
		Opiskelija oOlio3 = new Opiskelija(226236, "Pekka", "Eskonpoika", "Jokukatu 8", "JokuCity", "00002", "pekka.eskonpoika@mail.fi", "555200200");
		/** Kurssi olioiden atribuuttien määrittely.
		 *	@param Kurssi (int, String, int, String)
		 */
		Kurssi kOlio1 = new Kurssi(111111, "Olio-ohjelmointi", 5, "Opintojaksolla opetellaan olio-ohjelmoinnin perusteet. Kaytamme opintojakson aikana java-ohjelmointikieltä, jonka voi ladata ja asentaa henkilokohtaiseen tietokoneeseen Oracle-nimisen yrityksen sivuilta");
		Kurssi kOlio2 = new Kurssi(222222, "Kehitysymparistot", 3, "Talla opintojaksolla perehdyt hajautettuun ohjelmistokehitykseen ja siina hyodynnettaviin valineisiin ja ymparistoihin");
		Kurssi kOlio3 = new Kurssi(333333, "Raportointi ja kirjoitusviestinta", 2, "Viestintataidot ovat keskeisia opiskelutaitoja, mutta ne ovat myos osa ammatti-identiteettia ja ammatillista osaamista.");		
		/** Suoritus olioiden atribuuttien määrittely.
		 *	@param Suoritus (int Opiskelija -olion id, int Kurssi -olion id, int, String)	
		 */
		Suoritus sOlio1 = new Suoritus(oOlio1.getOpiskelija_id(), kOlio1.getKurssi_id(), 4, "20202712");
		Suoritus sOlio2 = new Suoritus(oOlio2.getOpiskelija_id(), kOlio2.getKurssi_id(), 5, "20203110");
		Suoritus sOlio3 = new Suoritus(oOlio3.getOpiskelija_id(), kOlio3.getKurssi_id(), 3, "20203011");		
		/** Määritellään kokoelma opiskelijat sisältämään Opiskelija luokan -olioita.
		 *	@param ArrayList<Opiskelija> opiskelijat<Opiskelija>
		 */
		ArrayList<Opiskelija> opiskelijat = new ArrayList<Opiskelija>(); 
		/** Opiskelija -olioiden sijoittaminen kokoelmaan opiskelijat.
		 *	@param ArrayList.add(Object)   
		 */
		opiskelijat.add(oOlio1);
		opiskelijat.add(oOlio2);
		opiskelijat.add(oOlio3);
		/** Määritellään kokoelma kurssitiedot sisältämään Kurssi luokan -olioita.
		 *	@param LinkedList<Kurssi> kurssitiedot<Kurssi>
		 */		 
		LinkedList<Kurssi> kurssitiedot = new LinkedList<Kurssi>(); 		
		/** Kurssi -olioiden sijoittaminen kokoelmaan kurssitiedot.
		 *	@param LinkedList.add(Object)
		 */
		kurssitiedot.add(kOlio1);
		kurssitiedot.add(kOlio2);
		kurssitiedot.add(kOlio3);
		/** Määritellään kokoelma suoritustiedot sisältämään Suoritus luokan -olioita.
		 *	@param LinkedList<Suoritus> suoritustiedot<Suoritus>
		 */		 
		LinkedList<Suoritus> suoritustiedot = new LinkedList<Suoritus>(); 		
		/** Suoritus -olioiden sijoittaminen kokoelmaan suoritustiedot.
		 *	@param LinkedList.add(Object)
		 */
		suoritustiedot.add(sOlio1);
		suoritustiedot.add(sOlio2);
		suoritustiedot.add(sOlio3);		
		/** Tulostetaan olioiden tiedot näkyville for loopissa.
		 */
		for (int i = 0; i < 3; i++) {
			System.out.println("\n" + opiskelijat.get(i).toString() + "\n"  + kurssitiedot.get(i).toString() + "\nArvosana: " + suoritustiedot.get(i).getArvosana() + " Suorituspaivamaara: " + suoritustiedot.get(i).getSuoritusPVM() + "\n");
		}
	}
}