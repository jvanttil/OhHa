package md.simulaatio;

import java.util.Random;
import md.aineistokasittely.aineistokasittelija;

/**
 * simulaatiolaatikko
 * 
 * suorittaa simulaation ja lähettää atomien paikat, liike-energiat
 * ja potentiaalienergiat aineistokäsittelijä-luokan staattiisiin 
 * taulukoihin
 * 
 * @author jvanttil
 */
public class laatikko {
    
    private double laatikonkoko;
    private int molekyylilkm;
    private int atomilkm;
    private int ruudukonkoko;
    private molekyyli[] molekyylilista;
    private ulkoisetvoimat voimarekisteri;
    private Random rng;
    public double muunnoskerroin = 74.50737;
    
    /**
     * laatikkokonstruktori asettaa atomilukumäärän nollaksi
     * ja luo satunnaislukugeneraattorin perturbointimetodia
     * varten.
     * 
     * laatikon parametrit asetetaan asetaparametrit-metodilla, 
     * koska mahtuuko-metodi pitää ajaa parametrien sopivuuden
     * varmistamiseksi
     */
    public laatikko() {
        this.atomilkm = 0;
        this.rng = new Random();
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
        if( koekoko/koeruudukonkoko < 4 ) {
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
     * @param laatikonkoko laatikon koko
     * @param molekyylilkm molekyylien määrä laatikossa
     */
    private void asetaparametrit(double laatikonkoko, int molekyylilkm) {
        this.laatikonkoko = laatikonkoko;
        this.molekyylilkm = molekyylilkm;
        this.molekyylilista = new molekyyli[this.molekyylilkm];
        this.voimarekisteri = new ulkoisetvoimat(this.molekyylilkm*2);
        this.ruudukonkoko = 1;
        while( Math.pow(this.ruudukonkoko,3) < this.molekyylilkm ) {
            this.ruudukonkoko += 1;
        }
    }
    
    /**
     * laittaa laatikkoon molekyylit riittävän kauas toisistaan
     * 
     * myöskin laittaa atomit ulkoisten voimien rekisteriin
     * 
     * @param laatikonkoko laatikon koko
     * @param molekyylilkm molekyylien määrä laatikossa
     */
    public void generoi(double laatikonkoko, int molekyylilkm) {
        asetaparametrit(laatikonkoko,molekyylilkm);
        int kohta = this.ruudukonkoko + 1;
        int laskuri = 0;
        for( int i = 1; i < kohta; i++ ) {
            for( int j = 1; j < kohta; j++ ) {
                for( int k = 1; k < kohta; k++ ) {
                    this.molekyylilista[laskuri] = new vetykaasu((double)i*(laatikonkoko/kohta),(double)j*(laatikonkoko/kohta),(double)k*(laatikonkoko/kohta));
                    this.atomilkm += this.molekyylilista[laskuri].annaatomilkm();
                    for( int l = 0; l < this.molekyylilista[laskuri].annaatomilkm(); l++ ) {
                       this.voimarekisteri.laitaatomirekisteriin(this.molekyylilista[laskuri].viittausatomiin(l),laskuri);
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
     * antaa molekyylille pikkaisen vauhtia satunnaiseen suuntaan.
     */
    public void perturboi() {
        double theta;
        double phi;
        for( int i = 0; i < this.molekyylilkm; i++ ) {
            phi   = 2*Math.PI*rng.nextDouble();
            theta = Math.PI*rng.nextDouble();
            this.molekyylilista[i].perturboi(0.5*Math.sin(theta)*Math.cos(phi),0.5*Math.sin(theta)*Math.sin(phi),0.5*Math.cos(theta));
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
            if( i % resoluutio == 0 ) {
                aineistokasittelija.laitaaikadataan((int)(i/resoluutio),i*dt);
                for( int j = 0; j < molekyylilkm; j++ ) { 
                    aineistokasittelija.laitapaikkadataan((int)(i/resoluutio),j,molekyylilista[j].annasijainnit()); 
                    aineistokasittelija.laitaliikeenergiadataan((int)(i/resoluutio),j*2,muunnoskerroin*molekyylilista[j].annaliikeenergia(0));
                    aineistokasittelija.laitaliikeenergiadataan((int)(i/resoluutio),j*2+1,muunnoskerroin*molekyylilista[j].annaliikeenergia(1));
                    aineistokasittelija.laitapotentiaalienergiadataan((int)(i/resoluutio),j*2,0.5*dt*muunnoskerroin*molekyylilista[j].annakertymasumma(0));
                    aineistokasittelija.laitapotentiaalienergiadataan((int)(i/resoluutio),j*2+1,0.5*dt*muunnoskerroin*molekyylilista[j].annakertymasumma(1));
                }
            }
            for( int j = 0; j < molekyylilkm; j++ ) { molekyylilista[j].liikuta(dt,laatikonkoko); }
        }
    }
    
    /**
     * palauttaa halutun molekyylin halutun atomin halutun koordinatin
     * 
     * metodia ei käytetä tällä hetkellä missään!
     * 
     * @param molekyyli molekyylilistan indeksi
     * @param atomi joko 0 tai 1 kaksiatomisessa molekyylissä
     * @param koordinaatti 0 on x, 1 on y, 2 on z
     * @return sijainti halutun koordinaatin suhteen
     */
    public double annasijainti( int molekyyli, int atomi, int koordinaatti ) {
        if( koordinaatti == 0 ) {
            return molekyylilista[molekyyli].annasijaintix(atomi);
        } else if ( koordinaatti == 1 ) {
            return molekyylilista[molekyyli].annasijaintiy(atomi);
        } else if ( koordinaatti == 2 ) {
            return molekyylilista[molekyyli].annasijaintiy(atomi);
        } else {
            return 0.0;
        }
    }
    
    /**
     * laskee ja palauttaa molekyylien nopeuksien summan
     * 
     * @return laatikon molekyylien nopeuksien summa
     */
    public double nopeussumma() {
        double summa = 0.0;
        for( int i = 0; i < molekyylilkm; i++ ) {
            molekyylilista[i].laskenopeus();
            summa += molekyylilista[i].annanopeus();
        }
        return summa;
    }
    
}
