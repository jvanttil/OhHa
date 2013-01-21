package md;

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
    
    public void liikuta(double dt) {
        double uusisijaintix = sijaintix + nopeusx*dt + (kertymax/(2.0*massa))*dt*dt;
        double uusisijaintiy = sijaintiy + nopeusy*dt + (kertymay/(2.0*massa))*dt*dt;
        double uusisijaintiz = sijaintiz + nopeusz*dt + (kertymaz/(2.0*massa))*dt*dt;
        double uusinopeusx = nopeusx + (kertymax/(2.0*massa))*dt;
        double uusinopeusy = nopeusy + (kertymay/(2.0*massa))*dt;
        double uusinopeusz = nopeusz + (kertymaz/(2.0*massa))*dt;
        sijaintix = uusisijaintix;
        sijaintiy = uusisijaintiy;
        sijaintiz = uusisijaintiz;
        nopeusx = uusinopeusx;
        nopeusy = uusinopeusy;
        nopeusz = uusinopeusz;
        nollaavoimat();
    }
    
    public void kertymatruudulle() {
        System.out.print(sijaintix+" "+sijaintiy+" "+sijaintiz+" ");
    }
    
}
