package md.grafiikka;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author jvanttil
 */
public class kayttopaneeli extends JPanel{
    
    private pallopaneeli pallopaneeli;
    private graafipaneeli graafipaneeli;
    private int atomilkm;
    private double laatikonkoko;
    private int simulaatiopituus;
    
    kayttopaneeli( int atomilkm, double laatikonkoko, int simulaatiopituus ) {
        this.atomilkm = atomilkm;
        this.laatikonkoko = laatikonkoko;
        this.simulaatiopituus = simulaatiopituus;
        pallopaneeli = new pallopaneeli(atomilkm,laatikonkoko,simulaatiopituus);
        graafipaneeli = new graafipaneeli(simulaatiopituus);
        JButton kaynnistysnappi = new JButton("kaynnista");
        JButton pysaytysnappi = new JButton("pysayta");
        kaynnistysnappi.addActionListener(new kaynnistystoiminto());
        pysaytysnappi.addActionListener(new pysaytystoiminto());
        JPanel nappipaneeli = new JPanel();
        nappipaneeli.setLayout(new FlowLayout());
        nappipaneeli.add(kaynnistysnappi);
        nappipaneeli.add(pysaytysnappi);
        this.setLayout(new BorderLayout());
        this.add(nappipaneeli,BorderLayout.NORTH);
        this.add(pallopaneeli,BorderLayout.CENTER);
        this.add(graafipaneeli,BorderLayout.EAST);
    }
    
    class kaynnistystoiminto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pallopaneeli.setAnimation(true);
        }
    }
    
    class pysaytystoiminto implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pallopaneeli.setAnimation(false);
        }
    }
    
}
