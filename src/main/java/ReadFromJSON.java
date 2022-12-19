
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadFromJSON {
    public ArrayList<Edge> readPoints(String fileName) {
        Gson gson = new Gson();
        InputStructure data = new InputStructure();
        try (Reader reader = new FileReader(fileName)) {
            data = gson.fromJson(reader, InputStructure.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.getRoofs();
    }
}

