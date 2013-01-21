package md;

import java.io.File;
import java.io.FileWriter;

/**
 * @author jvanttil
 */
public class tiedostokasittelija {
    
    public void kirjoitatiedostoon(String tiedostonNimi, String teksti) throws Exception {
        FileWriter kirjoittaja = new FileWriter(tiedostonNimi);
        kirjoittaja.write(teksti);
        kirjoittaja.close();
    }

    public void lisaatiedostoon(String tiedostonNimi, String teksti) throws Exception {
        FileWriter kirjoittaja = new FileWriter(tiedostonNimi);
        kirjoittaja.append(teksti);
        kirjoittaja.close();
    }
    
    public void laitavektoritiedostoon(String jepa) {
        System.out.println(jepa+" ");
    }
    
}
