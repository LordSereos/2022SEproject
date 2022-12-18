import java.util.List;

public class Area2 {
    public Area2() {

    }

    public Area2(List<Line> lines) {
        RidgeSetbackCalculator ridgeSetbackCalculator = new RidgeSetbackCalculator();
        FormSetbackArea setbackArea = ridgeSetbackCalculator.getFormSetbackArea(lines);

        System.out.println("ALL LINES AFTER CALCULATING SETBACK:");
        for (Line i : lines) {
            System.out.println(i.getId() + " " + i.getType() + " " + i.getP1().getId() + " " + i.getP2().getId());
        }
        System.out.println();
    }
}
