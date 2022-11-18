import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineTest {
    @Test
    public void pointSubtractionSetV() {

        var testline = new Line("L1", "TESTLINE", new Point("C1", 4, 2, 1),
                new Point("C2", 2, 1, 1));

        assertEquals(testline.getV().getCoordinateX() , -2, 0  );
    }

    @Test
    public void pointDivisonSetUtest() {

        Line ln = new Line();
        ln.setId("C3");    // be setID nevekia nes nes setU jis nenustato id jis lieka null
        ln.setU(new Point("C3", 6, 4, 2), 2);

        assertEquals(ln.getU().getCoordinateY(), 2, 0);
    }

    @Test
    public void pointSquareRootSetLength() {

        Line ln = new Line();
        ln.setLength(new Point("C4", 1, 2, 2));  // 1 + 4 + 4 = 9 = 3^2

        assertEquals(ln.getLength(), 3, 0);
    }
    @Test
    public void setSetbackCoordinatesTest() {

        var testline = new Line("L1", "TESTLINE", new Point("C1", 4, 2, 1),
                new Point("C2", 2, 1, 1));

        testline.setSetbackCoordinates(new Point("C4", 3, 2, 0));

        assertEquals(testline.getSetbackCoordinates().getCoordinateY(), 8, 0);
    }
}