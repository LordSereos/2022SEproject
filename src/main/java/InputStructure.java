import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class InputStructure {
    @SerializedName("Roofs")
    private ArrayList<Roof> roofs;

    public InputStructure() {

    }

    public InputStructure(ArrayList<Roof> roofs) {
        this.roofs = roofs;
    }

    public ArrayList<Roof> getRoofs() {
        return roofs;
    }

    public void setRoofs(ArrayList<Roof> roofs) {
        this.roofs = roofs;
    }

}