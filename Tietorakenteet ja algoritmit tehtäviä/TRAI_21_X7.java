/**
 * Jonon toteutus. Vanhin jonoon laitettu alkio otetaan ensimmäisenä pois (FIFO).
 * @param <E> Alkiotyyppi.
 */

// ota kommentit pois extends Iterable:n kohdalta kun teet tehtävää 29.
public interface TRAI_21_X7<E> /* extends Iterable<E> */ {

    /**
     * Lisää jonoon yhden alkion.
     * @param x lisättävä alkio.
     */
    public void lisaa(E x);

    /**
     * Poistaa ja palauttaa jonosta siellä pisimpään olleen alkion.
     * @return poistettu jonossa pisimpään ollut alkio.
     * @throws java.util.NoSuchElementException jollei jonossa ole yhtään alkiota.
     */
    public E poista();

    /**
     * Onko jono tyhjä vai ei?
     * @return true jos jonossa ei ole yhtään alkiota, muuten false
     */
    public boolean onkoTyhja();

    /**
     * Tyhjentää jonon.
     * Tämän voi tehdä tehokkaammin toteuttavassa luokassa kun
     * toteutusrakenne on selvillä.
     */
    public default void poistaKaikki() {
        while (! onkoTyhja())
            poista();
    }
}