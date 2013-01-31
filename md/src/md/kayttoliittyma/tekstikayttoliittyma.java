package md.kayttoliittyma;

import java.util.Scanner;
import md.simulaatio.laatikko;

/**
 * @author jvanttil
 */
public class tekstikayttoliittyma {
    
    public static Scanner input = new Scanner(System.in);
    private laatikko ltk;
    private double laatikonkoko;
    private int molekyylimaara;
    
    public tekstikayttoliittyma(){
        
    }
    
    public void rullaa() {
        asetalaatikko();
        asetamolekyylit();
        ltk.generoi(laatikonkoko,molekyylimaara);
        ltk.perturboi();
        ltk.simuloi(0.75,100);
    }
    
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
    
    public void asetamolekyylit() {
        boolean valmis = false;
        while( !valmis ) {
            int yriteluku;
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
    
        /*
        laatikko mesta = new laatikko();
        if( mesta.mahtuuko(10.0,4) ) {
            System.out.println("joo");
        } else {
            System.out.println("ei");
        }
        mesta.generoi(10.0,4);
        mesta.perturboi();
        mesta.simuloi(0.75,2000);
        */
    
}
