package md.aineistokasittely;

import java.io.File;
import java.io.PrintWriter;

/**
 * ottaa vastaan simulaatiosta lukuja ja tallettaa tiedostoon
 * @author jvanttil
 */
public class aineistokasittelija {
    
    private double laatikonkoko;
    private int molekyylimaara;
    private double askelkoko;
    private int askelmaara;
    private int resoluutio;
    private int rivit;
    private int paikkasarakkeet;
    private int liikeenergiasarakkeet;
    private static double[] aikadata;
    private static double[][] paikkadata;
    private static double[][] liikeenergiadata;
    
    public aineistokasittelija(double ltkoko, int mollkm, double dt, int steps, int reso) {
        laatikonkoko = ltkoko;
        molekyylimaara = mollkm;
        askelkoko = dt;
        askelmaara = steps;
        resoluutio = reso;
        rivit = (int)(askelmaara/resoluutio);
        paikkasarakkeet = molekyylimaara*3*2;
        liikeenergiasarakkeet = molekyylimaara;
        paikkadata = new double[rivit][paikkasarakkeet];
        aikadata = new double[rivit];
        liikeenergiadata = new double[rivit][liikeenergiasarakkeet];
        alustataulukot();
    }
    
    /**
     * täyttää aineistokäsittelijän taulukot nollilla.
     */
    private void alustataulukot() {
        for( int i = 0; i < rivit; i++ ) {
            aikadata[i] = 0;
            for( int j = 0; j < paikkasarakkeet; j++ ) {
                paikkadata[i][j] = 0.0;
            }
            for( int j = 0; j < liikeenergiasarakkeet; j++ ) {
                liikeenergiadata[i][j] = 0.0;
            }
        }
    }
    
    /**
     * täyttää paikkadatataulukkoa atomien sijainneilla
     * 
     * staattinen paikkadatataulukko tallettaa jokaisen atomin sijainnin 
     * jokaisena talletettavana ajan hetkenä (resoluution mukaan)
     * 
     * taulukko kutsuu tätä metodia
     * 
     * @param rivi taulukon tallennusrivi
     * @param molindeksi monesko molekyyli on talletettavana
     * @param luvut kuusi sijaintilukua järjestyksessä [x1,y1,z1,x2,y2,z2]
     */
    public static void laitapaikkadataan(int rivi, int molindeksi, double[] luvut) {
        for( int i = 0; i < 6; i++ ) {
            paikkadata[rivi][i+(6*molindeksi)] = luvut[i];
        }
    }
    
    /**
     * täyttää aikadatataulukkoa
     * 
     * laatikko kutsuu tätä metodia
     * 
     * @param rivi taulukon tallennusrivi
     * @param luku tallennettava luku (aika)
     */
    public static void laitaaikadataan(int rivi, double luku) {
        aikadata[rivi] = luku;
    }
    
    /**
     * laittaa liike-energiadatatauluun luvun. taulukko kutsuu tätä
     * metodia
     * 
     * @param rivi rivi, jolle luku syötetään
     * @param sarake sarake, jolle luku syötetään
     * @param luku syötettävä luku
     */
    public static void laitaliikeenergiadataan(int rivi, int sarake, double luku) {
        liikeenergiadata[rivi][sarake] = luku;
    }
    
    /**
     * laittaa paikkadatataulun ruudulle niin että näkee et homma toimii
     */
    public void paikkadataruudulle() {
        for( int i = 0; i < rivit; i++ ) {
            System.out.printf("%.4f ", aikadata[i]);
            for( int j = 0; j < paikkasarakkeet; j++ ) {
                System.out.printf("%.2f ", paikkadata[i][j]);
            }
            System.out.println("");
        }
    }
    
    /**
     * tallentaa koko paikkadatataulukon tiedostoon aikadatalla 
     * täydennettynä
     * 
     * ensimmäinen sarake sisältää ajat
     * 
     * @param tiedostonimi tallennustiedoston nimi
     */
    public void paikkadatatiedostoon(String tiedostonimi) {
        try{
            PrintWriter kirjoittaja = new PrintWriter(new File(tiedostonimi));
            for( int i = 0; i < rivit; i++ ) {
                System.out.printf("%.4f ", aikadata[i]);
                for( int j = 0; j < paikkasarakkeet; j++ ) {
                    System.out.printf("%.4f ", paikkadata[i][j]);
                }
                System.out.println("");
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }
    }
    
    /**
     * tulostaa tannennetut liike-energiat ruudulle
     */
    public void liikeenergiadataruudulle() {
        for( int i = 0; i < rivit; i++ ) {
            System.out.printf("%.2f ", aikadata[i]);
            for( int j = 0; j < liikeenergiasarakkeet; j++ ) {
                System.out.printf("%.3f ", liikeenergiadata[i][j]*100);
            }
            System.out.println("");
        }
    }
    
    /**
     * hakee paikkadatataulukosta luvun
     * 
     * animaatio käyttää tätä metodia
     * 
     * @param rivi 
     * @param sarake
     * @return paikkadata halutusta kohdasta taulukkoa
     */
    public static double haetaulukosta( int rivi, int sarake ) {
        return paikkadata[rivi][sarake];
    }
    
    public int annarivit() {
        return rivit;
    }
    
    public int annapaikkadatasarakkeet() {
        return paikkasarakkeet;
    }
}
