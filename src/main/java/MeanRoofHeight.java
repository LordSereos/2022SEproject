public class MeanRoofHeight {
    public final JsonReader data = new JsonReader();

    public MeanRoofHeight() {

    }

    public double getHeighestZCoordinate() {
        double heighest = 0;
        for (Point value : data.getPointStorage().values()) {
            if (value.getCoordinateZ() > heighest)
                heighest = value.getCoordinateZ();
        }

        return heighest;
    }

    public double getLowestZCoordinate() {
        double lowest = getHeighestZCoordinate();
        for (Point value : data.getPointStorage().values()) {
            if (value.getCoordinateZ() < lowest)
                lowest = value.getCoordinateZ();
        }

        return lowest;
    }

    public double getMeanHeight() {
        return (getHeighestZCoordinate() + getLowestZCoordinate()) / 2;
    }

    public double getWidthOfWindZone() {
        if (getMeanHeight() >= 3.0)
            return 0.4 * getMeanHeight();
        else
            return 3.0;
    }
}
