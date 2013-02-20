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
    private double massa = 2.0;
    
    /*
    public vetykaasu() {
        vedyt = new vety[2];
        vedyt[0] = new vety(-0.735,0.0,0.0);
        vedyt[1] = new vety(0.735,0.0,0.0);
        s1 = new sidos(vedyt[0],vedyt[1],tasapaino);
        nopeus = 0.0;
        koko = 2.0;
    }
    */
    
    /**
     * kenstruktori tekee vetymolekyylin haluttuun sijaintiin ja 
     * asettaa vetyatomit tasapainoetäisyyden päähän toisistaan
     * 
     * @param sx x-koordinaatti +- 0.735 (tasapaino)
     * @param sy y-koordinaatti
     * @param sz z-koordinaatti
     */
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
     * laskee molekyylin nopeuden atomien nopeuksien keskiarvona.
     */
    public void laskenopeus() {
        this.nopeus = (vedyt[0].annanopeusx()+vedyt[1].annanopeusx())/2.0+
                    (vedyt[0].annanopeusy()+vedyt[1].annanopeusy())/2.0+
                    (vedyt[0].annanopeusz()+vedyt[1].annanopeusz())/2.0;
    }
    
    /**
     * palauttaa molekyylin nopeuden
     * huom nopeus pitää ensin laskea metodilla laskenopeus()
     * 
     * @return nopeus
     */
    public double annanopeus() { return Math.abs(nopeus); }
    
    /**
     * laskee molekyylin liike-energian kaavalla (1/2)*m*v^2
     * 
     * @return molekyylin liike-energia
     */
    public double annaliikeenergia( int nro ) {
        double n = Math.sqrt((vedyt[nro].nopeusx*vedyt[nro].nopeusx)+(vedyt[nro].nopeusy*vedyt[nro].nopeusy)+(vedyt[nro].nopeusz*vedyt[nro].nopeusz));
        return 0.5*this.massa*n*n;
    }
    
    /**
     * palauttaa molekyyliin kohdistuvien voimien kertymän
     * 
     * voimat pitää kerryttää ensin
     * 
     * käytetään potentiaalienergian laskemiseen
     * 
     * @return molekyyliin tällä hetkellä kerrytetyt voimavaikutukset
     */
    public double annakertymasumma( int nro ) {
        return Math.sqrt((vedyt[nro].kertymax*vedyt[nro].kertymax)+
                (vedyt[nro].kertymay*vedyt[nro].kertymay)+
                (vedyt[nro].kertymaz*vedyt[nro].kertymaz));
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
     * @param laatikonkoko laatikon koko
     */
    public void liikuta(double dt,double laatikonkoko) {
        vedyt[0].liikuta(dt,laatikonkoko);
        vedyt[1].liikuta(dt,laatikonkoko);
    }
    
    /**
     * kohdistaa molekyyliin voiman satunnaiseen suuntaan
     * laatikko kutsuu tätä metodia
     * tekee simulaatio-ajoista satunnaisia
     * 
     * @param x1 voima x-suunnassa
     * @param y1 voima y-suunnassa
     * @param z1 voima z-suunnassa
     */
    public void perturboi(double x1,double y1,double z1) {
        vedyt[0].kerryta(x1,y1,z1);
        vedyt[1].kerryta(x1,y1,z1);
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
    
    public double annasijaintix( int nro ) { return vedyt[nro].annax(); }
    public double annasijaintiy( int nro ) { return vedyt[nro].annay(); }
    public double annasijaintiz( int nro ) { return vedyt[nro].annaz(); }
    
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
