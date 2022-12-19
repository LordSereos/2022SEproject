import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoplanarTest {
    @Test
    public void sameplane() {
        var Cop = new Coplanar();

        assertTrue(Cop.equation_plane(3, 2, -5, -1, 4, -3, -3, 8, -5, -3, 2, 1));
    }
    @Test
    public void notsameplane() {
        var Cop = new Coplanar();

        assertFalse(Cop.equation_plane(0, -1, -1, 4, 5, 1, 3, 9, 4, -4, 4, 3));
    }
}