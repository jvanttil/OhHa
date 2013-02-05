package md.kayttoliittyma;

import java.util.Scanner;
import md.simulaatio.laatikko;
import md.aineistokasittely.aineistokasittelija;

/**
 * @author jvanttil
 */
public class tekstikayttoliittyma {
    
    public static Scanner input = new Scanner(System.in);
    private laatikko ltk;
    private aineistokasittelija aineisto;
    private double laatikonkoko;
    private int molekyylimaara;
    private double askelkoko;
    private int askelmaara;
    private int resoluutio;
    
    public tekstikayttoliittyma(){
        ltk = new laatikko();
        resoluutio = 10;
    }
    
    /**
     * hoitaa koko simulaatioproseduurin
     */
    public void rullaa() {
        asetalaatikko();
        asetamolekyylit();
        asetaaskelkoko();
        asetaaskelmaara();
        aineisto = new aineistokasittelija(laatikonkoko,molekyylimaara,askelkoko,askelmaara,resoluutio);
        ltk.generoi(laatikonkoko,molekyylimaara);
        ltk.perturboi();
        ltk.simuloi(askelkoko,askelmaara,resoluutio);
        aineisto.ruudulle();
    }
    
    /**
     * kysyy käyttäjältä minkä kokoinen laatikko tehdään
     */
    public void asetalaatikko() {
        boolean valmis = false;
        while( !valmis ) {
            System.out.println("Minkä kokoinen laatikko tehdään? (minimi 10.0 maksimi 100.0) ");
            String lukutalteen = input.nextLine();
            if( Double.parseDouble(lukutalteen) < 10.0 ) {
                System.out.println("liian pieni laatikko, kokeile uudestaan");
            } else if ( Double.parseDouble(lukutalteen) > 100.0 ) {
                System.out.println("liian iso laatikko, kokeile uudestaan");
            } else {
                laatikonkoko = Double.parseDouble(lukutalteen);
                System.out.println("laatikon koko on nyt " + laatikonkoko);
                valmis = true;
            }
        }
    }
    
    /**
     * kysyy käyttäjältä kuinka monta molekyyliä laitetaan laatikkoon
     */
    public void asetamolekyylit() {
        boolean valmis = false;
        int yriteluku;
        while( !valmis ) {
            System.out.println("Montako molekyyliä laitetaan laatikkoon? ");
            String lukutalteen = input.nextLine();
            yriteluku = Integer.parseInt(lukutalteen);
            if( yriteluku < 1 ) {
                System.out.println("laita edes yksi, kokeile uudestaan");
            } else if ( yriteluku < 100 ) {
                if( ltk.mahtuuko(laatikonkoko,yriteluku) ) {
                    molekyylimaara = yriteluku;
                    System.out.println("molekyyleja laitettu " + molekyylimaara);
                    valmis = true;
                } else {
                    System.out.println("liian monta molekyylia, kokeile uudestaan");
                }
            } else {
                System.out.println("liian monta molekyylia, kokeile uudestaan");
            }
        }
    }
    
    /**
     * kysyy käyttäjältä simulaation tarkkuuden eli aika-askelen koon
     */
    public void asetaaskelkoko() {
        boolean valmis = false;
        double yriteluku;
        while( !valmis ) {
            System.out.println("Kuinka suurella askelkoolla simuloidaan?");
            System.out.println("Sopiva koko on väliltä 1.0 - 0.01");
            String lukutalteen = input.nextLine();
            yriteluku = Double.parseDouble(lukutalteen);
            if( (yriteluku < 0.01) || (yriteluku > 1.0) ) {
                System.out.println("ei ole sopiva luku, kokeile uudestaan");
            } else {
                askelkoko = yriteluku;
                System.out.println("Askelkooksi asetettu " + askelkoko);
                valmis = true;
            }
        }
    }
    
    /**
     * kysyy käyttäjältä simulaation pituuden eli askelten lukumäärän
     */
    public void asetaaskelmaara() {
        boolean valmis = false;
        int yriteluku;
        while( !valmis ) {
            System.out.println("Kuinka monta askelta simuloidaan?");
            System.out.println("Sopiva koko on väliltä 100 - 1000");
            String lukutalteen = input.nextLine();
            yriteluku = Integer.parseInt(lukutalteen);
            if( (yriteluku < 100) || (yriteluku > 1000) ) {
                System.out.println("ei ole sopiva luku, kokeile uudestaan");
            } else {
                askelmaara = yriteluku;
                System.out.println("Askelten määräksi asetettu " + askelmaara);
                valmis = true;
            }
        }
    }
    
}
