import java.util.ArrayList;
import java.util.List;

public class RidgeSetbackCalculator {
    private List<Point> setbackPoints = new ArrayList<>();

    private List<Point> getSetbackPoints(List<Line> lines) {
        for (Line i : lines){
            if (i.getType().equals("RAKE")){
                setbackPoints.add(i.getSetbackCoordinates());
            }
        }
        return setbackPoints;
    }

    public FormSetbackArea getFormSetbackArea(List<Line> lines) {
        List<Point> points = getSetbackPoints(lines);
        return new FormSetbackArea(points, lines);
    }



}
