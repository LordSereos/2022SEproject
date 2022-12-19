
import com.google.gson.annotations.SerializedName;

public class Point {
    @SerializedName("Id")
    private String id;
    @SerializedName("CoordinateX")
    private double coordinateX;
    @SerializedName("CoordinateY")
    private double coordinateY;
    @SerializedName("CoordinateZ")
    private double coordinateZ;

    public Point() {

    }

    public Point(String id, double coordinateX, double coordinateY, double coordinateZ) {
        this.id = id;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
    }

    public Point(double coordinateX, double coordinateY, double coordinateZ) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.coordinateZ = coordinateZ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public double getCoordinateZ() {
        return coordinateZ;
    }

    public void setCoordinateZ(double coordinateZ) {
        this.coordinateZ = coordinateZ;
    }


    public double calculateDistance(Point p1) {
        return Math.sqrt(Math.pow(p1.getCoordinateX(), 2) +
                Math.pow(p1.getCoordinateY(), 2) +
                Math.pow(p1.getCoordinateZ(), 2));
    }

    @Override
    public String toString() {
        return "Points{" +
                "id='" + id + '\'' +
                " " + coordinateX +
                ", " + coordinateY +
                ", " + coordinateZ +
                '}';
    }
}