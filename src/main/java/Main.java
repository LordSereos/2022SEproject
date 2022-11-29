import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");

        RectangleCoordinates roof = new RectangleCoordinates();


        roof.findCordinates("C6", "C5", "C1");
        roof.findCordinates("C5", "C2", "C6");
        roof.findCordinates("C2", "C1", "C5");
        roof.findCordinates("C1", "C6", "C2");

        roof.findCordinates("C1", "C2", "C4");
        roof.findCordinates("C2", "C3", "C1");
        roof.findCordinates("C3", "C4", "C2");
        roof.findCordinates("C4", "C1", "C3");

        roof.printZoneOne();
        roof.printZoneTwo();
        roof.printZoneThree();

        Point C1 = data.getPointStorage().get("C1");
        Point C2 = data.getPointStorage().get("C2");
        Point C3 = data.getPointStorage().get("C3");
        Point C4 = data.getPointStorage().get("C4");
        Point C5= data.getPointStorage().get("C5");
        Point C6 = data.getPointStorage().get("C6");

        List<Line> lines = new ArrayList<>();


        for (int i = 0; i < data.getInfo().getRoofs().size(); i++) {
            Line L = new Line(data.getInfo().getRoofs().get(i).getId(),
                    data.getInfo().getRoofs().get(i).getType(),
                    data.getInfo().getRoofs().get(i).getPoints().get(0),
                    data.getInfo().getRoofs().get(i).getPoints().get(1));
            lines.add(L);
        }


/**
 * 1Here we scan through our list of lines, and if the line is of type RAKE,
 * we need to apply our calculations to this line (find the setback from top
 * of the line).
 *
 * We also form a new list of setbackPoints, where we will store points that
 * are on the RAKE with 3 feet distance from the RIDGE
 */
        List<Point> setbackPoints = new ArrayList<>();
        for (Line i : lines){
            if (i.getType().equals("RAKE")){
                System.out.println(i.customToString());
                setbackPoints.add(i.getSetbackCoordinates());
            }
        }

        // Code to get setBackArea from team2
        RidgeSetbackCalculator ridgeSetbackCalculator = new RidgeSetbackCalculator();
        FormSetbackArea setbackArea = ridgeSetbackCalculator.getFormSetbackArea(lines);

/**
 * Now when we have all the setback points, we need to form lines between correct points.
 * The line will represent the limit of the 3feet from the ridge. This line is also
 * parallel to the RIDGE
 */
        new FormSetbackArea(setbackPoints, lines);

        System.out.println("ALL LINES AFTER CALCULATING SETBACK:");
        for (Line i : lines){
            System.out.println(i.getId()+ " " + i.getType() + " " + i.getP1().getId() + " " + i.getP2().getId());
            System.out.println(i.getType());
        }

    }


}

