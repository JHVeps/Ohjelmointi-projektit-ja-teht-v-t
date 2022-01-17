import java.util.Arrays;

public class TRAI_21_X1_joniveps implements TRAI_21_X1 {
             //         ^^^^^
          // oma tunnus tähän

    /**
     * Suurimman ja pienimmän summa.
     * Palauttaa taulukon suurimman ja pienimmän luvun summan tai null jos
     * taulukko on tyhjä.
     *
     * @param A Syötetaulukko.
     * @return suurimman ja pienimmän summan tai null jos taulukko on tyhjä.
     */
    @Override
    public Integer suurinJaPieninSumma(Integer[] A) {

        Integer taulukko[] = new Integer[A.length]; //Varataan uusi taulukko mikä on kooltaan sama kuin parametrina saadut taulukot.  O(1)
        
		System.arraycopy(A, 0, taulukko, 0, A.length); //Kopioidaan A taulukko luotuun taulukkoon. O(n)

        Arrays.sort(taulukko);  //Järjestetään luotu taulukko pienimmmästä suurimpaan, taulukko[0] - taulukko[N]  (Pahin tapaus = O(n))

        if (A.length < 1) {		//O(1)
            return null;
        } else {

        }

        return taulukko[0] + taulukko[taulukko.length - 1]; // Palautetaan järjestetyn taulukon ensimmäisen ja viimeisen alkion summa. O(1)
    }
}
/**
*Algoritmin aikavaativuus
*Taulukon varaus O(1), Taulukon kopionti O(n), Taulukon järjestäminen O(n), Ehtorakenne O(1), Palautus O(1)  
*1+1+1 = O(1)
*n+n = n2
*O(1) X n2 = O(n2)
* 
*
*/