/**
 *
 * @author Joni "JHVeps" Vepsäläinen 14.9.2021
 */
import java.util.ArrayList;

public class TRAI_21_X2_joniveps implements TRAI_21_X2 {

    /**
     * Kasvavien listojen erotus. Palauttaa sellaiset alkiot jotka löytyvät
     * listasta A, mutta eivät listasta B. Jos alkio on listassa A useasti,
     * mutta ei lainkaan listassa B, alkio tulee tuloslistaan yhtä monta kertaa
     * kuin se listassa A on. Jos alkio on edes yhden kerran listassa B, se ei
     * tule tuloslistaan lainkaan. Tuloslista tulee myös kasvavaan
     * järjestykseen.
     *
     * @param A ensimmäinen syötelista, alkiot kasvavassa järjestyksessä
     * @param B toinen syötelista, alkiot kasvavassa järjestyksessä
     * @return erotuslista
     */
 @Override
    public ArrayList<Integer> kasvavienErotus(ArrayList<Integer> A, ArrayList<Integer> B) {
		ArrayList<Integer> tulos = new ArrayList<>();			// O(1)
        boolean prevBSmaller = false;							// O(1)
        for (int i = 0, j = 0; i < A.size();) {					// O(n)
            if (B.isEmpty()) {
                return A;										// O(1) Jos B tyhjä palautetaan A sellaisenaan
            }
			if (A.get(i).equals(B.get(j))) {					
                prevBSmaller = false;							// O(1) Jos A i:ssä ja B j:ssä alkiot samat
                i++;											
            } else if (A.get(i) < B.get(j)) {						
                tulos.add(A.get(i));							// O(1)	Jos A i:ssä alkio pienempi kuin B j:ssä 
                prevBSmaller = false;							
                i++;											
            } else if (prevBSmaller && A.get(i) > B.get(j)) {	
                if (j == B.size() - 1) {						// O(1) Jos prevBSmaller ja A i:ssä on suurempi kuin B j:ssä 
                    tulos.add(A.get(i));						//		Jos J on yhtä suuri kuin B koko -1 
                    i++;											
                }
                if (j < B.size() - 1) {							
                    j++;										// O(1) Jos J on pienempi kuin B koko -1
                }
            } else if (A.get(i) > B.get(j)) {					
                prevBSmaller = true;							// O(1) Jos A i:ssä on suurempi kuin B j:ssä
                if (j < B.size() - 1) {								
                    j++;										// O(1) Jos J on pienempi kuin B koko -1
                }
            }

        }
        return tulos;											// O(1)
    }

}


















