package md;

/**
 * @author jvanttil
 */
public class sidos {
    
    private double jousivakio = 0.6;
    private double tasapaino;
    private atomi a1;
    private atomi a2;
    private double etaisyysx;
    private double etaisyysy;
    private double etaisyysz;
    private double etaisyys;
    private double voimax;
    private double voimay;
    private double voimaz;
    
    public sidos(atomi s1, atomi s2, double tsp) {
        a1 = s1;
        a2 = s2;
        tasapaino = tsp;
    }
    
    public void asetavoima() {
        etaisyysx = a1.annax() - a2.annax();
        etaisyysy = a1.annay() - a2.annay();
        etaisyysz = a1.annaz() - a2.annaz();
        etaisyys = Math.sqrt(etaisyysx*etaisyysx + etaisyysy*etaisyysy + etaisyysz*etaisyysz);
        voimax = (etaisyysx / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        voimay = (etaisyysy / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        voimaz = (etaisyysz / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        if( etaisyys > tasapaino ) {
            a1.kerryta(-1.0*voimax,-1.0*voimay,-1.0*voimaz);
            a2.kerryta(voimax,voimay,voimaz);
        } else {
            a1.kerryta(voimax,voimay,voimaz);
            a2.kerryta(-1.0*voimax,-1.0*voimay,-1.0*voimaz);
        }
        
    }
    
}
