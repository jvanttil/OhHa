package md.grafiikka;

import java.awt.*;
import javax.swing.*;
import md.aineistokasittely.aineistokasittelija;

/**
 * Piirtää liike-energiasta ja potentiaalienergiasta käyrät ruudulle
 * 
 * @author jvanttil
 */
public class graafipaneeli extends JPanel {
    
    private int simulaatiopituus;
    private int ikkunankoko;
    private pallo[] liikeenergiat;
    private pallo[] potentiaalienergiat;
    private double ymaksimi;
    
    public graafipaneeli( int simulaatiopituus ) {
        this.ikkunankoko = 500;
        this.simulaatiopituus = simulaatiopituus;
        this.liikeenergiat = new pallo[this.simulaatiopituus];
        this.potentiaalienergiat = new pallo[this.simulaatiopituus];
        this.ymaksimi = aineistokasittelija.annamaksimiliikeenergia();
        for( int i = 0; i < simulaatiopituus; i++ ) {
            this.liikeenergiat[i] = new pallo(
                    (int)(i*this.ikkunankoko/this.simulaatiopituus),
                    500 - (int)(this.ikkunankoko*aineistokasittelija.haeliikeenergia(i)/ymaksimi),
                    Color.white,3);
            this.potentiaalienergiat[i] = new pallo(
                    (int)(i*this.ikkunankoko/this.simulaatiopituus),
                    500 - (int)(this.ikkunankoko*aineistokasittelija.haepotentiaalienergia(i)/ymaksimi),
                    Color.red,3);
        }
        setPreferredSize( new Dimension( ikkunankoko, ikkunankoko ));
        setBackground( Color.black );
    }
    
    public void paintComponent( Graphics page ) {
        super.paintComponent( page );
        for( int i = 0; i < this.simulaatiopituus; i++ ) {
            this.liikeenergiat[i].draw( page );
            this.potentiaalienergiat[i].draw( page );
        }
    }
    
}
