package md;

/**
 * @author jvanttil
 */
public class vetykaasu implements molekyyli{
    
    private vety v1;
    private vety v2;
    private sidos s1;
    private double tasapaino = 1.47;
    private double koko;
    
    public vetykaasu() {
        v1 = new vety(-0.735,0.3,-0.1);
        v2 = new vety(0.735,-0.2,0.2);
        s1 = new sidos(v1,v2,tasapaino);
        koko = 2.0;
    }
    
    public vetykaasu(double sx, double sy, double sz) {
        v1 = new vety(sx-0.735,sy,sz);
        v2 = new vety(sx+0.735,sy,sz);
        s1 = new sidos(v1,v2,tasapaino);
        koko = 2.0;
    }
    
    public double annamomentti() {
        return 0.0;
    }
    
    public double annakoko() {
        return koko;
    }
    
    public void sisaiset() {
        s1.asetavoima();
    }
    
    public void liikuta(double dt) {
        v1.liikuta(dt);
        v2.liikuta(dt);
    }
    
    public void ruudulle() {
        v1.kertymatruudulle();
        v2.kertymatruudulle();
        System.out.println("");
    }
    
    public String annasijainnit() {
        String sijainnit = (v1.annax()+" "+v1.annay()+" "+v1.annaz()+" "+v2.annax()+" "+v2.annay()+" "+v2.annaz()+" ");
        return sijainnit;
    }
    
}
