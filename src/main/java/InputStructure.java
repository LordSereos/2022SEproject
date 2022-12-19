import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class InputStructure {
    @SerializedName("Roofs")
    private ArrayList<Edge> roof_edges;

    public InputStructure() {

    }

    public InputStructure(ArrayList<Edge> roofs) {
        this.roof_edges = roofs;
    }

    public ArrayList<Edge> getRoofs() {
        return roof_edges;
    }

    public void setRoofs(ArrayList<Edge> roofs) {
        this.roof_edges = roofs;
    }

    @Override
    public String toString() {
        return "InputStructure{" +
                "roofs=" + roof_edges +
                '}';
    }
}