import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFromJSON data = new ReadFromJSON();
        ArrayList<Edge> roof_edges = data.readPoints("./JSON_files/data2.json");
        for (Edge e : roof_edges) {
            System.out.println(e.toString());
        }
        ArrayList<Point> first_zone = new ArrayList<>();
        ArrayList<Point> third_zone = new ArrayList<>();

        CalcZones cz = new CalcZones();
        cz.calc_zones(roof_edges, first_zone, third_zone);
        System.out.println("\nFirst zone: ");
        cz.print_zone(first_zone);
        System.out.println("\nThird zone: ");
        cz.print_zone(third_zone);
        System.out.println();
    }
}
