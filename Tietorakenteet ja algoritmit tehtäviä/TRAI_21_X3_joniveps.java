/**
 *
 * @author JHVeps
 */
import java.util.LinkedList;
import java.util.ListIterator;

public class TRAI_21_X3_joniveps implements TRAI_21_X3 {


    /**
     * Poistaa listasta A saman alkion peräkkäisistä esiintymistä muut paitsi
     * ensimmäisen. Listasta (4 3 3 2 2 2 2 1 2 2 3 3) tulee lista (4 3 2 1 2
     * 3). Lista voi sisältää myös null:eja jotka käsitellään samoin kuin muut
     * alkiot.
     *
     * @param A syötelista
     * @return poistettujen määrä
     */
    @Override
    public <E> int poistaPerakkaisetDuplikaatit(LinkedList<E> A) {
        int poistettu = 0;

        ListIterator<E> i = A.listIterator();

        if (A.isEmpty()) {
            return 0;
        }
        Object prev = i.next();
        while (i.hasNext()) {
            Object next = i.next();

            if ((prev == null && next == null) || (prev != null && prev.equals(next))) {
                i.remove();

                poistettu++;

            }
            prev = next;

        }

        return poistettu;
    }
}
