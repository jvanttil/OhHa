package md.simulaatio;

import java.util.Random;
import md.aineistokasittely.aineistokasittelija;

/**
 * @author jvanttil
 */
public class laatikko {
    
    private double koko;
    private int molekyylilkm;
    private int atomilkm;
    private int ruudukonkoko;
    private molekyyli[] molekyylilista;
    private ulkoisetvoimat voimarekisteri;
    private Random rng;
    
    public laatikko() {
        atomilkm = 0;
        rng = new Random();
    }
    
    /**
     * laskee mahtuuko n molekyyliä k kokoiseen laatikkoon
     * 
     * toimii toistaiseksi hyvin suurpiirteisesti
     * 
     * @param koekoko ehdotettu laatikon koko
     * @param koemolekyylilkm ehdotettu molekyylien lukumäärä
     * @return true jos sopii false jos ei
     */
    public boolean mahtuuko(double koekoko, int koemolekyylilkm) {
        int koeruudukonkoko = 1;
        while( Math.pow(koeruudukonkoko,3) <= koemolekyylilkm ) {
            koeruudukonkoko += 1;
        }
        if( koekoko/koeruudukonkoko < 3 ) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * asettaa laatikon ominaisuudet
     * 
     * toimii generointimetodin sisältä
     * 
     * @param skoko laatikon koko
     * @param smolekyylilkm molekyylien määrä laatikossa
     */
    private void asetaparametrit(double skoko, int smolekyylilkm) {
        koko = skoko;
        molekyylilkm = smolekyylilkm;
        molekyylilista = new molekyyli[molekyylilkm];
        voimarekisteri = new ulkoisetvoimat(molekyylilkm*2);
        ruudukonkoko = 1;
        while( Math.pow(ruudukonkoko,3) < molekyylilkm ) {
            ruudukonkoko += 1;
        }
    }
    
    /**
     * laittaa laatikkoon molekyylit riittävän kauas toisistaan
     * 
     * myöskin laittaa atomit ulkoisten voimien rekisteriin
     * 
     * @param skoko laatikon koko
     * @param smolekyylilkm molekyylien määrä laatikossa
     */
    public void generoi(double skoko, int smolekyylilkm) {
        asetaparametrit(skoko,smolekyylilkm);
        int kohta = ruudukonkoko + 1;
        int laskuri = 0;
        for( int i = 1; i < kohta; i++ ) {
            for( int j = 1; j < kohta; j++ ) {
                for( int k = 1; k < kohta; k++ ) {
                    molekyylilista[laskuri] = new vetykaasu((double)i*(koko/kohta),(double)j*(koko/kohta),(double)k*(koko/kohta));
                    atomilkm += molekyylilista[laskuri].annaatomilkm();
                    for( int l = 0; l < molekyylilista[laskuri].annaatomilkm(); l++ ) {
                        voimarekisteri.laitaatomirekisteriin(molekyylilista[laskuri].viittausatomiin(l),laskuri);
                    }
                    laskuri += 1;
                    if(laskuri == molekyylilkm) { break; }
                }
                if(laskuri == molekyylilkm) { break; }
            }
            if(laskuri == molekyylilkm) { break; }
        }
    }
    
    /**
     * antaa molekyylille pikkaisen vauhtia johonkin suuntaan
     */
    public void perturboi() {
        // perturbaatio siirtyy pallokoordinaatistoon kunhan ehtii
        for( int i = 0; i < molekyylilkm; i++ ) {
            molekyylilista[i].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0,0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0);
        }
    }
    
    /**
     * ajaa koko simulaation
     * 
     * laskee ensin ulkoiset voimat, sitten sisäiset voimat ja sitten liikuttaa
     * kaikkia molekyylejä
     * 
     * @param dt aika-askeleen koko
     * @param askelia aika-askelten lukumäärä
     * @param resoluutio kuinka monen askeleen välein otetaan tunnuslukuja talteen
     */
    public void simuloi(double dt, int askelia, int resoluutio) {
        for( int i = 0; i < askelia; i++ ) {
            voimarekisteri.ulkoiset();
            for( int j = 0; j < molekyylilkm; j++ ) { molekyylilista[j].sisaiset(); }
            for( int j = 0; j < molekyylilkm; j++ ) { molekyylilista[j].liikuta(dt,koko); }
            if( i % resoluutio == 0 ) {
                for( int j = 0; j < molekyylilkm; j++ ) { 
                    aineistokasittelija.laitapaikkadataan((int)(i/resoluutio),j,molekyylilista[j].annasijainnit2()); 
                }
            }
        }
    }
    
    public double nopeussumma() {
        double summa = 0.0;
        for( int i = 0; i < molekyylilkm; i++ ) {
            summa += molekyylilista[i].annanopeus();
        }
        return summa;
    }
    
}
