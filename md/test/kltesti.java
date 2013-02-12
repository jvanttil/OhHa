import org.junit.*;
import static org.junit.Assert.*;
import md.kayttoliittyma.tekstikayttoliittyma;

/**
 * @author jvanttil
 */
public class kltesti {
    
    private tekstikayttoliittyma kl;
    
    public kltesti() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.kl = new tekstikayttoliittyma();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void laatikonalustus() {
        assertFalse(kl.asetalaatikonkoko("5.0"));
        assertFalse(kl.annalaatikonkoko()==5.0);
        assertFalse(kl.asetalaatikonkoko("150.0"));
        assertFalse(kl.annalaatikonkoko()==150.0);
        assertTrue(kl.asetalaatikonkoko("15.0"));
        assertTrue(kl.annalaatikonkoko()==15.0);
    }
    
    @Test
    public void molekyylimaaranalustus() {
        kl.asetalaatikonkoko("10.0");
        assertFalse(kl.asetamolekyylimaara("-6"));
        assertFalse(kl.annamolekyylimaara()==6);
        assertFalse(kl.asetamolekyylimaara("666"));
        assertFalse(kl.annamolekyylimaara()==666);
        assertTrue(kl.asetamolekyylimaara("8"));
        assertTrue(kl.annamolekyylimaara()==8);
    }
    
    @Test
    public void askelkoonalustus() {
        assertFalse(kl.asetaaskelkoko("6.0"));
        assertFalse(kl.annaaskelkoko()==6.0);
        assertFalse(kl.asetaaskelkoko("0.000009"));
        assertFalse(kl.annaaskelkoko()==0.000009);
        assertTrue(kl.asetaaskelkoko("0.5"));
        assertTrue(kl.annaaskelkoko()==0.5);
    }
    
    @Test
    public void askelmaaranalustus() {
        assertFalse(kl.asetaaskelmaara("2"));
        assertFalse(kl.annaaskelmaara()==2);
        assertFalse(kl.asetaaskelmaara("314154"));
        assertFalse(kl.annaaskelmaara()==314154);
        assertTrue(kl.asetaaskelmaara("100"));
        assertTrue(kl.annaaskelmaara()==100);
    }
}
