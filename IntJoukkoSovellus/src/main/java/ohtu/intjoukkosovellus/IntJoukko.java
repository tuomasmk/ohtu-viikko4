
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            kapasiteetti = KAPASITEETTI;
        }
        if (kasvatuskoko < 0) {
            kasvatuskoko = OLETUSKASVATUS;
        }
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko() {
        this(KAPASITEETTI);
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        /*ljono[alkioidenLkm++] = luku;
        Arrays.sort(ljono, 0, alkioidenLkm);
        if (alkioidenLkm == ljono.length) {
            ljono = Arrays.copyOf(ljono, ljono.length + kasvatuskoko);
        }
        Jos alkiot järjestää, niin testit hajoavat...
        */
        ljono[alkioidenLkm++] = luku;
        if (alkioidenLkm == ljono.length) {
            ljono = Arrays.copyOf(ljono, alkioidenLkm + kasvatuskoko);
        }
        return true;
    }

    public boolean kuuluu(int luku) {
        return indexOf(luku) > -1;
    }
    
    public int indexOf(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int indeksi = indexOf(luku);
        if (indeksi == -1) {
            return false;
        }
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            ljono[j] = ljono[j + 1];
        }
        alkioidenLkm--;
        return true;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) return "{}";
        else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        return Arrays.copyOfRange(ljono, 0, alkioidenLkm);
    }
    
    private static void lisaaKaikkiJoukosta(IntJoukko mista, IntJoukko mihin) {
        int[] alkiot = mista.toIntArray();
        for (int i = 0; i < alkiot.length; i++) {
            mihin.lisaa(alkiot[i]);
        }
    }
    
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        lisaaKaikkiJoukosta(a, yhdisteJoukko);
        lisaaKaikkiJoukosta(b, yhdisteJoukko);
        return yhdisteJoukko;
    }
    
    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                leikkausJoukko.lisaa(aTaulu[i]);
            }
        }
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (!b.kuuluu(aTaulu[i])) {
                erotusJoukko.lisaa(aTaulu[i]);
            }
        }
        return erotusJoukko;
    }
        
}