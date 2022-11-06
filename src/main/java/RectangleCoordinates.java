import java.util.HashMap;

//TODO create Unit tests for this class
//AR INCLUDINTI VISUS TASKUS??

public class RectangleCoordinates {
    private double x1, y1, z1, x2, y2, z2, ratio;
    private MeanRoofHeight roofHeight = new MeanRoofHeight();

    private Point point = new Point();
    private ReadFromJSON data = new ReadFromJSON();

    private HashMap<String, Point> zoneOne = new HashMap<String, Point>();
    private HashMap<String, Point> zoneTwo = new HashMap<String, Point>();
    private HashMap<String, Point> zoneThree = new HashMap<String, Point>();

    public RectangleCoordinates(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.ratio = roofHeight.getWidthOfWindZone() / point.calculateDistance(x1, y1, z1, x2, y2, z2);
    }

    public RectangleCoordinates() {
    }

    private double borderCoordinateX() {
        return x1 + ((x2 - x1) * ratio);
    }

    private double borderCoordinateY() {
        return y1 + ((y2 - y1)  * ratio);
    }

    private double borderCoordinateZ() {
        return z1 + ((z2 - z1) * ratio);
    }

    private double centerOfBorderCoordinateX() {
        return x1 + (x2 - x1) * 0.5;
    }

    private double centerOfBorderCoordinateY() {
        return y1 + (y2 - y1) * 0.5;
    }

    private double centerOfBorderCoordinateZ() {
        return z1 + (z2 - z1) * 0.5;
    }

    private double innerCornerCoordinateX() {
        return x1 + (x2 - x1) * 2;
    }

    private double innerCornerCoordinateY() {
        return y1 + (y2 - y1) * 2;
    }

    private double innerCornerCoordinateZ() {
        return z1 + (z2 - z1) * 2;
    }

    private RectangleCoordinates borderCoordinateSet(String mainCoordinateName, String relativeCoordinateName) {
        RectangleCoordinates t1 = new RectangleCoordinates(
                data.readPoints().get(mainCoordinateName).getX(), data.readPoints().get(mainCoordinateName).getY(), data.readPoints().get(mainCoordinateName).getZ(),
                data.readPoints().get(relativeCoordinateName).getX(), data.readPoints().get(relativeCoordinateName).getY(), data.readPoints().get(relativeCoordinateName).getZ());

        return t1;
    }

    private RectangleCoordinates centerCoordinateSet(RectangleCoordinates borderCoordinateSet1, RectangleCoordinates borderCoordinateSet2) {
        RectangleCoordinates t3 = new RectangleCoordinates(
                borderCoordinateSet1.borderCoordinateX(), borderCoordinateSet1.borderCoordinateY(), borderCoordinateSet1.borderCoordinateZ(),
                borderCoordinateSet2.borderCoordinateX(), borderCoordinateSet2.borderCoordinateY(), borderCoordinateSet2.borderCoordinateZ());

        return t3;
    }

    private RectangleCoordinates innerCoordinateSet(String mainCoordinateName, RectangleCoordinates centerCoordinateSet) {
        RectangleCoordinates t4 = new RectangleCoordinates(
                data.readPoints().get(mainCoordinateName).getX(), data.readPoints().get(mainCoordinateName).getY(), data.readPoints().get(mainCoordinateName).getZ(),
                centerCoordinateSet.centerOfBorderCoordinateX(), centerCoordinateSet.centerOfBorderCoordinateY(), centerCoordinateSet.centerOfBorderCoordinateZ());

        return t4;
    }

