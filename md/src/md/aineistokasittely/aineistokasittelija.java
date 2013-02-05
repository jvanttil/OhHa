package md.aineistokasittely;

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
     * täyttää paikkadatataulukkoa
     * 
     * staattinen paikkadatataulukko tallettaa jokaisen atomin sijainnin 
     * jokaisena talletettavana ajan hetkenä (resoluution mukaan)
     * 
     * @param rivi mille riville laitetaan
     * @param molindeksi monesko molekyyli on talletettavana
     * @param luvut kuusi lukua, kolme per atomi ja x, y, z
     */
    public static void laitapaikkadataan(int rivi, int molindeksi, double[] luvut) {
        for( int i = 0; i < 6; i++ ) {
            paikkadata[rivi][1+i+(6*molindeksi)] = luvut[i];
        }
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
}
