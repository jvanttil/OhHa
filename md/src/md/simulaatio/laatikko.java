package md.simulaatio;

import java.io.FileWriter;
import java.util.Random;
//import md.tiedostokasittelija;

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
    //public tiedostokasittelija fio;
    
    public laatikko() {
        koko = 10.0;
        molekyylilkm = 4;
        atomilkm = 0;
        molekyylilista = new molekyyli[molekyylilkm];
        voimarekisteri = new ulkoisetvoimat(molekyylilkm*2);
        //fio = new tiedostokasittelija();
        rng = new Random();
        ruudukonkoko = 1;
        while( Math.pow(ruudukonkoko,3) < molekyylilkm ) {
            ruudukonkoko += 1;
        }
    }
    
    public boolean mahtuuko() {
        if( koko < ruudukonkoko*3 ) {
            return false;
        } else {
            return true;
        }
    }
    
    public void generoi() {
        int kohta = ruudukonkoko + 1;
        int laskuri = 0;
        for( int i = 1; i < kohta; i++ ) {
            for( int j = 1; j < kohta; j++ ) {
                for( int k = 1; k < kohta; k++ ) {
                    System.out.println("laskuri " + laskuri + " i " + i + " j " + j + " k " + k);
                    molekyylilista[laskuri] = new vetykaasu((double)i*(koko/kohta),(double)j*(koko/kohta),(double)k*(koko/kohta));
                    atomilkm += molekyylilista[laskuri].annaatomilkm();
                    for( int l = 0; l < molekyylilista[laskuri].annaatomilkm(); l++ ) {
                        voimarekisteri.laitaatomirekisteriin(molekyylilista[laskuri].viittausatomiin(l),laskuri);
                    }
                    molekyylilista[laskuri].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0,0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0);
                    laskuri += 1;
                    if(laskuri == molekyylilkm) { break; }
                }
                if(laskuri == molekyylilkm) { break; }
            }
            if(laskuri == molekyylilkm) { break; }
        }
        /*
        molekyylilista[0] = new vetykaasu(8.0,8.0,5.0);
        atomilkm += molekyylilista[0].annaatomilkm();
        voimarekisteri.laitaatomirekisteriin(molekyylilista[0].viittausatomiin(0),0);
        voimarekisteri.laitaatomirekisteriin(molekyylilista[0].viittausatomiin(1),0);
        molekyylilista[0].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0,0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0);
        molekyylilista[1] = new vetykaasu(2.0,2.0,5.0);
        atomilkm += molekyylilista[1].annaatomilkm();
        voimarekisteri.laitaatomirekisteriin(molekyylilista[1].viittausatomiin(0),1);
        voimarekisteri.laitaatomirekisteriin(molekyylilista[1].viittausatomiin(1),1);
        molekyylilista[1].perturboi(0.07,0.07,0.0,0.07,0.06,0.0);
        */
    }
    
    public void simuloi(double dt, int askelia) {
        try {
        FileWriter kirjoittaja = new FileWriter("ulos.txt");
        for( int i = 0; i < askelia; i++ ) {
            String tulostus = "";
            for( int j = 0; j < molekyylilkm; j++ ) {
                molekyylilista[j].sisaiset();
            }
            voimarekisteri.ulkoiset();
            for( int j = 0; j < molekyylilkm; j++ ) {
                molekyylilista[j].liikuta(dt,koko);
            }
            for( int j = 0; j < molekyylilkm; j++ ) {
                tulostus = tulostus + molekyylilista[j].annasijainnit()+" ";
            }
            kirjoittaja.append(tulostus);
            //kirjoittaja.append(molekyylilista[0].annasijainnit()+" "+molekyylilista[1].annasijainnit()+" "+molekyylilista[2].annasijainnit()
            //        +" "+molekyylilista[3].annasijainnit());
            kirjoittaja.append("\r\n");
        }
        kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("tiedoston käsittely ei onnistu");
        }
        System.out.println("ruudukon koko on " + ruudukonkoko);
    }
    
}
