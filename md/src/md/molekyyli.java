package md;

/**
 * @author jvanttil
 */
public interface molekyyli {
    
    double annamomentti();
    
    double annakoko();
    
    void sisaiset();
    
    void liikuta(double dt,double koko);
    
    void ruudulle();
    
    void perturboi(double x1,double y1,double z1,double x2,double y2,double z2);
    
    String annasijainnit();
    
}
