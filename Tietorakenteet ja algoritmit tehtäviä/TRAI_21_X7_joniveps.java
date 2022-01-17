import java.util.NoSuchElementException;

/**
 * X7. Toteuta abstrakti tietotyyppi jono
 *
 * @author Joni Vepsäläinen
 * @param <E> Alkiotyyppi
 */
public class TRAI_21_X7_joniveps<E> implements TRAI_21_X7<E> {

    private static final int MIN_KOKO = 2;  // Vakio, minimi koko taulukolle. (mikälie olisi paras arvo) kokonaisluku
    private E[] data;                       // Taulukkorakenne missä käsitellään alkioita 
    private int alkioLkm;                   // Alkioiden lukumäärän muuttuja. Kokonaisluku
    private int ensimmainenIndeksi;         // Ensimmäisen alkion sijainti indeksi. Kokonaisluku
    private int viimeinenIndeksi;           // Viimeisen alkion sijainti indeksi. Kokonaisluku

    /**
     * ITSEARVIOINTI TÄHÄN:
     *
     * Oli taas kerran tuskallisen vaikea lähteä tekemään. Paperille en osaa
     * edelleenkään mitään suunnitella ennenkuin olen jotain saanut ohjelmoitua
     * ja on edes joku käsitys mitä pitää / voi tehdä. Ajattelin tehdessäni
     * ensimmäistä ja viimeistä alkiota ja sitä miten niitä jonon tapauksessa liikutellaan.
     * Vaikeaa oli taas kaikki ja punainen lanka katosi parikin kertaa tekemisen
     * yhteydessä. Vaikeinta oli koon muuttamisen toteteuttaminen. Aikavaativuus
     * on vakioaikaista O(1) vaadituille operaatioille lisää, poista, onkoTyhja.
     * Mikäli tilaa joudutaan kasvattamaan (maaritaKoko) kopioimalla taulukon sisältö
     * uuteen taulukkoon on sen aika O(n).
     *
     */
    /**
     * default konstruktori
     */
    @SuppressWarnings("unchecked")
    public TRAI_21_X7_joniveps() {
        alkioLkm = 0;
        data = (E[]) new Object[MIN_KOKO];
        ensimmainenIndeksi = 0;
        viimeinenIndeksi = 1;
    }

    /**
     *
     * @return jonon alkioiden lukumäärä
     */
    private int koko() {    // voisi olla myös public
        return alkioLkm;
    }

    /**
     * Lisää jonoon yhden alkion.
     *
     * @param x lisättävä alkio.
     */
    @Override
    public void lisaa(E x) {
        if (x == null) {
            throw new NullPointerException("Ei voi lisätä Null!");
        }
        if (ensimmainenIndeksi < 0) {
            maaritaKoko(data.length + alkioLkm);
        }
        data[ensimmainenIndeksi--] = x;
        alkioLkm++;
    }

    /**
     * Poistaa ja palauttaa jonosta siellä pisimpään olleen alkion.
     *
     * @return alkio = poistettu jonossa pisimpään ollut alkio.
     * @throws NoSuchElementException jollei jonossa ole yhtään alkiota.
     */
    @Override
    public E poista() {
        if (onkoTyhja()) {
            throw new NoSuchElementException("jono on tyhjä");
        }
        E alkio = data[--viimeinenIndeksi];
        data[viimeinenIndeksi] = null;
        alkioLkm--;

        return alkio;
    }

    /**
     * Onko jono tyhjä vai ei?
     *
     * @return true jos jonossa ei ole yhtään alkiota, muuten false
     */
    @Override
    public boolean onkoTyhja() {
        return koko() == 0;
    }

    /**
     * Taulukon koon muuttaminen tarvittaessa
     *
     * @param uusiKoko
     */
    @SuppressWarnings("unchecked")
    private void maaritaKoko(int uusiKoko) {
        E[] temp = (E[]) new Object[uusiKoko];
        int sijoitus = 0;
        int loppu = viimeinenIndeksi;
        int alku = 0;

        if (uusiKoko < data.length) {
            alku = ensimmainenIndeksi;
            int erotus = data.length - uusiKoko;
            ensimmainenIndeksi -= erotus;
            viimeinenIndeksi -= erotus;
            sijoitus = ensimmainenIndeksi;
            loppu = alkioLkm + 1;
        } else {
            ensimmainenIndeksi = alkioLkm - 1;
            viimeinenIndeksi += alkioLkm;
            sijoitus = loppu = alkioLkm;
        }
        System.arraycopy(data, alku, temp, sijoitus, loppu);    // O(n)
        data = temp;
    }

}
