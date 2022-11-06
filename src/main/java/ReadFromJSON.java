import java.util.HashMap;

public class ReadFromJSON {
    public HashMap<String, Point> readPoints() {
        HashMap<String, Point> pointStorage = new HashMap<String, Point>();

        pointStorage.put("C1", new Point(0.0, 0.0, 29.53795144));
        pointStorage.put("C2", new Point(4.697802960025147, 37.42631535828113, 29.53795144));
        pointStorage.put("C3", new Point(17.390205760058013, 35.833147199004884, 9.84));
        pointStorage.put("C4", new Point(12.692402800032868, -1.593168162330985, 9.84));
        pointStorage.put("C5", new Point(-7.994599840007722, 39.01948351755738, 9.84));
        pointStorage.put("C6", new Point(-12.692402800032868, 1.593168159276247, 9.84));

        //TODO Add reading from JSON file function instead of providing points manually in code

        return pointStorage;
    }

}
