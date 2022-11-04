public class Point {
    /**
     * Every point has its ID (C1, C2, ...)
     * and coordinates in 3d space forming a roof.
     */
    private String id;
    private double coordinateX;
    private double coordinateY;
    private double coordinateZ;

    public Point() {
    }

    public Point(String id, double coordx, double coordy, double coordz) {
        this.id = id;
        this.coordinateX = coordx;
        this.coordinateY = coordy;
        this.coordinateZ = coordz;
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

    @Override
    public String toString() {
        return "Points{" +
                "id='" + id + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", coordinateZ=" + coordinateZ +
                '}';
    }
}