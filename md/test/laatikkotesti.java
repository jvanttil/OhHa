import md.simulaatio.laatikko;
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
        koelaatikko.generoi(10.0,2);
        koelaatikko.simuloi(0.01,1,1);
        assertTrue(koelaatikko.nopeussumma() > 0.0);
    }
    
    @Test
    public void ulkoinenvoimaeitoimisisaisesti() {
        koelaatikko.generoi(10.0,1);
        koelaatikko.simuloi(0.01,10,1);
        assertTrue(koelaatikko.nopeussumma() == 0.0);
    }
    
}
