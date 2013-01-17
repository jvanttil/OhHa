package md;

/**
 * @author jvanttil
 */
public class vetykaasu implements molekyyli{
    
    private vety v1;
    private vety v2;
    private double koko;
    
    public vetykaasu() {
        v1 = new vety(-0.735,0.0,0.0);
        v2 = new vety(0.735,0.0,0.0);
        koko = 2;
    }
    
    public vetykaasu(double sx, double sy, double sz) {
        v1 = new vety(sx-0.735,sy,sz);
        v2 = new vety(sx+0.735,sy,sz);
        koko = 2;
    }
    
    public double annamomentti() {
        return 0.0;
    }
    
}
