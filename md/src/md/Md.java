package md;

import md.simulaatio.laatikko;

/**
 * @author jvanttil
 */

public class Md {

    public static void main(String[] args) {
        
        laatikko mesta = new laatikko();
        mesta.generoi();
        mesta.simuloi(0.4,600);
    }
}
