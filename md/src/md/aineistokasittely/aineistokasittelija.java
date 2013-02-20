package md.aineistokasittely;

import java.io.File;
import java.io.PrintWriter;

/**
 * ottaa vastaan simulaatiosta lukuja ja tallettaa tiedostoon
 * @author jvanttil
 */
public class aineistokasittelija {
    
    private double laatikonkoko;
    private int molekyylilkm;
    private double askelkoko;
    private int askelmaara;
    private int resoluutio;
    private static int rivit;
    private static int paikkasarakkeet;
    private static int energiasarakkeet;
    private static double[] aikadata;
    private static double[][] paikkadata;
    private static double[][] liikeenergiadata;
    private static double[] liikeenergiakeskiarvot;
    private static double[][] potentiaalienergiadata;
    private static double[] potentiaalienergiakeskiarvot;
    
    public aineistokasittelija(double laatikonkoko, int molekyylilkm, double dt, int steps, int resoluutio) {
        this.laatikonkoko = laatikonkoko;
        this.molekyylilkm = molekyylilkm;
        askelkoko = dt;
        askelmaara = steps;
        this.resoluutio = resoluutio;
        rivit = (int)(askelmaara/resoluutio);
        paikkasarakkeet = this.molekyylilkm*3*2;
        energiasarakkeet = this.molekyylilkm*2;
        paikkadata = new double[rivit][paikkasarakkeet];
        aikadata = new double[rivit];
        liikeenergiadata = new double[rivit][energiasarakkeet];
        liikeenergiakeskiarvot = new double[rivit];
        potentiaalienergiadata = new double[rivit][energiasarakkeet];
        potentiaalienergiakeskiarvot = new double[rivit];
        alustataulukot();
    }
    
