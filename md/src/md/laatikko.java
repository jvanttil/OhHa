package md;

import java.io.FileWriter;
import java.util.Random;

/**
 * @author jvanttil
 */
public class laatikko {
    
    private double koko;
    private short molekyylilkm;
    private molekyyli[] molekyylilista;
    private Random rng;
    public tiedostokasittelija fio;
    
    public laatikko() {
        koko = 10.0;
        molekyylilkm = 1;
        molekyylilista = new molekyyli[molekyylilkm];
        fio = new tiedostokasittelija();
        rng = new Random();
    }
    
    public void generoi() {
        molekyylilista[0] = new vetykaasu(8.0,8.0,8.0);
        molekyylilista[0].perturboi(0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.05*rng.nextGaussian(),0.05*rng.nextGaussian());
    }
    
    public void simuloi(double dt, int askelia) {
        try {
        FileWriter kirjoittaja = new FileWriter("uloos.txt");
        for( int i = 0; i < askelia; i++ ) {
            molekyylilista[0].sisaiset();
            molekyylilista[0].liikuta(dt,koko);
            molekyylilista[0].ruudulle();
            kirjoittaja.append(molekyylilista[0].annasijainnit());
            kirjoittaja.append("\r\n");
        }
        kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("imee");
        }
    }
    
}
