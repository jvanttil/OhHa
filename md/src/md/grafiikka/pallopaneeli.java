package md.grafiikka;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import md.aineistokasittely.aineistokasittelija;

/**
 * @author jvanttil
 */
public class pallopaneeli extends JPanel {
    
    private int pallolkm;
    private int viivalkm;
    private int simulaatiopituus;
    private double laatikonkoko;
    private int ikkunankoko;
    private pallo[] pallot;
    private viiva[] viivat;
    private int intervalli  = 35;
    private Timer ajastin;
    private int askel;
    
    public pallopaneeli( int atomilkm, double laatikonkoko, int simulaatiopituus ) {
        this.askel = 0;
        this.ikkunankoko = 500;
        this.simulaatiopituus = simulaatiopituus;
        this.pallolkm = atomilkm;
        this.pallot = new pallo[pallolkm];
        for( int i = 0; i < pallolkm; i++ ) {
            this.pallot[i] = new pallo();
        }
        this.viivalkm = this.pallolkm/2;
        this.viivat = new viiva[viivalkm];
        for( int i = 0; i < viivalkm; i++ ) {
            this.viivat[i] = new viiva();
        }
        this.laatikonkoko = laatikonkoko;
        setPreferredSize( new Dimension( ikkunankoko, ikkunankoko ));
        setBackground( Color.black );
        ajastin = new Timer(intervalli, new ajastintoiminto());
    }
    
    public void setAnimation(boolean turnOnOff) {
        if (turnOnOff) {
            ajastin.start();  // start animation by starting the timer.
        } else {
            ajastin.stop();   // stop timer
        }
    }
    
    public void paintComponent( Graphics page ) {
        super.paintComponent( page );
        /*
        for( int i = 0; i < pallolkm; i++ ) {
            haepaikat(0,i);
        }*/
        for( int i = 0; i < pallolkm; i++ ) {
            pallot[i].draw( page );
        }
        for( int i = 0; i < viivalkm; i++ ) {
            viivat[i].draw( page );
        }
    }
    
    class ajastintoiminto implements ActionListener {
        //================================================== actionPerformed
        /** ActionListener of the timer.  Each time this is called,
         *  the ball's position is updated, creating the appearance of
         *  movement.
         *@param e This ActionEvent parameter is unused.
         */
        public void actionPerformed(ActionEvent e) {
            //pallot[0].setBounds(getWidth(), getHeight());
            for( int i = 0; i < pallolkm; i++ ) {
                if( i%2 == 0 ) {
                    viivat[(int)Math.floor(i/2)].liikutaalku(haexpaikka(askel,i),haeypaikka(askel,i));
                } else {
                    viivat[(int)Math.floor(i/2)].liikutaloppu(haexpaikka(askel,i),haeypaikka(askel,i));
                }
                pallot[i].liikuta(haexpaikka(askel,i),haeypaikka(askel,i),haezpaikka(askel,i));
            }
            repaint();
            askel++;
            if( askel == simulaatiopituus ) { askel = 0; }
        }
    }
    
    public int haexpaikka( int rivi, int atomi ) {
        return (int)(aineistokasittelija.haetaulukosta(rivi,atomi*3)*(ikkunankoko/laatikonkoko));
    }
    
    public int haeypaikka( int rivi, int atomi ) {
        return (int)(aineistokasittelija.haetaulukosta(rivi,atomi*3+1)*(ikkunankoko/laatikonkoko));
    }
    
    public int haezpaikka( int rivi, int atomi ) {
        return (int)(5+20*aineistokasittelija.haetaulukosta(rivi,atomi*3+2)/laatikonkoko);
    }
    
}