    /**
     * täyttää aineistokäsittelijän taulukot nollilla.
     */
    private void alustataulukot() {
        for( int i = 0; i < rivit; i++ ) {
            aikadata[i] = 0.0;
            liikeenergiakeskiarvot[i] = 0.0;
            potentiaalienergiakeskiarvot[i] = 0.0;
            for( int j = 0; j < paikkasarakkeet; j++ ) {
                paikkadata[i][j] = 0.0;
            }
            for( int j = 0; j < energiasarakkeet; j++ ) {
                liikeenergiadata[i][j] = 0.0;
                potentiaalienergiadata[i][j] = 0.0;
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
     * laittaa liike-energiadatatauluun luvun, taulukko kutsuu tätä
     * metodia
     * 
     * @param rivi rivi/aika, jolle luku syötetään
     * @param sarake sarake/atomi, jolle luku syötetään
     * @param luku syötettävä luku
     */
    public static void laitaliikeenergiadataan(int rivi, int sarake, double luku) {
        liikeenergiadata[rivi][sarake] = luku;
    }
    
    /**
     * laittaa potentiaalienergiadatatauluun luvun, taulukko kutsuu tätä
     * metodia 
     * 
     * @param rivi rivi/aika, jolle luku syötetään
     * @param sarake sarake/atomi, jolle luku syötetään
     * @param luku syötettävä luku
     */
    public static void laitapotentiaalienergiadataan( int rivi, int sarake, double luku ) {
        potentiaalienergiadata[rivi][sarake] = luku;
    }
    
    /**
     * laskee liike-energialle ja potentiaalienergialle keskiarvot jokaiselle 
     * aika-askeleelle piirtoa varten.
     */
    public static void laskekeskiarvot() {
        for( int i = 0; i < rivit; i++ ) {
            for( int j = 0; j < energiasarakkeet; j++ ) {
                liikeenergiakeskiarvot[i] += liikeenergiadata[i][j];
                potentiaalienergiakeskiarvot[i] += potentiaalienergiadata[i][j];
            }
            liikeenergiakeskiarvot[i] = liikeenergiakeskiarvot[i]/energiasarakkeet;
            potentiaalienergiakeskiarvot[i] = potentiaalienergiakeskiarvot[i]/energiasarakkeet;
        }
    }
    
    /**
     * laittaa paikkadatataulun ruudulle niin että näkee et homma toimii.
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
     * tallentaa datataulukoita tiedostoon aikadatalla 
     * täydennettynä
     * 
     * ensimmäinen sarake sisältää ajat, sen perään liitetään
     * lukuja a) paikkadatasta, b) liike-energiadatasta tai
     * c) potentiaalienergiadatasta
     * 
     * @param data 0 jos halutaan tallentaa paikat, 1 jos KE, 2 jos PE
     * @param tiedostonimi tallennustiedoston nimi
     */
    public void kirjoitadatatiedostoon( int data, String tiedostonimi ) {
        try{
            PrintWriter kirjoittaja = new PrintWriter(new File(tiedostonimi));
            for( int i = 0; i < rivit; i++ ) {
                kirjoittaja.printf("%.3f ", aikadata[i]);
                if(data == 0) {
                    for( int j = 0; j < paikkasarakkeet; j++ ) {
                        kirjoittaja.printf("%.8f ", paikkadata[i][j]);
                    }
                } else if(data == 1) {
                    for( int j = 0; j < energiasarakkeet; j++ ) {
                        kirjoittaja.printf("%.8f ", liikeenergiadata[i][j]);
                    }
                } else if(data == 2) {
                    for( int j = 0; j < energiasarakkeet; j++ ) {
                        kirjoittaja.printf("%.8f ", potentiaalienergiadata[i][j]);
                    }
                }
                kirjoittaja.println("");
            }
            kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe tiedostoon kirjoittamisessa!");
        }
    }
    
    /**
     * tulostaa tannennetut liike-energiat ruudulle, käytetään 
     * vain tarkastamaan, että koodi toimii.
     */
    public void liikeenergiadataruudulle() {
        for( int i = 0; i < rivit; i++ ) {
            System.out.printf("%.2f ", aikadata[i]);
            System.out.printf("%.5f ", liikeenergiakeskiarvot[i]);
            for( int j = 0; j < energiasarakkeet; j++ ) {
                System.out.printf("%.5f ", liikeenergiadata[i][j]);
            }
            System.out.println("");
        }
    }
    
    /**
     * tulostaa tannennetut potentiaalienergiat ruudulle, käytetään 
     * vain tarkastamaan, että koodi toimii.
     */
    public void potentiaalienergiadataruudulle() {
        for( int i = 0; i < rivit; i++ ) {
            System.out.printf("%.2f ", aikadata[i]);
            System.out.printf("%.5f ", potentiaalienergiakeskiarvot[i]);
            for( int j = 0; j < energiasarakkeet; j++ ) {
                System.out.printf("%.5f ", potentiaalienergiadata[i][j]);
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
    
    /**
     * hakee liike-energiakeskiarvoista halutun ajankohdan luvun
     * 
     * @param rivi ajankohta, jolta luku haetaan
     * @return liike-energian keskiarvo
     */
    public static double haeliikeenergia( int rivi ) {
        return liikeenergiakeskiarvot[rivi];
    }
    
    /**
     * hakee potentiaalienergiakeskiarvoista halutun ajankohdan luvut
     * 
     * @param rivi ajankohta, jolta luku haetaan
     * @return potentiaalienergian keskiarvo
     */
    public static double haepotentiaalienergia( int rivi ) {
        return potentiaalienergiakeskiarvot[rivi];
    }
    
    public int annarivit() {
        return rivit;
    }
    
    public int annapaikkadatasarakkeet() {
        return paikkasarakkeet;
    }
    
    /**
     * hakee liike-energiataulukosta maksimiarvon
     * 
     * @return maksimiliike-energia simulaatiossa
     */
    public static double annamaksimiliikeenergia() {
        double maksimi = 0.0;
        for( int i = 0; i < rivit; i++ ) {
            for( int j = 0; j < energiasarakkeet; j++ ) {
                if( liikeenergiadata[i][j] > maksimi ) {
                    maksimi = liikeenergiadata[i][j];
                }
            }
        }
        return maksimi;
    }
    
    /**
     * hakee potentiaalienergiataulukosta maksimiarvon
     * 
     * @return maksimipotentiaalienergia simulaatiossa
     */
    public static double annamaksimipotentiaalienergia() {
        double maksimi = 0.0;
        for( int i = 1; i < rivit; i++ ) {
            for( int j = 0; j < energiasarakkeet; j++ ) {
                if( potentiaalienergiadata[i][j] > maksimi ) {
                    maksimi = potentiaalienergiadata[i][j];
                }
            }
        }
        return maksimi;
    }
    
}
