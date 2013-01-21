package md;

import java.io.FileWriter;

/**
 * @author jvanttil
 */
public class laatikko {
    
    private double kokox;
    private double kokoy;
    private double kokoz;
    private short molekyylilkm;
    private molekyyli[] molekyylilista;
    public tiedostokasittelija fio;
    
    public laatikko() {
        kokox = 10;
        kokoy = 10;
        kokoz = 10;
        molekyylilkm = 1;
        molekyylilista = new molekyyli[molekyylilkm];
        fio = new tiedostokasittelija();
    }
    
    public void generoi() {
        molekyylilista[0] = new vetykaasu();
    }
    
    public void simuloi(double dt, int askelia) {
        try {
        FileWriter kirjoittaja = new FileWriter("uloos.txt");
        for( int i = 0; i < askelia; i++ ) {
            molekyylilista[0].sisaiset();
            molekyylilista[0].liikuta(dt);
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
