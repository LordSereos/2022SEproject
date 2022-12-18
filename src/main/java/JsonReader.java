
import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonReader {
    private static HashMap<String, Point> pointStorage = new HashMap<>();
    private static ArrayList<Roof> roof = new ArrayList<>();
    private static InputStructure info = new InputStructure();

     void readPoints(String fileName) {
         Gson gson = new Gson();

         try (Reader reader = new FileReader(fileName)) {
            InputStructure data = gson.fromJson(reader, InputStructure.class);
            pointStorage = formatData(data);
            info = data;
         } catch (IOException e) {
            e.printStackTrace();
         }

    }

    public HashMap<String, Point> formatData(InputStructure data) {
        HashMap<String, Point> points = new HashMap<>();
        for (Roof i : data.getRoofs()){
            for(Point j : i.getPoints())
                points.put(j.getId(), new Point(j.getId(), j.getCoordinateX(), j.getCoordinateY(), j.getCoordinateZ()));
        }
        return points;
    }

    public InputStructure getInfo() {
         return info;
    }

    public void parsedData(InputStructure data) {
        for (Roof roofs : data.getRoofs()){
            System.out.println(roofs.getId());
            System.out.println(roofs.getType());
            System.out.println(roofs.getPoints().get(0));
            System.out.println(roofs.getPoints().get(1));
        }
    }

    public HashMap<String, Point> getPointStorage() {
        return this.pointStorage;
    }


}

