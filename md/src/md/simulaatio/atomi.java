package md.simulaatio;

/**
 * @author jvanttil
 */
public class atomi {
    
    protected double massa;
    protected double sijaintix;
    protected double sijaintiy;
    protected double sijaintiz;
    protected double nopeusx;
    protected double nopeusy;
    protected double nopeusz;
    protected double kertymax;
    protected double kertymay;
    protected double kertymaz;
    private double uusisijaintix;
    private double uusisijaintiy;
    private double uusisijaintiz;
    private double uusinopeusx;
    private double uusinopeusy;
    private double uusinopeusz;
    
    public double annax() { return sijaintix; }
    public double annay() { return sijaintiy; }
    public double annaz() { return sijaintiz; }
    public double annanopeusx() { return nopeusx; }
    public double annanopeusy() { return nopeusy; }
    public double annanopeusz() { return nopeusz; }
    
    /**
     * lisää kertymiin voimia eri koordinaattien mukaisesti
     * 
     * tallettaa atomiin vaikuttavat voimat x, y, ja z koordinaattien
     * suuntaan, eri lähteistä tulevat voimat vaikuttavat additiivisesti
     * 
     * @param sx tallettaa x-koordinaatin suuntaisen voiman
     * @param sy tallettaa y-koordinaatin suuntaisen voiman
     * @param sz tallettaa z-koordinaatin suuntaisen voiman
     */
    public void kerryta( double sx, double sy, double sz ) {
        kertymax += sx;
        kertymay += sy;
        kertymaz += sz;
    }
    
    /**
     * asettaa kerrytetyt voimat uudelleen nolliksi
     */
    private void nollaavoimat() {
        kertymax = 0.0;
        kertymay = 0.0;
        kertymaz = 0.0;
    }
    
    /**
     * laskee uudet sijainnit ja nopeudet
     * 
     * laskee atomille uuden sijainnin ja nopeuden kaikissa suunnissa 
     * perustuen vanhaan sijaintiin, nopeuteen, massaan, ja atomiin 
     * kohdistuviin voimiin, laskentatapa on nimeltään velocity-verlet,
     * muutoksia skaalataan aika-askeleen pituudella
     * 
     * @param dt aika-askeleen pituus
     */
    private void laskeuudet(double dt) {
        uusisijaintix = sijaintix + nopeusx*dt + (kertymax/(2.0*massa))*dt*dt;
        uusisijaintiy = sijaintiy + nopeusy*dt + (kertymay/(2.0*massa))*dt*dt;
        uusisijaintiz = sijaintiz + nopeusz*dt + (kertymaz/(2.0*massa))*dt*dt;
        uusinopeusx = nopeusx + (kertymax/(2.0*massa))*dt;
        uusinopeusy = nopeusy + (kertymay/(2.0*massa))*dt;
        uusinopeusz = nopeusz + (kertymaz/(2.0*massa))*dt;
    }
    
    /**
     * pitää atomit laatikon sisällä
     * 
     * tarkistaa ovatko uudet sijainnit yhä laatikon sisällä ja kimmottaa 
     * atomin laatikon reunoista, jos näin ei ole
     * 
     * @param koko laatikon koko
     */
    private void tarkistareunat(double koko) {
        if( uusisijaintix > koko ) {
            uusisijaintix = 2.0*koko - uusisijaintix;
            uusinopeusx = -1.0*uusinopeusx;
        }
        if( uusisijaintiy > koko ) {
            uusisijaintiy = 2.0*koko - uusisijaintiy;
            uusinopeusy = -1.0*uusinopeusy;
        }
        if( uusisijaintiz > koko ) {
            uusisijaintiz = 2.0*koko - uusisijaintiz;
            uusinopeusz = -1.0*uusinopeusz;
        }
        if( uusisijaintix < 0 ) {
            uusisijaintix = -1.0*uusisijaintix;
            uusinopeusx = -1.0*uusinopeusx;
        }
        if( uusisijaintiy < 0 ) {
            uusisijaintiy = -1.0*uusisijaintiy;
            uusinopeusy = -1.0*uusinopeusy;
        }
        if( uusisijaintiz < 0 ) {
            uusisijaintiz = -1.0*uusisijaintiz;
            uusinopeusz = -1.0*uusinopeusz;
        }
    }
    
    /**
     * liikuttaa atomia
     * 
     * pyytää uudet sijainnit ja nopeudet ja tarkistaa, että atomit 
     * pysyvät laatikossa, toteuttaa atomien siirron, nollaa atomeihin 
     * vaikuttavat voimat seuraavan askeleen laskemista varten
     * 
     * @param dt aika-askeleen pituus
     * @param koko laatikon koko
     */
    public void liikuta(double dt,double koko) {
        laskeuudet(dt);
        tarkistareunat(koko);
        sijaintix = uusisijaintix;
        sijaintiy = uusisijaintiy;
        sijaintiz = uusisijaintiz;
        nopeusx = uusinopeusx;
        nopeusy = uusinopeusy;
        nopeusz = uusinopeusz;
        nollaavoimat();
    }
    
}
