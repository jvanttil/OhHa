import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
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
    public void alustustesti() {
        String syote1 = "10.0/r/n";
        InputStream stdin = System.in;
        //kl.alusta();
        try {
            kl.alusta();
            System.setIn(new ByteArrayInputStream(syote1.getBytes()));
            Scanner scanner = new Scanner(System.in);
            System.out.println(scanner.nextLine());
        } finally {
            System.setIn(stdin);
        }
    }
}
