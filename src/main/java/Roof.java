import java.util.ArrayList;
import java.util.SplittableRandom;

public class Roof {
    private String id;
    private String type;
    private ArrayList<Point> points;

    public Roof() {}

    public Roof(String id, String type, ArrayList<Point> points) {
        this.id = id;
        this.type = type;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }


    @Override
    public String toString() {
        return "Roof{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", points=" + points +
                '}';
    }
}
