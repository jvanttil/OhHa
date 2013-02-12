package md.grafiikka;

import javax.swing.*;
import java.awt.*;
import md.aineistokasittely.aineistokasittelija;

/**
 * @author jvanttil
 */
public class pallopaneeli extends JPanel {
    
    private int pallolkm;
    private double laatikonkoko;
    private int ikkunankoko;
    private pallo[] pallot;
    
    public pallopaneeli( int lkm, double koko ) {
        ikkunankoko = 500;
        pallolkm = lkm;
        pallot = new pallo[pallolkm];
        for( int i = 0; i < pallolkm; i++ ) {
            pallot[i] = new pallo();
        }
        laatikonkoko = koko;
        setPreferredSize( new Dimension( ikkunankoko, ikkunankoko ));
        setBackground( Color.black );
    }
    
    public void paintComponent( Graphics page ) {
        super.paintComponent( page );
        for( int i = 0; i < pallolkm; i++ ) {
            haepaikat(0,i);
        }
        for( int i = 0; i < pallolkm; i++ ) {
            pallot[i].draw( page );
        }
        
    }
    
    public void haepaikat( int rivi, int atomi ) {
        pallot[atomi].setx((int)(aineistokasittelija.haetaulukosta(rivi,atomi*3+1)*(ikkunankoko/laatikonkoko)));
        pallot[atomi].sety((int)(aineistokasittelija.haetaulukosta(rivi,atomi*3+2)*(ikkunankoko/laatikonkoko)));
    }
    
}
