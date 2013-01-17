package md;

/**
 * @author jvanttil
 */
public class atomi {
    
    protected double massa;
    protected double sijaintix;
    protected double sijaintiy;
    protected double sijaintiz;
    protected double kertymax;
    protected double kertymay;
    protected double kertymaz;
    
    //public atomi() {
    //    massa = 1.0;
    //    sijaintix = 0.0;
    //    sijaintiy = 0.0;
    //    sijaintiz = 0.0;
    //    kertymax = 0.0;
    //    kertymay = 0.0;
    //    kertymaz = 0.0;
    //}
    
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
    
    public void liikuta() {
        System.out.print("liikkuu");
    }
    
}
