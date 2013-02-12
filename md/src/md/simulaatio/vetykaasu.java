package md.simulaatio;

import md.simulaatio.vety;
import md.simulaatio.sidos;
import md.simulaatio.molekyyli;

/**
 * vetykaasumolekyyliluokka
 * @author jvanttil
 */
public class vetykaasu implements molekyyli{
    
    private vety[] vedyt;
    private sidos s1;
    private double nopeus;
    private int atomilkm = 2;
    private double tasapaino = 1.47;
    private double koko;
    
    public vetykaasu() {
        vedyt = new vety[2];
        vedyt[0] = new vety(-0.735,0.0,0.0);
        vedyt[1] = new vety(0.735,0.0,0.0);
        s1 = new sidos(vedyt[0],vedyt[1],tasapaino);
        nopeus = 0.0;
        koko = 2.0;
    }
    
    public vetykaasu(double sx, double sy, double sz) {
        vedyt = new vety[2];
        vedyt[0] = new vety(sx-0.735,sy,sz);
        vedyt[1] = new vety(sx+0.735,sy,sz);
        s1 = new sidos(vedyt[0],vedyt[1],tasapaino);
        nopeus = 0.0;
        koko = 2.0;
    }
    
    public int annaatomilkm() {
        return atomilkm;
    }
    
    /**
     * laskee atomin kokonaisnopeuden ja myös palauttaa sen
     * 
     * @return atomin nopeus
     */
    public double annanopeus() {
        nopeus = (vedyt[0].annanopeusx()+vedyt[1].annanopeusx())/2.0+
                (vedyt[0].annanopeusy()+vedyt[1].annanopeusy())/2.0+
                (vedyt[0].annanopeusz()+vedyt[1].annanopeusz())/2.0;
        return Math.abs(nopeus);
    }
    
    public double annakertymasumma() {
        return Math.abs(vedyt[0].kertymax)+Math.abs(vedyt[0].kertymay)+Math.abs(vedyt[0].kertymaz)+
                Math.abs(vedyt[1].kertymax)+Math.abs(vedyt[1].kertymay)+Math.abs(vedyt[1].kertymaz);
    }
    
    public double annakoko() {
        return koko;
    }
    
    /**
     * kerryttää sidoksesta aiheutuvat voimat
     * 
     * kutsuu molekyylin sidosta laskemaan sidoksesta aiheutuvat voimat
     * ja kerryttämään nämä atomeille
     */
    public void sisaiset() {
        s1.asetavoima();
    }
    
    /**
     * liikuttaa atomeja senhetkisten kerrytettyjen voimien mukaisesti
     * 
     * liikuttaa kumpaakin molekyylin atomia kerrytettyjen voimien mukaisesti, 
     * laatikon koko tarvitaan atomien pitämiseksi laatikon sisällä ja aika-
     * askeleen koko tarvitaan liikkeen skaalaamiseksi
     * 
     * @param dt aika-askeleen koko
     * @param koko laatikon koko
     */
    public void liikuta(double dt,double koko) {
        vedyt[0].liikuta(dt,koko);
        vedyt[1].liikuta(dt,koko);
    }
    
    public void perturboi(double x1,double y1,double z1,double x2,double y2,double z2) {
        vedyt[0].kerryta(x1,y1,z1);
        vedyt[1].kerryta(x2,y2,z2);
    }
    
    public double annaetaisyys() {
        return s1.etaisyys;
    }
    
    /**
     * palauttaa viittauksen atomiin
     * 
     * käytetään ulkoisten voimien rekisterin muodostamista varten
     * 
     * @param nro tarvitaan indeksi 0 tai 1
     * @return viittaus atomiin
     */
    public atomi viittausatomiin(int nro) {
        return vedyt[nro];
    }
    
    public double annasijaintix() { return (vedyt[0].annax()+vedyt[1].annax())/2.0; }
    public double annasijaintiy() { return (vedyt[0].annay()+vedyt[1].annay())/2.0; }
    public double annasijaintiz() { return (vedyt[0].annaz()+vedyt[1].annaz())/2.0; }
    
    /**
     * palauttaa molekyylin atomien sijainnit taulukkona [x1,y1,z1,x2,y2,z2]
     * 
     * @return kuuden alkion taulukko atomien sijainneista [x1,y1,z1,x2,y2,z2]
     */
    public double[] annasijainnit() {
        double[] a = {vedyt[0].annax(),vedyt[0].annay(),vedyt[0].annaz(),vedyt[1].annax(),vedyt[1].annay(),vedyt[1].annaz()};
        return a;
    }
    
}
