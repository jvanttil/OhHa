package md;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author jvanttil
 */
public class tiedostokasittelija {
    
    private PrintWriter kirjoittaja;
    
    public tiedostokasittelija() {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File ("outfilee.txt"));
        } catch (Exception e) {
            System.out.println("Ei natsaa");
        }
    }
    
    public void laitavektoritiedostoon(String jepa) {
        System.out.println(jepa+" ");
    }
    
    public void vaihdarivia() {
        kirjoittaja.println("");
    }
    
    public void laitakiinni() {
        kirjoittaja.close();
    }
    
}
