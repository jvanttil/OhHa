package md.simulaatio;

/**
 * @author jvanttil
 */
public interface molekyyli {
    
    int annaatomilkm();
    
    double annakoko();
    
    double annasijaintix();
    
    double annasijaintiy();
    
    double annasijaintiz();
    
    void sisaiset();
    
    void liikuta(double dt,double koko);
    
    void ruudulle();
    
    atomi viittausatomiin(int nro);
    
    void perturboi(double x1,double y1,double z1,double x2,double y2,double z2);
    
    String annasijainnit();
    
}
