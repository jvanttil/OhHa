package md.simulaatio;

/**
 * @author jvanttil
 */
public class atomi {
    
    protected double massa;
    protected double sijaintix;
    protected double sijaintiy;
    protected double sijaintiz;
    protected double nopeusx;
    protected double nopeusy;
    protected double nopeusz;
    protected double kertymax;
    protected double kertymay;
    protected double kertymaz;
    
    public double annax() { return sijaintix; }
    public double annay() { return sijaintiy; }
    public double annaz() { return sijaintiz; }
    
    public double annanopeusx() { return nopeusx; }
    public double annanopeusy() { return nopeusy; }
    public double annanopeusz() { return nopeusz; }
    
    public void kerryta( double sx, double sy, double sz ) {
        kertymax += sx;
        kertymay += sy;
        kertymaz += sz;
    }
    
    public void nollaavoimat() {
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
    
    public void liikuta(double dt,double koko) {
        double uusisijaintix = sijaintix + nopeusx*dt + (kertymax/(2.0*massa))*dt*dt;
        double uusisijaintiy = sijaintiy + nopeusy*dt + (kertymay/(2.0*massa))*dt*dt;
        double uusisijaintiz = sijaintiz + nopeusz*dt + (kertymaz/(2.0*massa))*dt*dt;
        double uusinopeusx = nopeusx + (kertymax/(2.0*massa))*dt;
        double uusinopeusy = nopeusy + (kertymay/(2.0*massa))*dt;
        double uusinopeusz = nopeusz + (kertymaz/(2.0*massa))*dt;
        if( uusisijaintix > koko ) {
            uusisijaintix = 2.0*koko - uusisijaintix;
            uusinopeusx = -1.0*uusinopeusx;
        }
        if( uusisijaintiy > koko ) {
            uusisijaintiy = 2.0*koko - uusisijaintiy;
            uusinopeusy = -1.0*uusinopeusy;
        }
        if( uusisijaintiz > koko ) {
            uusisijaintiz = 2.0*koko - uusisijaintiz;
            uusinopeusz = -1.0*uusinopeusz;
        }
        if( uusisijaintix < 0 ) {
            uusisijaintix = -1.0*uusisijaintix;
            uusinopeusx = -1.0*uusinopeusx;
        }
        if( uusisijaintiy < 0 ) {
            uusisijaintiy = -1.0*uusisijaintiy;
            uusinopeusy = -1.0*uusinopeusy;
        }
        if( uusisijaintiz < 0 ) {
            uusisijaintiz = -1.0*uusisijaintiz;
            uusinopeusz = -1.0*uusinopeusz;
        }
        sijaintix = uusisijaintix;
        sijaintiy = uusisijaintiy;
        sijaintiz = uusisijaintiz;
        nopeusx = uusinopeusx;
        nopeusy = uusinopeusy;
        nopeusz = uusinopeusz;
        nollaavoimat();
    }
    
    public void lisaanopeus(double sx, double sy, double sz) {
        nopeusx = nopeusx + sx;
        nopeusy = nopeusy + sy;
        nopeusz = nopeusz + sz;
    }
    
    public void kertymatruudulle() {
        System.out.print(sijaintix+" "+sijaintiy+" "+sijaintiz+" ");
    }
    
}
