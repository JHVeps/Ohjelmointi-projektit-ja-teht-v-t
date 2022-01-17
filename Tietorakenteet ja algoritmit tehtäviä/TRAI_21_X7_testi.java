import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;

public class TRAI_21_X7_testi {

    public static void main(String[] args) {

        boolean ok = true;

        System.out.println("Luodaan kolme jonoa");

        TRAI_21_X7<Integer> jono1 = new TRAI_21_X7_joniveps<>();
        TRAI_21_X7<Integer> jono2 = new TRAI_21_X7_joniveps<>();
        TRAI_21_X7<String> jono3 = new TRAI_21_X7_joniveps<>();

        // luodaan myös kolme verrokkia
        Queue<Integer> vrt1 = new ArrayDeque<>();
        Queue<Integer> vrt2 = new ArrayDeque<>();
        Queue<String> vrt3 = new ArrayDeque<>();

        // alussa pitäisi olla tyhjiä
        ok &= testaaTyhja(jono1, vrt1);

        System.out.println("Lisätään jono1:een 5 alkiota");
        ok &= lisaa(jono1, vrt1, 5);

        System.out.println("Tulostetaan jonot (järkevää kun olet toteuttanut toString:n).");
        System.out.println("Jono1: " + jono1);
        System.out.println("Vrt1 : " + vrt1);

        ok &= testaaTyhja(jono1, vrt1);

        System.out.println("Poistetaan jono1:stä 4 alkiota");
        ok &= poista(jono1, vrt1, 4);

        ok &= testaaTyhja(jono1, vrt1);

        System.out.println("Lisätään jono1:een 20 alkiota");
        ok &= lisaa(jono1, vrt1, 20);

        System.out.println("Poistetaan jono1:stä 21 alkiota");
        ok &= poista(jono1, vrt1, 21);
        ok &= testaaTyhja(jono1, vrt1);

        System.out.println("Testataan tyhjästä poisto");
        if (jono1.onkoTyhja()) {
            try {
                jono1.poista();
                System.out.println("Ei pitänyt poisto onnistua vaan piti tulla poikkeus.");
                ok = false;
            } catch (NoSuchElementException e) {
                System.out.println("Tuli poikkeus oikein");
            } catch (Exception e) {
                System.out.println("Tuli väärä poikkeus");
                ok = false;
            }
        } else {
            System.out.println("Jonon piti olla tyhjä");
            ok = false;
        }

        System.out.println("Lisätään jono1:een 5 alkiota, siirretään ne sitten jonoon 2 ja poistetaan sieltä");
        ok &= lisaa(jono1, vrt1, 5);
        ok &= siirra(jono1, jono2, vrt1, vrt2, 5);
        ok &= poista(jono2, vrt2, 5);

        System.out.println("Lisätään ja poistetaan jono3:een 25 merkkijonoa");
        ok &= lisaaS(jono3, vrt3, 25);
        ok &= poista(jono3, vrt3, 25);
        ok &= testaaTyhja(jono3, vrt3);

        System.out.println("Lisätään jono2:een 5 alkiota");
        ok &= lisaa(jono2, vrt2, 5);
        System.out.println("Kierrätetään jono2:n alkioita 87 kertaa");
        ok &= kierrata(jono2, vrt2, 87);
        System.out.println("Tulostetaan jonot (järkevää kun olet toteuttanut toString:n).");
        System.out.println("Jono2: " + jono2);
        System.out.println("Vrt2 : " + vrt2);

        System.out.println("Poistetaan jono2:stä 5 alkiota");
        ok &= poista(jono2, vrt2, 5);
        ok &= testaaTyhja(jono2, vrt2);


        // kun olet toteuttanut iterablen, niin testataan sitäkin

        /*
        if (jono2 instanceof Iterable) {

            System.out.println("\nTestataan läpikäyntiä:");
            // tätä ei kannata kutsua ennenkuin se toimii
            jono2.poistaKaikki();
            System.out.println("Lisätään jono2:een 25 alkiota");
            ok &= lisaa(jono2, vrt2, 25);
            System.out.println("Kierrätetään jono2:n alkioita 87 kertaa");
            ok &= kierrata(jono2, vrt2, 87);

            System.out.println("Tulostetaan läpikäynnillä:");
            System.out.print("jono2: < ");
            for (Integer x : jono2)
                System.out.print(x + " ");
            System.out.println("< ");
            System.out.println("vrt2 : " + vrt2);
        }
        */


        System.out.println("Testien tulos: " + ok);

    }

    static boolean lisaa(TRAI_21_X7<Integer> jono, Queue<Integer> vrt, int n) {
        for (int i = 0; i < n; i++) {
            jono.lisaa(i);
            vrt.offer(i);
        }
        return true;
    }

    static boolean lisaaS(TRAI_21_X7<String> jono, Queue<String> vrt, int n) {
        for (int i = 0; i < n; i++) {
            jono.lisaa("s" + i);
            vrt.offer("s" + i);
        }
        return true;
    }




    static <E> boolean poista(TRAI_21_X7<E> jono, Queue<E> vrt, int n) {
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            E x = jono.poista();
            E v = vrt.poll();
            if (! Objects.equals(x, v)) {
               System.out.println("poista: vääräalkio:" + x + " piti olla:" +v);
               ok = false;
            }

        }
        return ok;
    }

    static boolean testaaTyhja(TRAI_21_X7 jono, Queue vrt) {
        if (jono.onkoTyhja() == vrt.isEmpty())
            return true;
        else {
            System.out.println("onkoTyhja virhe: onkoTyhja:" + jono.onkoTyhja() + " odotettu:" + vrt.isEmpty());
            return false;
        }
    }

    static <E> boolean siirra(TRAI_21_X7<E> jono1, TRAI_21_X7<E> jono2,
                              Queue<Integer> vrt1, Queue<Integer> vrt2 , int n) {
        for (int i = 0; i < n; i++) {
            jono2.lisaa(jono1.poista());
            vrt2.offer(vrt1.poll());
        }
        return true;
    }

    static <E> boolean kierrata(TRAI_21_X7<E> jono, Queue<E> vrt, int n) {
        boolean ok = true;
        for (int i = 0; i < n; i++) {
            E x = jono.poista();
            E v = vrt.poll();
            if (! Objects.equals(x, v)) {
               System.out.println("poista: väärä alkio:" + x + " piti olla:" +v);
               ok = false;
            }
            jono.lisaa(x);
            vrt.offer(v);
        }
        return ok;
    }





}