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
    private int simulaatiopituus;
    private piirto animaatio;
    
    /**
     * käyttöliittymäkonstruktori
     */
    public tekstikayttoliittyma(){
        ltk = new laatikko();
        resoluutio = 10;
    }
    
    /**
     * hoitaa koko simulaatioproseduurin.
     */
    public void aja() {
        alusta();
        aineisto = new aineistokasittelija(laatikonkoko,molekyylimaara,askelkoko,askelmaara,resoluutio);
        ltk.generoi(laatikonkoko,molekyylimaara);
        ltk.perturboi();
        System.out.println("simuloidaan ");
        ltk.simuloi(askelkoko,askelmaara,resoluutio);
        System.out.println("simulaatio valmis ");
        tallennadata();
        aineisto.laskekeskiarvot();
        kerrotulokset();
        animaatio = new piirto(molekyylimaara*2,laatikonkoko,simulaatiopituus);
        animaatio.aktivoi();
        
    }
    
    /**
     * kirjoittaa ruudulle keskeisimpiä tuloksia simulaatiosta.
     */
    private void kerrotulokset() {
        System.out.println("liike-energian keskiarvo simulaation lopussa oli " 
                + aineistokasittelija.haeliikeenergia((int)(askelmaara/resoluutio)-1) + " eV");
        System.out.println("tällä perusteella kaasun lämpötila oli arviolta " 
                + 14746.98*aineistokasittelija.haeliikeenergia((int)(askelmaara/resoluutio)-1) + " K");
    }
    
    /**
     * tallentaa tiedostoihin atomien paikat, liike-energiat ja potentiaalienergiat.
     */
    private void tallennadata() {
        aineisto.kirjoitadatatiedostoon(0,"paikat.txt");
        aineisto.kirjoitadatatiedostoon(1,"ke.txt");
        aineisto.kirjoitadatatiedostoon(2,"pe.txt");
        System.out.println("atomien sijainnit kirjoitettu tiedostoon paikat.txt");
    }
    
    /**
     * kutsuu metodit, jotka kysyvät käyttäjältä simulaation parametrejä.
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
            System.out.println("Minkä kokoinen laatikko tehdään? (minimi 10.0 maksimi 50.0 suositus 18.0) ");
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
        try {
            double yriteluku = Double.parseDouble(syote);
            if( (yriteluku >= 10.0) && (yriteluku <= 50.0) ) {
                laatikonkoko = yriteluku;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Laatikon koon pitää olla luku!");
            return false;
        }
    }
        
    
    /**
     * kysyy käyttäjältä kuinka monta molekyyliä laitetaan laatikkoon.
     */
    private void kysymolekyylimaara() {
        while( true ) {
            System.out.println("Anna molekyylien lukumäärä. (minimi 1 maksimi 30 suositus 8)");
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
        try {
            int yriteluku = Integer.parseInt(syote);
            if( (yriteluku > 0) && (yriteluku < 31) ) {
                if( ltk.mahtuuko( laatikonkoko, yriteluku ) ) {
                    molekyylimaara = yriteluku;
                    return true;
                }
                System.out.println("Määrä ei mahdu laatikkoon");
                return false;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Molekyylimäärän pitää olla kokonaisluku!");
            return false;
        }
    }
    
    /**
     * kysyy käyttäjältä simulaation tarkkuuden eli aika-askelen koon.
     */
    private void kysyaskelkoko() {
        while( true ) {
            System.out.println("Anna simulaation tarkkuus eli askelkoko. (minimi 0.05 maksimi 1.0 suositus 0.2)");
            String syote = input.nextLine();
            if( asetaaskelkoko(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva koko, kokeile uudestaan.");
        }
        System.out.println("simulaation askelkoko on nyt " + askelkoko);
    }
    
    /**
     * asettaa simulaation askelkoon jos syöte on sopiva
     * 
     * @param syote käyttäjältä kysytty syöte
     * @return true jos syöte on sopiva, false jos ei ole
     */
    public boolean asetaaskelkoko(String syote) {
        try {
            double yriteluku = Double.parseDouble(syote);
            if( (yriteluku >= 0.05) && (yriteluku <= 1.0) ) {
                askelkoko = yriteluku;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Askelkoon pitää olla luku");
            return false;
        }
    }
    
    /**
     * kysyy käyttäjältä simulaation pituuden eli askelten lukumäärän.
     */
    public void kysyaskelmaara() {
        while( true ) {
            System.out.println("Anna simulaation pituus eli askelmaara. (minimi 100 maksimi 20000 suositus 8000)");
            String syote = input.nextLine();
            if( asetaaskelmaara(syote) ) {
                break;
            }
            System.out.println("Ei ole sopiva pituus, kokeile uudestaan.");
        }
        System.out.println("simulaation pituus on nyt " + askelmaara + " askelta.");
    }
    
    /**
     * asettaa simulaation askelmäärän eli pituuden jos syöte on sopiva
     * 
     * @param syote käyttäjältä kysytty syöte
     * @return true jos syöte on sopiva, false jos ei ole
     */
    public boolean asetaaskelmaara(String syote) {
        try {
            double yriteluku = Double.parseDouble(syote);
            if( (yriteluku >= 100.0) && (yriteluku <= 20000.0) ) {
                    askelmaara = 10*(int)(yriteluku/10.0);
                    simulaatiopituus = (int)(askelmaara/resoluutio);
                    return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Askelmäärän pitää olla kokonaisluku!");
            return false;
        }
    }
    
    public double annalaatikonkoko() { return laatikonkoko; }
    public int annamolekyylimaara() { return molekyylimaara; }
    public double annaaskelkoko() { return askelkoko; }
    public double annaaskelmaara() { return askelmaara; }
    public int annasimulaatiopituus() { return simulaatiopituus; }
}
