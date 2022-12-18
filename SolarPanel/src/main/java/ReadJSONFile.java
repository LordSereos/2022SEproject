import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ReadJSONFile {
    public static void main(String[] args) throws IOException {
        // very very very early version
        Gson gson = new Gson();

        Reader reader = Files.newBufferedReader(Paths.get("testJSON.json"));

        Map<?, ?> map = gson.fromJson(reader, Map.class);

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        reader.close();


        }
}
