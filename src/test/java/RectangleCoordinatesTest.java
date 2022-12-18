import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleCoordinatesTest {
    @BeforeAll
    public static void initializeDataFromJson() {
        var data = new ReadFromJSON();
        //specify data source
        data.readPoints("./JSON_files/Data.json");
    }

    @Test
    void setOfBorderCoordinatesAreCorrect1() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C6", "C5");

        assertEquals(-11.711544610066909, borderCoordinateSet.borderCoordinateX());
        assertEquals(9.407439692542585, borderCoordinateSet.borderCoordinateY());
        assertEquals(9.84, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect2() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C5", "C2");

        assertEquals(-3.738642273086798, borderCoordinateSet.borderCoordinateX());
        assertEquals(38.485269768618394, borderCoordinateSet.borderCoordinateY());
        assertEquals(16.44502560505658, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect3() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C2", "C1");

        assertEquals(3.7169447700591864, borderCoordinateSet.borderCoordinateX());
        assertEquals(29.61204382501479, borderCoordinateSet.borderCoordinateY());
        assertEquals(29.53795144, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect4() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C1", "C6");

        assertEquals(-4.255957566920924, borderCoordinateSet.borderCoordinateX());
        assertEquals(0.5342137489389845, borderCoordinateSet.borderCoordinateY());
        assertEquals(22.932925834943422, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect5() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C1", "C2");

        assertEquals(0.9808581899659602, borderCoordinateSet.borderCoordinateX());
        assertEquals(7.814271533266338, borderCoordinateSet.borderCoordinateY());
        assertEquals(29.53795144, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect6() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C2", "C3");

        assertEquals(8.95376052694607, borderCoordinateSet.borderCoordinateX());
        assertEquals(36.89210160934215, borderCoordinateSet.borderCoordinateY());
        assertEquals(22.932925834943422, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect7() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C3", "C4");

        assertEquals(16.40934757017087, borderCoordinateSet.borderCoordinateX());
        assertEquals(28.018875665728654, borderCoordinateSet.borderCoordinateY());
        assertEquals(9.84, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfBorderCoordinatesAreCorrect8() {
        var rectangle = new RectangleCoordinates();
        var borderCoordinateSet = rectangle.borderCoordinateSet("C4", "C1");

        assertEquals(8.436445233149492, borderCoordinateSet.borderCoordinateX());
        assertEquals(-1.0589544123724128, borderCoordinateSet.borderCoordinateY());
        assertEquals(16.445025604998307, borderCoordinateSet.borderCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect1() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C6", "C5");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C6", "C1");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C6", centerCoordinateSet);

        assertEquals(-7.455587043145984, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(8.873225943603602, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(16.44502560505658, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect2() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C5", "C2");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C5", "C6");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C5", centerCoordinateSet);

        assertEquals(-4.719500463052758, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(30.67099823535205, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(16.44502560505658, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect3() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C2", "C5");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C2", "C1");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C2", centerCoordinateSet);

        assertEquals(-0.5390127968617371, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(30.146257573953775, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(22.93292583494342, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect4() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C1", "C6");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C1", "C2");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C1", centerCoordinateSet);

        assertEquals(-3.275099376954964, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(8.348485282205322, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(22.93292583494342, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect5() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C1", "C2");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C1", "C4");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C1", centerCoordinateSet);

        assertEquals(5.236815756849337, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(7.280057783307766, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(22.93292583500169, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect6() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C2", "C3");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C2", "C1");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C2", centerCoordinateSet);

        assertEquals(7.972902336980109, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(29.077830076075813, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(22.93292583494342, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect7() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C3", "C4");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C3", "C2");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C3", centerCoordinateSet);

        assertEquals(12.153390003249946, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(28.55308941466764, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(16.44502560505658, innerCoordinateSet.innerCornerCoordinateZ());
    }

    @Test
    void setOfInnerCornerCoordinatesAreCorrect8() {
        var rectangle = new RectangleCoordinates();

        var borderCoordinateSet1 = rectangle.borderCoordinateSet("C4", "C1");
        var borderCoordinateSet2 = rectangle.borderCoordinateSet("C4", "C3");
        var centerCoordinateSet = rectangle.centerCoordinateSet(borderCoordinateSet1, borderCoordinateSet2);
        var innerCoordinateSet = rectangle.innerCoordinateSet("C4", centerCoordinateSet);

        assertEquals(9.417303423036634, innerCoordinateSet.innerCornerCoordinateX());
        assertEquals(6.7553171209038165, innerCoordinateSet.innerCornerCoordinateY());
        assertEquals(16.445025604998307, innerCoordinateSet.innerCornerCoordinateZ());
    }
}