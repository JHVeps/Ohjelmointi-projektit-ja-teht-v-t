import java.util.Set;

public interface TRAI_21_X5 {

    /**
     * Joukkojen kaksi kolmesta -leikkaus.
     * Luo uuden joukon johon algoritmi laittaa ne syötejoukkojen alkiot jotka
     * kuuluvat tasan kahteen kolmesta syötejoukosta.
     * Jos jokin alkio kuuluu vain yhteen tai kaikkiin kolmeen syötejoukkoon,
     * alkiota ei laiteta tulosjoukkoon.
     * Ei muuta syötejoukkojensa sisältää (vaan luo uuden tulosjoukon)
     * Jos mikään alkio ei täytä ehtoa, palautetaan tyhjä joukko.
     * @param S1    syötejoukko
     * @param S2    syötejoukko
     * @param S3    syötejoukko
     * @param <E>   alkioiden tyyppi
     * @return  tulosjoukko
     */
    public <E> Set<E> kaksiKolmesta(Set<E> S1, Set<E> S2, Set<E> S3);

}