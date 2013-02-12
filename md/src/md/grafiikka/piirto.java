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
    
    public piirto( int lkm, double koko ) {
        ruutu = new JFrame("ikkuna");
        atomilkm = lkm;
        laatikonkoko = koko;
        ruutu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * käynnistää piirtämisen
     */
    public void aktivoi() {
        ruutu.getContentPane().add(new pallopaneeli(atomilkm,laatikonkoko));
        ruutu.pack();
        ruutu.setVisible(true);
    }
    
}
