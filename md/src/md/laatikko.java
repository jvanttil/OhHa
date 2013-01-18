package md;

/**
 * @author jvanttil
 */
public class laatikko {
    
    private double kokox;
    private double kokoy;
    private double kokoz;
    private short molekyylilkm;
    private molekyyli[] molekyylilista;
    
    public laatikko() {
        kokox = 10;
        kokoy = 10;
        kokoz = 10;
        molekyylilkm = 1;
        molekyylilista = new molekyyli[molekyylilkm];
    }
    
    public void generoi() {
        molekyylilista[0] = new vetykaasu();
    }
    
    public void simuloi(double dt, int askelia) {
        for( int i = 0; i < askelia; i++ ) {
            molekyylilista[0].sisaiset();
            molekyylilista[0].liikuta(dt);
            molekyylilista[0].ruudulle();
        }
    }
    
}
