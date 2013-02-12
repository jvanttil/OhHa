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
    private int sarakkeet;
    private static double[][] paikkadata;
    
    public aineistokasittelija(double ltkoko, int mollkm, double dt, int steps, int reso) {
        laatikonkoko = ltkoko;
        molekyylimaara = mollkm;
        askelkoko = dt;
        askelmaara = steps;
        resoluutio = reso;
        rivit = (int)(askelmaara/resoluutio);
        sarakkeet = 1+molekyylimaara*3*2;
        paikkadata = new double[rivit][sarakkeet];
        for( int i = 0; i < rivit; i++ ) {
            for( int j = 0; j < sarakkeet; j++ ) {
                paikkadata[i][j] = 0.0;
            }
        }
    }
    
    /**
     * täyttää paikkadatataulukkoa atomien sijainneilla
     * 
     * staattinen paikkadatataulukko tallettaa jokaisen atomin sijainnin 
     * jokaisena talletettavana ajan hetkenä (resoluution mukaan)
     * 
     * @param rivi taulukon tallennusrivi
     * @param molindeksi monesko molekyyli on talletettavana
     * @param luvut kuusi sijaintilukua järjestyksessä [x1,y1,z1,x2,y2,z2]
     */
    public static void laitapaikkadataan(int rivi, int molindeksi, double[] luvut) {
        for( int i = 0; i < 6; i++ ) {
            paikkadata[rivi][1+i+(6*molindeksi)] = luvut[i];
        }
    }
    
    /**
     * täyttää paikkadatataulukkoa ajalla
     * 
     * @param rivi taulukon tallennusrivi
     * @param luku tallennettava luku (aika)
     */
    public static void laitaaikadataan(int rivi, double luku) {
        paikkadata[rivi][0] = luku;
    }
    
    /**
     * laittaa paikkadatataulun ruudulle niin että näkee et homma toimii
     */
    public void ruudulle() {
        for( int i = 0; i < rivit; i++ ) {
            for( int j = 0; j < sarakkeet; j++ ) {
                System.out.printf("%.2f ", paikkadata[i][j]);
            }
            System.out.println("");
        }
    }
    
    public void tiedostoon(String tiedostonimi) {
        try{
            PrintWriter kirjoittaja = new PrintWriter(new File(tiedostonimi));
            for( int i = 0; i < rivit; i++ ) {
                for( int j = 0; j < sarakkeet; j++ ) {
                    System.out.printf("%.4f ", paikkadata[i][j]);
                }
                System.out.println("");
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedoston kirjoittamisessa!");
        }
    }
    
    public static double haetaulukosta( int rivi, int sarake ) {
        return paikkadata[rivi][sarake];
    }
    
    public int annarivit() {
        return rivit;
    }
    
    public int annasarakkeet() {
        return sarakkeet;
    }
}
