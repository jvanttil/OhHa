import md.simulaatio.laatikko;
import md.aineistokasittely.aineistokasittelija;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author jvanttil
 */
public class laatikkotesti {
    
    private laatikko koelaatikko;
    
    public laatikkotesti() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.koelaatikko = new laatikko();
    }
    
    @After
    public void tearDown() {
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:

    @Test
    public void pitaisimahtua() {
        assertTrue(koelaatikko.mahtuuko(10.0,26));
    }
    
    @Test
    public void eimahdu() {
        assertFalse(koelaatikko.mahtuuko(10.0,27));
    }
    
    @Test
    public void ulkoinenvoimatoimiiheti() {
        double laatikonkoko = 10.0;
        int molekyylilkm = 2;
        double askelkoko = 0.01;
        int askellkm = 1;
        int resoluutio = 1;
        aineistokasittelija aineisto = new aineistokasittelija(laatikonkoko,molekyylilkm,askelkoko,askellkm,resoluutio);
        koelaatikko.generoi(laatikonkoko,molekyylilkm);
        koelaatikko.simuloi(askelkoko,askellkm,resoluutio);
        assertTrue(koelaatikko.nopeussumma() > 0.0);
    }
    
    @Test
    public void ulkoinenvoimaeitoimisisaisesti() {
        double laatikonkoko = 10.0;
        int molekyylilkm = 1;
        double askelkoko = 0.01;
        int askellkm = 10;
        int resoluutio = 1;
        aineistokasittelija aineisto = new aineistokasittelija(laatikonkoko,molekyylilkm,askelkoko,askellkm,resoluutio);
        koelaatikko.generoi(laatikonkoko,molekyylilkm);
        koelaatikko.simuloi(askelkoko,askellkm,resoluutio);
        assertTrue(koelaatikko.nopeussumma() == 0.0);
    }
    
}
