import java.util.HashMap;

public class Roof {

    ReadFromJSON data = new ReadFromJSON();
    private String id;
    private String edgeType;
    public HashMap<String, Point> pointInfo;

    public Roof(String id, String edgeType) {
        this.id = id;
        this.edgeType = edgeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(String edgeType) {
        this.edgeType = edgeType;
    }
}
