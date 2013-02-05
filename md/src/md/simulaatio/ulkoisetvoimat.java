package md.simulaatio;

/**
 * molekyylienvälisten voimien rekisteri
 * asettaa atomeihin molekyylin ulkopuolelta tulevat voimat
 * @author jvanttil
 */
public class ulkoisetvoimat {
    
    private int atomilkm;
    private int tayttoaste;
    private double etaisyysx;
    private double etaisyysy;
    private double etaisyysz;
    protected double etaisyys;
    private double voimax;
    private double voimay;
    private double voimaz;
    private int[] molekyylissa;
    private atomi[] atomit;
    
    public ulkoisetvoimat(int lkm) {
        atomilkm = lkm;
        tayttoaste = 0;
        molekyylissa = new int[lkm];
        atomit = new atomi[lkm];
    }
    
    /**
     * täydentää voimien rekisteriä atomilla
     * 
     * @param a1 viittaus atomiin
     * @param indeksi sijainti taulukossa
     */
    public void laitaatomirekisteriin(atomi a1, int indeksi) {
        if( tayttoaste < atomilkm ) {
            atomit[tayttoaste] = a1;
            molekyylissa[tayttoaste] = indeksi;
            tayttoaste += 1;
        } else {
            System.out.println("ulkoisten voimien rekisteri taynna");
        }
    }
    
    /**
     * laskee atomien etäisyydet koordinaattien suhteen ja yhdessä
     * 
     * @param i atomi 1
     * @param j atomi 2
     */
    public void laskeetaisyydet(int i, int j) {
        etaisyysx = atomit[i].annax() - atomit[j].annax();
        etaisyysy = atomit[i].annay() - atomit[j].annay();
        etaisyysz = atomit[i].annaz() - atomit[j].annaz();
        etaisyys = Math.sqrt(etaisyysx*etaisyysx + etaisyysy*etaisyysy + etaisyysz*etaisyysz);
    }
    
    /**
     * laskee ulkoisen voiman ja kerryttää sen jokaiselle atomille rekisterissä
     * hyppää yli saman molekyylin atomit
     * käyttää ulkoisena voimana Lennard-Jones -potentiaalia
     */
    public void ulkoiset() {
        for( int i = 0; i < atomilkm; i++ ) {
            for( int j = 0; j < atomilkm; j++ ) {
                if( (i != j) && (molekyylissa[i] != molekyylissa[j]) ) {
                    laskeetaisyydet(i,j);
                    voimax = (etaisyysx / etaisyys)*(1/Math.pow(etaisyys,12)-0.5/Math.pow(etaisyys,6));
                    voimay = (etaisyysy / etaisyys)*(1/Math.pow(etaisyys,12)-0.5/Math.pow(etaisyys,6));
                    voimaz = (etaisyysz / etaisyys)*(1/Math.pow(etaisyys,12)-0.5/Math.pow(etaisyys,6));
                    atomit[i].kerryta(-1.0*voimax,-1.0*voimay,-1.0*voimaz);
                    atomit[j].kerryta(voimax,voimay,voimaz);
                }
            }
        }
    }
    
}
