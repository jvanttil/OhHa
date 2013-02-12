import md.aineistokasittely.aineistokasittelija;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author jvanttil
 */
public class aineistotesti {
    
    private aineistokasittelija aineisto;
    
    public aineistotesti() {
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

    @Test
    public void taulukonrivittoimii() {
        double syote[] = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
        aineisto = new aineistokasittelija( 10.0, 1, 0.1, 1, 1 );
        aineistokasittelija.laitaaikadataan( 0, 0.0 );
        aineistokasittelija.laitapaikkadataan( 0, 0, syote );
        double summa = 0.0;
        for( int i = 0; i < aineisto.annasarakkeet(); i++ ) {
            summa += aineistokasittelija.haetaulukosta( 0, i );
        }
        assertTrue( summa == 21.0 );
    }
    
    @Test
    public void taulukonsarakkeettoimii() {
        double syote1[] = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
        double syote2[] = { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
        aineisto = new aineistokasittelija( 10.0, 1, 0.1, 2, 1 );
        aineistokasittelija.laitaaikadataan( 0, 0.0 );
        aineistokasittelija.laitaaikadataan( 1, 0.1 );
        aineistokasittelija.laitapaikkadataan( 0, 0, syote1 );
        aineistokasittelija.laitapaikkadataan( 1, 0, syote2 );
        double summa = 0.0;
        for( int i = 1; i < aineisto.annasarakkeet(); i++ ) {
            for( int j = 0; j < aineisto.annarivit(); j++ ) {
                summa += aineistokasittelija.haetaulukosta( j, i );
            }
        }
        assertTrue( summa == 12.0 );
    }
}
