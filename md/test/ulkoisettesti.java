import org.junit.*;
import static org.junit.Assert.*;
import md.simulaatio.atomi;
import md.simulaatio.vety;
import md.simulaatio.vetykaasu;
import md.simulaatio.sidos;
import md.simulaatio.ulkoisetvoimat;
        
/**
 * @author jvanttil
 */
public class ulkoisettesti {
    
    public ulkoisettesti() {
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
    public void voimatvaikuttaa() {
        vetykaasu vk1 = new vetykaasu(2.0,5.0,5.0);
        vetykaasu vk2 = new vetykaasu(8.0,5.0,5.0);
        ulkoisetvoimat voimarekisteri = new ulkoisetvoimat(4);
        voimarekisteri.laitaatomirekisteriin(vk1.viittausatomiin(0),0);
        voimarekisteri.laitaatomirekisteriin(vk1.viittausatomiin(1),0);
        voimarekisteri.laitaatomirekisteriin(vk2.viittausatomiin(0),1);
        voimarekisteri.laitaatomirekisteriin(vk2.viittausatomiin(1),1);
        assertTrue(vk1.annakertymasumma(0)==0.0);
        assertTrue(vk1.annakertymasumma(1)==0.0);
        assertTrue(vk2.annakertymasumma(0)==0.0);
        assertTrue(vk2.annakertymasumma(1)==0.0);
        voimarekisteri.ulkoiset();
        assertTrue(vk1.annakertymasumma(0)>0.0);
        assertTrue(vk1.annakertymasumma(1)>0.0);
        assertTrue(vk2.annakertymasumma(0)>0.0);
        assertTrue(vk2.annakertymasumma(1)>0.0);
    }
    
    @Test
    public void voimateivaikutasamassamolekyylissa() {
        vetykaasu vk1 = new vetykaasu(2.0,5.0,5.0);
        ulkoisetvoimat voimarekisteri = new ulkoisetvoimat(2);
        voimarekisteri.laitaatomirekisteriin(vk1.viittausatomiin(0),0);
        voimarekisteri.laitaatomirekisteriin(vk1.viittausatomiin(1),0);
        assertTrue(vk1.annakertymasumma(0)==0.0);
        assertTrue(vk1.annakertymasumma(1)==0.0);
        voimarekisteri.ulkoiset();
        assertTrue(vk1.annakertymasumma(0)==0.0);
        assertTrue(vk1.annakertymasumma(1)==0.0);
    }
}
