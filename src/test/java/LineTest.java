import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void pointSubtraction() {
        var tl = new Line();

        var testline = new Line("L1", "TESTLINE", new Point("C1", 4, 2, 1),
                new Point("C2", 2, 1, 1));

        assertEquals(testline.getV().getCoordinateX() , -2, 0  );
        //System.out.println(tl.getV().toString());
    }
}