package md.simulaatio;

import md.simulaatio.atomi;

/**
 * sidos tulee kaasumolekyylissä atomien väliin
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
    protected double etaisyys;
    private double voimax;
    private double voimay;
    private double voimaz;
    
    /**
     * konstruktori sidokselle laittaa sidoksen kahden atomin väliin
     * 
     * @param s1 atomi 1
     * @param s2 atomi 2
     * @param tsp tasapainoetäisyys
     */
    public sidos(atomi s1, atomi s2, double tsp) {
        a1 = s1;
        a2 = s2;
        tasapaino = tsp;
    }
    
    /**
     * laskee sidoksen osapuolten etäisyydet koordinaattien suhteen
     */
    private void laskeetaisyydet() {
        etaisyysx = a1.annax() - a2.annax();
        etaisyysy = a1.annay() - a2.annay();
        etaisyysz = a1.annaz() - a2.annaz();
        etaisyys = Math.sqrt(etaisyysx*etaisyysx + etaisyysy*etaisyysy + etaisyysz*etaisyysz);
    }
    
    /**
     * kerryttää atomeille sidoksesta aiheutuvan voiman
     */
    public void asetavoima() {
        laskeetaisyydet();
        voimax = 0.5*(etaisyysx / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        voimay = 0.5*(etaisyysy / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        voimaz = 0.5*(etaisyysz / etaisyys)*jousivakio*(etaisyys-tasapaino)*(etaisyys-tasapaino);
        if( etaisyys > tasapaino ) {
            a1.kerryta(-1.0*voimax,-1.0*voimay,-1.0*voimaz);
            a2.kerryta(voimax,voimay,voimaz);
        } else {
            a1.kerryta(voimax,voimay,voimaz);
            a2.kerryta(-1.0*voimax,-1.0*voimay,-1.0*voimaz);
        }
    }
    
}
