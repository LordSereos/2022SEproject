import java.util.ArrayList;

public class MeanRoofHeight {
    public MeanRoofHeight() {

    }

    public double getHeighestZCoordinate(ArrayList<Edge> roofs) {
        double heighest = 0;
        for (Edge value : roofs) {
            for (Point p : value.getPoints())
                if (p.getCoordinateZ() > heighest)
                    heighest = p.getCoordinateZ();
        }
        return heighest;
    }

    public double getLowestZCoordinate(ArrayList<Edge> roofs) {
        double lowest = getHeighestZCoordinate(roofs);
        for (Edge value : roofs) {
            for (Point p : value.getPoints())
                if (p.getCoordinateZ() < lowest)
                    lowest = p.getCoordinateZ();
        }
        return lowest;
    }

    public double getMeanHeight(ArrayList<Edge> roofs) {
        return (getHeighestZCoordinate(roofs) + getLowestZCoordinate(roofs)) / 2;
    }

    public double getWidthOfWindZone(ArrayList<Edge> roofs) {
        if (getMeanHeight(roofs) >= 3.0)
            return 0.4 * getMeanHeight(roofs);
        else
            return 3.0;
    }
}
