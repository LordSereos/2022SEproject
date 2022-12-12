import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < data.getInfo().getRoofs().size(); i++) {
            Line L = new Line(data.getInfo().getRoofs().get(i).getId(),
                    data.getInfo().getRoofs().get(i).getType(),
                    data.getInfo().getRoofs().get(i).getPoints().get(0),
                    data.getInfo().getRoofs().get(i).getPoints().get(1));
            lines.add(L);
        }

        new Area2(lines);

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

        FormatPdf formatPdf = new FormatPdf();
        formatPdf.PrintPdf();

        DrawLinesFromCords drawLinesFromCords = new DrawLinesFromCords();
        drawLinesFromCords.Launch(lines);

    }

}

