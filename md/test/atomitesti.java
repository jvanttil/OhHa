import org.junit.*;
import md.simulaatio.atomi;
import md.simulaatio.vety;
import static org.junit.Assert.*;

/**
 * @author jvanttil
 */
public class atomitesti {
    
    public atomitesti() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void atomiliikkuu() {
        double kohta = 5.0;
        vety a1 = new vety(5.0,5.0,5.0);
        for( int i = 0; i < 15; i++ ) {
            a1.kerryta(1.0,0.0,0.0);
            a1.liikuta(0.1,20.0);
            assertTrue(a1.annax() > kohta);
            assertTrue(a1.annanopeusx() > 0.0);
            assertTrue(a1.annanopeusy() == 0.0);
            assertTrue(a1.annanopeusz() == 0.0);
            kohta = a1.annax();
        }
    }
    
    public void kimpoaaseinasta() {
        vety a1 = new vety(9.5,5.0,5.0);
        a1.kerryta(1.0,0.0,0.0);
        a1.liikuta(1.0,10.0);
        assertTrue(a1.annanopeusx() < 0.0);
        assertTrue(a1.annax() < 10.0);
        assertTrue(a1.annay() == 5.0);
        assertTrue(a1.annaz() == 5.0);
    }
}
