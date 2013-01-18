package md;

/**
 * @author jvanttil
 */

public class Md {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        laatikko mesta = new laatikko();
        mesta.generoi();
        mesta.simuloi(0.01,5);
    }
}
