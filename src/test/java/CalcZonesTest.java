import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class CalcZonesTest {
    @Test
    public void testCalcVectorCoordn() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(5, 9, 14);

        CalcZones calcZones = new CalcZones();
        Point actualVector = calcZones.calc_vector_coordn(p1, p2);

        assertEquals(4, actualVector.getCoordinateX());
        assertEquals(7, actualVector.getCoordinateY());
        assertEquals(11, actualVector.getCoordinateZ());
    }

    @Test
    public void testCalcPoint() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 7);
        double width = 1.5;

        CalcZones calcZones = new CalcZones();
        Point actualPoint = calcZones.calc_point(p1, p2, width);

        assertEquals(1.7717436331412897, actualPoint.getCoordinateX());
        assertEquals(2.7717436331412895, actualPoint.getCoordinateY());
        assertEquals(4.028991510855053, actualPoint.getCoordinateZ());
    }

    @Test
    public void testFindFirstZonePoints() {
        Point p1 = new Point("C1", 1.17445, 9.35658, 29.538);
        Point p2 = new Point("C2", 3.52335, 28.0697, 29.538);
        Point p3 = new Point("C3", 17.390205760058013, 35.833147199004884, 9.84);

        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(p1);
        points1.add(p2);

        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(p2);
        points2.add(p3);

        Edge e1 = new Edge("L1", "RIDGE", points1);
        Edge e2 = new Edge("L2", "HIP", points2);
        ArrayList<Point> firstZone = new ArrayList<>();

        CalcZones calcZones = new CalcZones();

        calcZones.find_first_zone_points(7.8756, p2, e1, e2, firstZone);


        assertEquals(7.116143194371764, firstZone.get(0).getCoordinateX());
        assertEquals(22.25232435193956, firstZone.get(0).getCoordinateY());
        assertEquals(22.932976929376192, firstZone.get(0).getCoordinateZ());

    }

    @Test
    public void testFindThirdZonePoints() {
        Point p1 = new Point("C1", 1.17445, 9.35658, 29.538);
        Point p3 = new Point("C3", 17.390205760058013, 35.833147199004884, 9.84);
        Point p4 = new Point("C4", 12.692402800032868, -1.593168162330985, 9.84);

        ArrayList<Point> points1 = new ArrayList<>();
        points1.add(p3);
        points1.add(p4);

        ArrayList<Point> points2 = new ArrayList<>();
        points2.add(p4);
        points2.add(p1);

        Edge e1 = new Edge("L3", "EAVE", points1);
        Edge e2 = new Edge("L4", "HIP", points2);
        ArrayList<Point> thirdZone = new ArrayList<>();

        CalcZones calcZones = new CalcZones();

        calcZones.find_third_zone_points(7.8756, p4, e1, e2, thirdZone);

        assertEquals(8.83026560916252, thirdZone.get(0).getCoordinateX());
        assertEquals(2.078441744620714, thirdZone.get(0).getCoordinateY());
        assertEquals(16.445026058584563, thirdZone.get(0).getCoordinateZ());

        assertEquals(13.67326219949217, thirdZone.get(1).getCoordinateX());
        assertEquals(6.221113007328343, thirdZone.get(1).getCoordinateY());
        assertEquals(9.84, thirdZone.get(1).getCoordinateZ());

        assertEquals(9.417319114191868, thirdZone.get(2).getCoordinateX());
        assertEquals(6.755361956082349, thirdZone.get(2).getCoordinateY());
        assertEquals(16.445026058584563, thirdZone.get(2).getCoordinateZ());

    }
}