package md;

/**
 * @author jvanttil
 */
public class vetykaasu implements molekyyli{
    
    private vety v1;
    private vety v2;
    private sidos s1;
    private double nopeus;
    private double tasapaino = 1.47;
    private double koko;
    
    public vetykaasu() {
        v1 = new vety(-0.735,0.0,0.0);
        v2 = new vety(0.735,0.0,0.0);
        s1 = new sidos(v1,v2,tasapaino);
        nopeus = 0.0;
        koko = 2.0;
    }
    
    public vetykaasu(double sx, double sy, double sz) {
        v1 = new vety(sx-0.735,sy,sz);
        v2 = new vety(sx+0.735,sy,sz);
        s1 = new sidos(v1,v2,tasapaino);
        nopeus = 0.0;
        koko = 2.0;
    }
    
    public double annanopeus() {
        nopeus = v1.annanopeusx()+v1.annanopeusy()+v1.annanopeusz()+v2.annanopeusx()+v2.annanopeusy()+v2.annanopeusz();
        return nopeus;
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
    
    public void liikuta(double dt,double koko) {
        v1.liikuta(dt,koko);
        v2.liikuta(dt,koko);
    }
    
    public void perturboi(double x1,double y1,double z1,double x2,double y2,double z2) {
        v1.lisaanopeus(x1,y1,z1);
        v2.lisaanopeus(x2,y2,z2);
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
