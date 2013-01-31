package md.simulaatio;

import java.io.FileWriter;
import java.util.Random;

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
    
    public void generoi(double skoko, int smolekyylilkm) {
        // generointimetodin pilkkominen olisi hyvä idea jos mahdollista
        koko = skoko;
        molekyylilkm = smolekyylilkm;
        molekyylilista = new molekyyli[molekyylilkm];
        voimarekisteri = new ulkoisetvoimat(molekyylilkm*2);
        ruudukonkoko = 1;
        while( Math.pow(ruudukonkoko,3) < molekyylilkm ) {
            ruudukonkoko += 1;
        }
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
    
    public void perturboi() {
        // perturbaatio siirtyy pallokoordinaatistoon kunhan ehtii
        for( int i = 0; i < molekyylilkm; i++ ) {
            molekyylilista[i].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0,0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0);
        }
    }
    
    public void simuloi(double dt, int askelia) {
        // tulostusproseduuri on muutettava siten, että tiedot tallentuvat taulukkoon -> aineiston käsittelijään
        try {
        FileWriter kirjoittaja = new FileWriter("ulos.txt");
        for( int i = 0; i < askelia; i++ ) {
            String tulostus = "";
            voimarekisteri.ulkoiset();
            for( int j = 0; j < molekyylilkm; j++ ) { molekyylilista[j].sisaiset(); }
            for( int j = 0; j < molekyylilkm; j++ ) { molekyylilista[j].liikuta(dt,koko); }
            for( int j = 0; j < molekyylilkm; j++ ) { tulostus = tulostus + molekyylilista[j].annasijainnit()+" "; }
            kirjoittaja.append(tulostus);
            kirjoittaja.append("\r\n");
        }
        kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("tiedoston käsittely ei onnistu");
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
