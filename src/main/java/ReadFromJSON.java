import com.google.gson.Gson;
import java.io.*;
import java.util.HashMap;

public class ReadFromJSON {
    private static HashMap<String, Point> pointStorage = new HashMap<>();

     void readPoints(String fileName) {
         Gson gson = new Gson();

         try (Reader reader = new FileReader(fileName)) {
            InputStructure data = gson.fromJson(reader, InputStructure.class);
            pointStorage = formatData(data);
         } catch (IOException e) {
            e.printStackTrace();
         }

    }

    public HashMap<String, Point> formatData(InputStructure data) {
        HashMap<String, Point> points = new HashMap<>();
        for (Roof i : data.getRoofs()){
            for(Point j : i.getPoints())
                points.put(j.getId(), new Point(j.getCoordinateX(), j.getCoordinateY(), j.getCoordinateZ()));
        }
        return points;
    }

    public HashMap<String, Point> getPointStorage() {
        return this.pointStorage;
    }
}
