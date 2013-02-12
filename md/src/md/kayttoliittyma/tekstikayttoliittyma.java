package md.kayttoliittyma;

import java.util.Scanner;
import md.simulaatio.laatikko;
import md.aineistokasittely.aineistokasittelija;
import md.grafiikka.piirto;

/**
 * tekstikäyttöliittymä
 * kysyy käyttäjältä parametrejä, muodostaa laatikon ja simuloi
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
    private piirto animaatio;
    
    public tekstikayttoliittyma(){
        ltk = new laatikko();
        resoluutio = 10;
    }
    
    /**
     * hoitaa koko simulaatioproseduurin
     */
    public void aja() {
        alusta();
        aineisto = new aineistokasittelija(laatikonkoko,molekyylimaara,askelkoko,askelmaara,resoluutio);
        ltk.generoi(laatikonkoko,molekyylimaara);
        ltk.perturboi();
        ltk.simuloi(askelkoko,askelmaara,resoluutio);
        aineisto.ruudulle();
        //aineisto.tiedostoon("ulos.txt");
        animaatio = new piirto(molekyylimaara*2,laatikonkoko);
        animaatio.aktivoi();
    }
    
    /**
     * kutsuu metodit, jotka kysyvät käyttäjältä simulaation parametrejä
     */
    public void alusta() {
        kysylaatikonkoko();
        kysymolekyylimaara();
        kysyaskelkoko();
        kysyaskelmaara();
    }
    
    /**
     * kysyy käyttäjältä minkä kokoinen laatikko tehdään.
     */
    private void kysylaatikonkoko() {
        while( true ) {
            System.out.println("Minkä kokoinen laatikko tehdään? (minimi 10.0 maksimi 100.0) ");
            String syote = input.nextLine();
            if( asetalaatikonkoko(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva koko, yritä uudelleen.");
        }
        System.out.println("laatikon koko on nyt " + laatikonkoko);
    }
    
    /**
     * asettaa laatikon koon jos syöte on sopiva
     * 
     * @param syote käyttäjältä kysytty syöte
     * @return true jos syöte on sopiva false jos ei ole
     */
    public boolean asetalaatikonkoko(String syote) {
        double yriteluku = Double.parseDouble(syote);
        if( (yriteluku >= 10.0) && (yriteluku <= 100.0) ) {
            laatikonkoko = yriteluku;
            return true;
        }
        return false;
    }
    
    /**
     * kysyy käyttäjältä kuinka monta molekyyliä laitetaan laatikkoon.
     */
    private void kysymolekyylimaara() {
        while( true ) {
            System.out.println("Anna molekyylien lukumäärä. (minimi 1 maksimi 100)");
            String syote = input.nextLine();
            if( asetamolekyylimaara(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva määrä, kokeile uudestaan.");
        }
        System.out.println("molekyylien lukumäärä on nyt " + molekyylimaara);
    }
    
    
    /**
     * asettaa molekyylien lukumäärän jos syöte on sopiva
     * 
     * @param syote käyttäjältä kysytty syöte
     * @return true jos syöte on sopiva, false jos ei ole
     */
    public boolean asetamolekyylimaara(String syote) {
        int yriteluku = Integer.parseInt(syote);
        if( (yriteluku > 0) && (yriteluku < 101) ) {
            if( ltk.mahtuuko( laatikonkoko, yriteluku ) ) {
                molekyylimaara = yriteluku;
                return true;
            }
            System.out.println("Määrä ei mahdu laatikkoon");
            return false;
        }
        return false;
    }
    
    /**
     * kysyy käyttäjältä simulaation tarkkuuden eli aika-askelen koon.
     */
    private void kysyaskelkoko() {
        while( true ) {
            System.out.println("Anna simulaation tarkkuus eli askelkoko. (minimi 0.01 maksimi 1.0)");
            String syote = input.nextLine();
            if( asetaaskelkoko(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva koko, kokeile uudestaan.");
        }
        System.out.println("simulaation askelkoko on nyt " + molekyylimaara);
    }
    
    /**
     * asettaa simulaation askelkoon jos syöte on sopiva
     * 
     * @param syote käyttäjältä kysytty syöte
     * @return true jos syöte on sopiva, false jos ei ole
     */
    public boolean asetaaskelkoko(String syote) {
        double yriteluku = Double.parseDouble(syote);
        if( (yriteluku >= 0.01) && (yriteluku <= 1.0) ) {
            askelkoko = yriteluku;
            return true;
        }
        return false;
    }
    
    /**
     * kysyy käyttäjältä simulaation pituuden eli askelten lukumäärän.
     */
    public void kysyaskelmaara() {
        while( true ) {
            System.out.println("Anna simulaation pituus eli askelmaara. (minimi 100 maksimi 10000)");
            String syote = input.nextLine();
            if( asetaaskelmaara(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva pituus, kokeile uudestaan.");
        }
        System.out.println("simulaation pituus on nyt " + askelmaara + " askelta.");
    }
    
    public boolean asetaaskelmaara(String syote) {
        int yriteluku = Integer.parseInt(syote);
        if( (yriteluku >= 100) && (yriteluku <= 10000) ) {
                askelmaara = yriteluku;
                return true;
        }
        return false;
    }
    
    public double annalaatikonkoko() { return laatikonkoko; }
    public int annamolekyylimaara() { return molekyylimaara; }
    public double annaaskelkoko() { return askelkoko; }
    public double annaaskelmaara() { return askelmaara; }
    
}
