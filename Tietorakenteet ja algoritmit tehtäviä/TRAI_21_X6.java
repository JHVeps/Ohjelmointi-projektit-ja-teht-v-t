import java.util.Map;
import java.util.Set;

public interface TRAI_21_X6 {

    /**
     * Alkioiden hakemisto. Palauttaa kuvauksen jossa kutakin syötejoukoissa
     * olevaa alkiota kohti on joukko niitl joukkoja jossa ko. syötealkio
     * esiintyi.
     *
     * @param SS syötejoukkojen joukko
     * @param <E> alkioiden tyyppi
     * @return kuvaus alkioilta syötejoukoille
     */
    <E> Map<E, Set<Set<E>>> hakemisto(Set<Set<E>> SS);
}
