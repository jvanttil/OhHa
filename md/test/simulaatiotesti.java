/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import md.simulaatio.vetykaasu;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author jvanttil
 */
public class simulaatiotesti {
    
    private vetykaasu vk;
    
    public simulaatiotesti() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.vk = new vetykaasu(2.0,2.0,2.0);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tasapainopysyy() {
        this.vk.sisaiset();
        assertTrue(vk.annakertymasumma()<0.00000000000001);
    }
    
    @Test
    public void voimakertyy() {
        assertTrue(this.vk.annakertymasumma()==0.0);
        this.vk.perturboi(0.01,0.003,-0.03);
        assertFalse(this.vk.annakertymasumma()==0.0);
    }
    
    @Test 
    public void kertymanollautuu() {
        double askel = 0.01;
        double koko = 10.0;
        this.vk.perturboi(0.01,0.003,-0.03);
        this.vk.sisaiset();
        this.vk.liikuta(askel,koko);
        assertTrue(this.vk.annakertymasumma()==0.0);
    }
    
    @Test
    public void kertymakasautuu() {
        double lisays = 0.1;
        this.vk.perturboi(lisays,lisays,lisays);
        this.vk.perturboi(lisays,lisays,lisays);
        assertTrue(Math.abs(this.vk.annakertymasumma()-lisays*12)<0.0000000000001);
    }
    
    @Test
    public void nopeussailyy() {
        double askel = 0.01;
        double koko = 10.0;
        double nopeustalteen;
        this.vk.perturboi(0.02,-0.03,-0.03);
        this.vk.sisaiset();
        this.vk.liikuta(askel,koko);
        nopeustalteen = this.vk.annanopeus();
        this.vk.liikuta(askel,koko);
        assertTrue(this.vk.annanopeus() == nopeustalteen);
    }
    
    @Test
    public void liikkuukoatomit() {
        double askel = 0.01;
        double koko = 10.0;
        this.vk.perturboi(0.01,0.003,-0.03);
        this.vk.sisaiset();
        this.vk.liikuta(askel,koko);
        assertTrue(vk.annanopeus()!=0.0);
    }
    
    @Test
    public void pysyylaatikossa() {
        double koko = 4.0;
        double askel = 0.5;
        this.vk.perturboi(0.02,0.03,-0.08);
        for( int i = 0; i < 300; i++ ) {
            this.vk.sisaiset();
            this.vk.liikuta(askel,koko);
        }
        assertTrue((vk.annasijaintix(0) < koko)&&(vk.annasijaintix(0) > 0.0));
        assertTrue((vk.annasijaintiy(0) < koko)&&(vk.annasijaintiy(0) > 0.0));
        assertTrue((vk.annasijaintiz(0) < koko)&&(vk.annasijaintiz(0) > 0.0));
    }
    
    @Test
    public void etaisyysainaallekolme() {
        double koko = 10.0;
        double askel = 0.5;
        double maksimietaisyys = 1.47;
        this.vk.perturboi(-0.03,0.03,-0.08);
        for( int i = 0; i < 300; i++ ) {
            this.vk.sisaiset();
            this.vk.liikuta(askel,koko);
            if( this.vk.annaetaisyys() > maksimietaisyys ) {
                maksimietaisyys = this.vk.annaetaisyys();
            }
        }
        assertTrue(maksimietaisyys < 3.0);
    }
    
    @Test
    public void etaisyysainaylipuoli() {
        double koko = 10.0;
        double askel = 0.5;
        double minimietaisyys = 1.47;
        this.vk.perturboi(-0.03,0.03,-0.08);
        for( int i = 0; i < 300; i++ ) {
            this.vk.sisaiset();
            this.vk.liikuta(askel,koko);
            if( this.vk.annaetaisyys() < minimietaisyys ) {
                minimietaisyys = this.vk.annaetaisyys();
            }
        }
        assertTrue(minimietaisyys > 0.5);
    }
    
    @Test
    public void keskietaisyysontasapaino() {
        double koko = 10.0;
        double askel = 0.5;
        double askelmaara = 600;
        double kumulatiivinenetaisyys = 0.0;
        double keskietaisyys;
        this.vk.perturboi(-0.03,0.03,-0.084);
        for( int i = 0; i < askelmaara; i++ ) {
            this.vk.sisaiset();
            this.vk.liikuta(askel,koko);
            kumulatiivinenetaisyys += this.vk.annaetaisyys();
        }
        keskietaisyys = kumulatiivinenetaisyys/askelmaara;
        assertTrue(Math.abs(keskietaisyys-1.47) < 0.1);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