    private void putCoordinatesIntoZoneOne(String mainCoordinateName, RectangleCoordinates t4) {
        String zoneOnePointName = mainCoordinateName + "One";

        if (!(zoneOne.containsKey(zoneOnePointName)))
            zoneOne.put(zoneOnePointName, new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));
        else {
            zoneOnePointName = mainCoordinateName + "OneOtherSide";
            zoneOne.put(zoneOnePointName, new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));
        }

    }

    private void putCoordinatesIntoZoneTwo(String mainCoordinateName, String relativeCoordinateName1, String relativeCoordinateName2,
                                           RectangleCoordinates t1, RectangleCoordinates t2, RectangleCoordinates t4) {
        String zoneTwoPointName1 = mainCoordinateName + relativeCoordinateName1 + "Two1";
        zoneTwo.put(zoneTwoPointName1, new Point(t1.borderCoordinateX(), t1.borderCoordinateY(), t1.borderCoordinateZ()));

        String zoneTwoPointName2 = mainCoordinateName + relativeCoordinateName2 + "Two2";
        zoneTwo.put(zoneTwoPointName2, new Point(t2.borderCoordinateX(), t2.borderCoordinateY(), t2.borderCoordinateZ()));

        String zoneTwoPointName3 = mainCoordinateName + relativeCoordinateName2 + "Two3";
        zoneTwo.put(zoneTwoPointName3, new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));

    }

    private void putCoordinatesIntoZoneThree(String mainCoordinateName, String relativeCoordinateName1, String relativeCoordinateName2,
                                             RectangleCoordinates t1, RectangleCoordinates t2, RectangleCoordinates t4) {

        if (!(zoneThree.containsKey(mainCoordinateName)))
            zoneThree.put(mainCoordinateName, new Point(data.readPoints().get(mainCoordinateName).getX(), data.readPoints().get(mainCoordinateName).getY(), data.readPoints().get(mainCoordinateName).getZ()));
        else {
            String zoneThreeOtherSide = mainCoordinateName + "ThreeOtherSide";
            zoneThree.put(zoneThreeOtherSide, new Point(data.readPoints().get(mainCoordinateName).getX(), data.readPoints().get(mainCoordinateName).getY(), data.readPoints().get(mainCoordinateName).getZ()));
        }

        String zoneThreePointName1 = mainCoordinateName + relativeCoordinateName1 + "Three1";
        zoneThree.put(zoneThreePointName1, new Point(t1.borderCoordinateX(), t1.borderCoordinateY(), t1.borderCoordinateZ()));

        String zoneThreePointName2 = mainCoordinateName + relativeCoordinateName2 + "Three2";
        zoneThree.put(zoneThreePointName2, new Point(t2.borderCoordinateX(), t2.borderCoordinateY(), t2.borderCoordinateZ()));

        String zoneThreePointName3 = mainCoordinateName + relativeCoordinateName2 + "Three3";
        zoneThree.put(zoneThreePointName3, new Point(t4.innerCornerCoordinateX(), t4.innerCornerCoordinateY(), t4.innerCornerCoordinateZ()));
    }

    public void findCordinates(String mainCoordinateName, String relativeCoordinateName1, String relativeCoordinateName2) {
        RectangleCoordinates t1 = borderCoordinateSet(mainCoordinateName, relativeCoordinateName1);
        RectangleCoordinates t2 = borderCoordinateSet(mainCoordinateName, relativeCoordinateName2);
        RectangleCoordinates t3 = centerCoordinateSet(t1, t2);
        RectangleCoordinates t4 = innerCoordinateSet(mainCoordinateName, t3);

        putCoordinatesIntoZoneOne(mainCoordinateName, t4);
        putCoordinatesIntoZoneTwo(mainCoordinateName, relativeCoordinateName1, relativeCoordinateName2, t1, t2, t4);
        putCoordinatesIntoZoneThree(mainCoordinateName, relativeCoordinateName1, relativeCoordinateName2, t1, t2, t4);


        // TODO change generated points naming scheme to simpler one (especially for zone 2 and 3)
    }

    public void printZoneOne() {
        int i = 1;
        for (HashMap.Entry<String, Point> entry : zoneOne.entrySet()) {
            System.out.println (i + ". " + entry.getKey() + ": " +
                    entry.getValue().getX() + " " +
                    entry.getValue().getY() + " " +
                    entry.getValue().getZ());
            i++;
        }
        System.out.println();
    }

    public void printZoneTwo() {
        int i = 1;
        for (HashMap.Entry<String, Point> entry : zoneTwo.entrySet()) {
            System.out.println (i + ". " + entry.getKey() + ": " +
                    entry.getValue().getX() + " " +
                       entry.getValue().getY() + " " +
                    entry.getValue().getZ());
            i++;
        }
        System.out.println();
    }

    public void printZoneThree() {
        int i = 1;
        for (HashMap.Entry<String, Point> entry : zoneThree.entrySet()) {
            System.out.println (i + ". " + entry.getKey() + ": " +
                    entry.getValue().getX() + " " +
                    entry.getValue().getY() + " " +
                    entry.getValue().getZ());
            i++;
        }
        System.out.println();
    }

}
