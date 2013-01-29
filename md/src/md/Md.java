package md;

import md.simulaatio.laatikko;

/**
 * @author jvanttil
 */

public class Md {

    public static void main(String[] args) {
        
        laatikko mesta = new laatikko();
        if( mesta.mahtuuko(10.0,4) ) {
            System.out.println("joo");
        } else {
            System.out.println("ei");
        }
        mesta.generoi(10.0,4);
        mesta.perturboi();
        mesta.simuloi(0.75,2000);
    }
}
