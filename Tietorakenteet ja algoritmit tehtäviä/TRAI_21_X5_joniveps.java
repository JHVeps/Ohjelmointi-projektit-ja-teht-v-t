import java.util.HashSet;
import java.util.Set;
	/**
	 * X5. Kirjoita algoritmi joka hakee joukkojen ”kaksi kolmesta” leikkauksen. 
	 * Algoritmi saa siis parametrinaan kolme joukkoa (java.util.Set) ja muodostaa uuden joukon niistä alkioista jotka
	 * kuuluvat täsmälleen kahteen syötejoukoista.
	 * Mukana ei siis ole niitä alkioita jotka kuuluvat vain yhteen syötejoukoista, eikä niitä alkioita jotka kuuluvat kaikkiin syötejoukkoihin.
	 * Älä muuta syötejoukkoja äläkä käytä apuna kuvausta (Map) tai tietorakennekirjastomme joukkoa (TraSet).
	 * Vihjeitä: voit ottaa joukoista kopioita, käytä joukko-operaatioita, ei kannata lähteä iteroimaan joukkoja alkioittain.
	 * Mikä on algoritmisi aikavaativuus jos syötejoukotovat HashSet-tyyppiä? Aikavaativuus vaikuttaa arvosteluun.
	 */
public class TRAI_21_X5_joniveps implements TRAI_21_X5 {
    /**
     * ITSEARVIOINTI TÄHÄN:
     *	Algoritmin aikavaativuus: O(|A| x 9) missä A on operoitava HashSet syötejoukko.
     * 	Huom! RetainAll operaatio on JAVA:ssa optimoitu käyttämään pienempää joukkoa askelten lukumääränä.	 
     *	
	 *	Käytin tehtävänannon mukaisesti joukko operaatioita, sekä vihjeen mukaisesti otin kopioita joukoista,
	 *	että parametrina saadut joukot pysyvät muuttumattomina.  
	 *	Mielestäni algoritmi on helppolukuinen ja ns. "kaunista koodia" omaan silmään.
	 *	Aikavaativuus oli aavistuksen hankala minulle kirjoittaa auki.
	 *	Kommentoin koodiin vaiheita auki selvyydeksi.  
     */

    /**
     * Joukkojen kaksi kolmesta -leikkaus. Luo uuden joukon johon algoritmi laittaa ne syötejoukkojen alkiot jotka
     * kuuluvat tasan kahteen kolmesta syötejoukosta. Jos jokin alkio kuuluu vain yhteen tai kaikkiin kolmeen
     * syötejoukkoon, alkiota ei laiteta tulosjoukkoon. Ei muuta syötejoukkojensa sisältää (vaan luo uuden tulosjoukon)
     * Jos mikään alkio ei täytä ehtoa, palautetaan tyhjä joukko.
     *
     * @param S1 syötejoukko
     * @param S2 syötejoukko
     * @param S3 syötejoukko
     * @return tulosjoukko
     */
        @Override
    public <E> Set<E> kaksiKolmesta(Set<E> S1, Set<E> S2, Set<E> S3) {
        Set<E> tulos = new HashSet<>(); //O(1)
        //S1 joukon kopiot
        Set<E> copyOfS1a = new HashSet<>(S1); //O(1)
        Set<E> copyOfS1b = new HashSet<>(S1); //O(1)
        //S2 joukon kopiot 
        Set<E> copyOfS2 = new HashSet<>(S2);  //O(1)

        copyOfS1a.retainAll(S2); //Joukkojen S1 ja S2 leikkaus, O(|pienempi joukko|)  1
        copyOfS1b.retainAll(S3); //Joukkojen S1 ja S3 leikkaus, O(|pienempi joukko|)  2
        copyOfS2.retainAll(S3); //Joukkojen S2 ja S3 leikkaus, O(|pienempi joukko|)   3

        copyOfS1a.removeAll(S3); //Joukkojen S1 ja S2 leikkaus mistä poistetaan joukon S3 alkiot, O(|copyOfS1a|)    4
        copyOfS1b.removeAll(S2); //Joukkojen S1 ja S3 leikkaus mistä poistetaan joukon S2 alkiot, O(|copyOfS1b|)    5
        copyOfS2.removeAll(S1); //Joukkojen S2 ja S3 leikkaus mistä poistetaan joukon S1 alkiot, O(|copyOfS2|)      6

        //Lisätään joukkojen leikkausten alkiot tulosjoukkoon
        tulos.addAll(copyOfS1a); //O(|copyOfS1a|)   7
        tulos.addAll(copyOfS1b); //O(|copyOfS1b|)   8
        tulos.addAll(copyOfS2); //O(|copyOfS2|)     9

        return tulos;   //O(1)
    }

}