package md;

/**
 *
 * @author jvanttil
 */
public class vety implements atomi{
    
    private double massa;
    private double sijaintix;
    private double sijaintiy;
    private double sijaintiz;
    private double kertymax;
    private double kertymay;
    private double kertymaz;
    
    public vety() {
        massa = 1.0;
        sijaintix = 0.0;
        sijaintiy = 0.0;
        sijaintiz = 0.0;
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
    
    public vety(double sx, double sy, double sz) {
        massa = 1.0;
        sijaintix = sx;
        sijaintiy = sy;
        sijaintiz = sz;
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
    
    public double getx() { return sijaintix; }
    public double gety() { return sijaintiy; }
    public double getz() { return sijaintiz; }
    
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
