package md.grafiikka;

import javax.swing.*;

/**
 * piirtää atomien liikkeen paikkadatataulusta ruudulle ikkunaan
 * @author jvanttil
 */
public class piirto {
    
    private JFrame ruutu;
    private int atomilkm;
    private double laatikonkoko;
    private int simulaatiopituus;
    
    /**
     * alustaa JFramen piirtoa varten
     * 
     * @param atomilkm atomien lukumäärä
     * @param laatikonkoko laatikon koko
     * @param simulaatiopituus simulaatiosta kerätyn datan pituus
     */
    public piirto( int atomilkm, double laatikonkoko, int simulaatiopituus ) {
        ruutu = new JFrame("ikkuna");
        this.atomilkm = atomilkm;
        this.laatikonkoko = laatikonkoko;
        this.simulaatiopituus = simulaatiopituus;
        ruutu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * käynnistää piirtämisen.
     */
    public void aktivoi() {
        ruutu.getContentPane().add(new kayttopaneeli(atomilkm,laatikonkoko,simulaatiopituus));
        ruutu.pack();
        ruutu.setVisible(true);
    }
    
}
