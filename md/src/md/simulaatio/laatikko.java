package md.simulaatio;

import java.io.FileWriter;
import java.util.Random;
import md.tiedostokasittelija;

/**
 * @author jvanttil
 */
public class laatikko {
    
    private double koko;
    private int molekyylilkm;
    private int atomilkm;
    private molekyyli[] molekyylilista;
    private ulkoisetvoimat voimarekisteri;
    private Random rng;
    public tiedostokasittelija fio;
    
    public laatikko() {
        koko = 10.0;
        molekyylilkm = 2;
        atomilkm = 0;
        molekyylilista = new molekyyli[molekyylilkm];
        voimarekisteri = new ulkoisetvoimat(molekyylilkm*2);
        fio = new tiedostokasittelija();
        rng = new Random();
    }
    
    public void generoi() {
        molekyylilista[0] = new vetykaasu(8.0,8.0,5.0);
        atomilkm += molekyylilista[0].annaatomilkm();
        voimarekisteri.laitaatomirekisteriin(molekyylilista[0].viittausatomiin(1),0);
        voimarekisteri.laitaatomirekisteriin(molekyylilista[0].viittausatomiin(2),0);
        molekyylilista[0].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0,0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.0);
        molekyylilista[1] = new vetykaasu(2.0,2.0,5.0);
        atomilkm += molekyylilista[1].annaatomilkm();
        voimarekisteri.laitaatomirekisteriin(molekyylilista[1].viittausatomiin(1),1);
        voimarekisteri.laitaatomirekisteriin(molekyylilista[1].viittausatomiin(2),1);
        molekyylilista[1].perturboi(0.07,0.07,0.0,0.07,0.06,0.0);
    }
    
    public void simuloi(double dt, int askelia) {
        try {
        FileWriter kirjoittaja = new FileWriter("ulos.txt");
        for( int i = 0; i < askelia; i++ ) {
            molekyylilista[0].sisaiset();
            molekyylilista[1].sisaiset();
            voimarekisteri.ulkoiset();
            molekyylilista[0].liikuta(dt,koko);
            molekyylilista[1].liikuta(dt,koko);
            molekyylilista[0].ruudulle();
            molekyylilista[1].ruudulle();
            kirjoittaja.append(molekyylilista[0].annasijainnit()+" "+molekyylilista[1].annasijainnit());
            kirjoittaja.append("\r\n");
        }
        kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("imee");
        }
    }
    
}
