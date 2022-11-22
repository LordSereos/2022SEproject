import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
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

        Point C1 = new Point("C1", 0, 0, 29.53795144);
        Point C2 = new Point("C2", 4.697802960025147, 37.42631535828113, 29.53795144);
        Point C3 = new Point("C3", 17.390205760058013, 35.833147199004884, 9.84);
        Point C4 = new Point("C4", 12.692402800032868, -1.593168162330985, 9.84);
        Point C5 = new Point("C5", -7.994599840007722, 39.01948351755738, 9.84);
        Point C6 = new Point("C6", -12.692402800032868, 1.593168159276247, 9.84);

        List<Line> lines = new ArrayList<>();
        Line L1 = new Line("L1", "RIDGE", C1, C2);
        Line L2 = new Line("L2", "RAKE", C2, C3);
        Line L3 = new Line("L3", "EAVE", C3, C4);
        //Line L4 = new Line("L4", "RAKE", C4, C1);
        Line L4 = new Line("L4", "RAKE", C1, C4);
        Line L5 = new Line("L5", "RAKE", C2, C5);
        Line L6 = new Line("L6", "EAVE", C5, C6);
        //Lines L7 = new Lines("L7", "RAKE", C6, C1);
        Line L7 = new Line("L7", "RAKE", C1, C6);

        lines.add(L1);
        lines.add(L2);
        lines.add(L3);
        lines.add(L4);
        lines.add(L5);
        lines.add(L6);
        lines.add(L7);
/**
 * 12Here we scan through our list of lines, and if the line is of type RAKE,
 * we need to apply our calculations to this line (find the setback from top
 * of the line).
 *
 * We also form a new list of setbackPoints, where we will store points that
 * are on the RAKE with 3 feet distance from the RIDGE
 */
        List<Point> setbackPoints = new ArrayList<>();
        for (Line i : lines){
            if (i.getType() == "RAKE"){
                System.out.println(i.customToString());
                setbackPoints.add(i.getSetbackCoordinates());
            }
        }
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

