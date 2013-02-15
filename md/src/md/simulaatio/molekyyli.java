package md.simulaatio;

/**
 * yhteinen rajapinta molekyyleille
 * @author jvanttil
 */
public interface molekyyli {
    
    int annaatomilkm();
    
    double annakoko();
    
    double annasijaintix( int nro );
    
    double annasijaintiy( int nro );
    
    double annasijaintiz( int nro );
    
    double annanopeus();
    
    double annaliikeenergia();
    
    void sisaiset();
    
    void liikuta(double dt,double laatikonkoko);
    
    atomi viittausatomiin(int nro);
    
    void perturboi(double x1,double y1,double z1);
    
    double[] annasijainnit();
    
}
