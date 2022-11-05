public class MeanRoofHeight {
    private double heighest = 0, lowest;
    private ReadFromJSON data = new ReadFromJSON();
    public MeanRoofHeight() {
    }

    public double getHeighestZCoordinate() {
        for (Point value : data.readPoints().values()) {
            if(value.getZ() > heighest)
                heighest = value.getZ();
        }

        return heighest;
    }

    public double getLowestZCoordinate() {
        lowest = getHeighestZCoordinate();
        for (Point value : data.readPoints().values()) {
            if(value.getZ() < lowest)
                lowest = value.getZ();
        }

        return lowest;
    }
    public double getMeanHeight() {
        return (getHeighestZCoordinate() + getLowestZCoordinate()) / 2;
    }

    public double getWidthOfWindZone() {
        if(getMeanHeight() >= 3.0)
            return 0.4 * getMeanHeight();
        else
            return 3.0;
    }
}
