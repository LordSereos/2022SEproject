import java.util.ArrayList;
import java.util.List;

public class RectangleCoordinates {
    private double x1, y1, z1, x2, y2, z2, ratio;
    private MeanRoofHeight roofHeight = new MeanRoofHeight();
    private Point point = new Point();
    private ReadFromJSON data = new ReadFromJSON();
    private static List<Point> ZoneOne = new ArrayList<>();
    private static List<Point> ZoneTwo = new ArrayList<>();
    private static List<Point> ZoneThree = new ArrayList<>();

    public RectangleCoordinates() {

    }

    public RectangleCoordinates(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.ratio = roofHeight.getWidthOfWindZone() / point.calculateDistance(x1, y1, z1, x2, y2, z2);
    }

    public double borderCoordinateX() {
        return x1 + ((x2 - x1) * ratio);
    }

    public double borderCoordinateY() {
        return y1 + ((y2 - y1) * ratio);
    }

    public double borderCoordinateZ() {
        return z1 + ((z2 - z1) * ratio);
    }

    public double centerOfBorderCoordinateX() {
        return x1 + (x2 - x1) * 0.5;
    }

    public double centerOfBorderCoordinateY() {
        return y1 + (y2 - y1) * 0.5;
    }

    public double centerOfBorderCoordinateZ() {
        return z1 + (z2 - z1) * 0.5;
    }

    public double innerCornerCoordinateX() {
        return x1 + (x2 - x1) * 2;
    }

    public double innerCornerCoordinateY() {
        return y1 + (y2 - y1) * 2;
    }

    public double innerCornerCoordinateZ() {
        return z1 + (z2 - z1) * 2;
    }

    public RectangleCoordinates borderCoordinateSet(String mainCoordinateName, String relativeCoordinateName) {
        RectangleCoordinates t1 = new RectangleCoordinates(
                data.getPointStorage().get(mainCoordinateName).getCoordinateX(), data.getPointStorage().get(mainCoordinateName).getCoordinateY(), data.getPointStorage().get(mainCoordinateName).getCoordinateZ(),
                data.getPointStorage().get(relativeCoordinateName).getCoordinateX(), data.getPointStorage().get(relativeCoordinateName).getCoordinateY(), data.getPointStorage().get(relativeCoordinateName).getCoordinateZ());

        return t1;
    }

    public RectangleCoordinates centerCoordinateSet(RectangleCoordinates borderCoordinateSet1, RectangleCoordinates borderCoordinateSet2) {
        RectangleCoordinates t3 = new RectangleCoordinates(
                borderCoordinateSet1.borderCoordinateX(), borderCoordinateSet1.borderCoordinateY(), borderCoordinateSet1.borderCoordinateZ(),
                borderCoordinateSet2.borderCoordinateX(), borderCoordinateSet2.borderCoordinateY(), borderCoordinateSet2.borderCoordinateZ());

        return t3;
    }

    public RectangleCoordinates innerCoordinateSet(String mainCoordinateName, RectangleCoordinates centerCoordinateSet) {
        RectangleCoordinates t4 = new RectangleCoordinates(
                data.getPointStorage().get(mainCoordinateName).getCoordinateX(), data.getPointStorage().get(mainCoordinateName).getCoordinateY(), data.getPointStorage().get(mainCoordinateName).getCoordinateZ(),
                centerCoordinateSet.centerOfBorderCoordinateX(), centerCoordinateSet.centerOfBorderCoordinateY(), centerCoordinateSet.centerOfBorderCoordinateZ());

        return t4;
    }

    private void putCoordinatesIntoZoneOne(String mainCoordinateName, RectangleCoordinates t4) {
        ZoneOne.add(new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));
    }

    private void putCoordinatesIntoZoneTwo(String mainCoordinateName, RectangleCoordinates t1, RectangleCoordinates t2, RectangleCoordinates t4) {
        ZoneTwo.add(new Point(data.getPointStorage().get(mainCoordinateName).getCoordinateX(),
                data.getPointStorage().get(mainCoordinateName).getCoordinateY(),
                data.getPointStorage().get(mainCoordinateName).getCoordinateZ()));
        ZoneTwo.add(new Point(t1.borderCoordinateX(), t1.borderCoordinateY(), t1.borderCoordinateZ()));
        ZoneTwo.add(new Point(t2.borderCoordinateX(), t2.borderCoordinateY(), t2.borderCoordinateZ()));
        ZoneTwo.add(new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));

    }

    private void putCoordinatesIntoZoneThree(String mainCoordinateName, RectangleCoordinates t1, RectangleCoordinates t2, RectangleCoordinates t4) {
        ZoneThree.add(new Point(data.getPointStorage().get(mainCoordinateName).getCoordinateX(), data.getPointStorage().get(mainCoordinateName).getCoordinateY(), data.getPointStorage().get(mainCoordinateName).getCoordinateZ()));
        ZoneThree.add(new Point(t1.borderCoordinateX(), t1.borderCoordinateY(), t1.borderCoordinateZ()));
        ZoneThree.add(new Point(t2.borderCoordinateX(), t2.borderCoordinateY(), t2.borderCoordinateZ()));
        ZoneThree.add(new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));

    }

    public void findCordinates(String mainCoordinateName, String relativeCoordinateName1, String relativeCoordinateName2) {
        RectangleCoordinates t1 = borderCoordinateSet(mainCoordinateName, relativeCoordinateName1);
        RectangleCoordinates t2 = borderCoordinateSet(mainCoordinateName, relativeCoordinateName2);
        RectangleCoordinates t3 = centerCoordinateSet(t1, t2);
        RectangleCoordinates t4 = innerCoordinateSet(mainCoordinateName, t3);

        putCoordinatesIntoZoneOne(mainCoordinateName, t4);
        putCoordinatesIntoZoneTwo(mainCoordinateName, t1, t2, t4);
        putCoordinatesIntoZoneThree(mainCoordinateName, t1, t2, t4);
    }

    public List<Point> getZoneOne() {
        return ZoneOne;
    }

    public List<Point> getZoneTwo() {
        return ZoneTwo;
    }

    public List<Point> getZoneThree() {
        return ZoneThree;
    }

}
