import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {
    @Test
    public void isSameNumberTest() {
        Integer a = 128;
        Integer b = 128;
        assertTrue(!Flik.isSameNumber(a, b));
    }
}
