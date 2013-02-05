package md.simulaatio;

import md.simulaatio.atomi;

/**
 * vetyluokka rakentuu atomiluokan päälle, vedyn massa on 1
 * @author jvanttil
 */
public class vety extends atomi{
    
    public vety() {
        massa = 1.0;
        sijaintix = 0.0;
        sijaintiy = 0.0;
        sijaintiz = 0.0;
        nopeusx = 0.0;
        nopeusy = 0.0;
        nopeusz = 0.0;
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
    
    public vety(double sx, double sy, double sz) {
        massa = 1.0;
        sijaintix = sx;
        sijaintiy = sy;
        sijaintiz = sz;
        nopeusx = 0.0;
        nopeusy = 0.0;
        nopeusz = 0.0;
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
}
