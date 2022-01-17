import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TRAI_21_X6_joniveps implements TRAI_21_X6 {
    /**
     * ITSEARVIOINTI TÄHÄN:
     *  Tehtävä oli minulle haastava, koska en ole aikaisemmin kuvausta käyttänyt.
     *  Tiesin miten ongelman saan ratkaistua, mutta oikean syntakstin löytymiseen meni kolmen päivän opiskeluaika.
     *  Koen suurta onnistumisen tunnetta siitä, että sain tehtyä testit läpäisevän algoritimin.
	 *	Aikavaativuus oli taas kerran hankala määrittää. Kolme kertaa iteroidaan, joten eikös kolme kertaa tule O(n).
     *  En ole varma opinko ongelman ratkaisuprosessin aikana mitään uutta.
     *  Ehkä sietokyky sitä kohtaan, että ei ymmärrä asiaa kasvoi taas hiukan. Ehkä tätä voidaan pitää kehityksenä.
     *  
     *  Algoritmin aikavaativuus: O(|A|+n^3) 
     * (Avaimien haku: O(n), Avaimien(A) lisäys: O(|A|), avaimien iterointi: O(n), joukkojen iterointi O(n), muut operaatiot vakioaikaisia O(1))
     *   
     * 
     */
    /**
     * Alkioiden hakemisto. Palauttaa kuvauksen jossa kutakin syötejoukoissa
     * olevaa alkiota kohti on joukko niitä joukkoja jossa ko. syötealkio
     * esiintyi.
     *
     * @param SS syötejoukkojen joukko
     * @param <E> alkioiden tyyppi
     * @return kuvaus alkioilta syötejoukoille
     */
    @Override
    public <E> Map<E, Set<Set<E>>> hakemisto(Set<Set<E>> SS) {
        Map<E, Set<Set<E>>> tulosHakemisto = new HashMap<>();
        Set<E> avainLista = new HashSet<>(haeAvaimet(SS)); // haetaan SS joukkojen joukossa olevat "uniikit" alkiot

        Iterator<E> avaimet = avainLista.iterator(); //avain alkioiden iteraattorin luonti
        while (avaimet.hasNext()) {   // käydään avainlistaa läpi
            Set<Set<E>> tempJoukot = new HashSet<>();   //tempJoukot mihin tallennetaan oikeat joukot mistä löytyy avain
            E avain = avaimet.next(); // otetaan E avain arvoksi avaimet.next(), tämä on avain
            
            Iterator<Set<E>> joukot = SS.iterator(); //joukkojen joukon iteraattorin luonti
            while (joukot.hasNext()) {   //käydään joukkojen joukkoa läpi
                Set<E> joukko = joukot.next();   //otetaan Set<E>Joukko arvoksi joukot.next()
                if (joukko.contains(avain)) {   //jos joukosta löytyy avain
                    tempJoukot.add(joukko);     //lisätään joukko tempJoukot:iin       

                    tulosHakemisto.put(avain, tempJoukot);//asetetaan alkio avaimeksi ja lisätään alkion sisältävät joukot   

                }

            }
        }

        return tulosHakemisto;
    }

    public static <E> Set<E> haeAvaimet(Set<Set<E>> SS) {
        Set<E> avaimet = new HashSet<>();
        Iterator<Set<E>> i = SS.iterator(); //O(n)
        while (i.hasNext()) {
            Set<E> alkiot = i.next(); // alkiot määritellään joukoksi mikä on seuraava alkio
            avaimet.addAll(alkiot);     // jokainen alkiot joukon alkio mikä löytyy joukkojen joukosta lisätään vain kerran    
        }//O(|alkiot|)

        return avaimet;
    }

}