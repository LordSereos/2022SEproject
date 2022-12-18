public class Line {
    /**
     * Every line has its default parameters:
     * ID (L1, L2, ...)
     * type, which defines if it is a RAKE, RIDGE, EAVE or SETBACK_LINE
     * and two points which form that line (start and end)
     */
    private String id;
    private String type;
    private Point p1, p2;
    /**
     * From the default parameters, we can calculate the vector of that line,
     * its length, point vector of the RIDGE point and the setback point
     * coordinates (3 feet from RIDGE on that line)
     *
     *  v is the vector between p1 and p2
     *  u is the point vector from the RIDGE point (p1)
     */
    private Point v = new Point();
    private double length;
    private Point u = new Point();
    private Point setback = new Point();

    public Line() {
    }

    public Line(String id, String type, Point point1, Point point2) {
        this.id = id;
        this.type = type;
        if(point1.getCoordinateZ() > point2.getCoordinateZ()) {
            this.p1 = point1;
            this.p2 = point2;
        } else {
            this.p2 = point1;
            this.p1 = point2;
        }
/**
 * setV - finds the vector between two points forming a line
 * setLength - finds the length of that vector
 * setU - finds the point vector of a line
 * setSetbackCoordinates - find a point which is 3 feet away from our RIDGE on that line
 */
        setV(p1, p2);
        setLength(v);
        setU(v, length);
        setSetbackCoordinates(u);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setV(Point p1, Point p2){
        v.setId("V"+id.charAt(1));
        v.setCoordinateX(p2.getCoordinateX()- p1.getCoordinateX());
        v.setCoordinateY(p2.getCoordinateY()- p1.getCoordinateY());
        v.setCoordinateZ(p2.getCoordinateZ()- p1.getCoordinateZ());
    }
    public Point getV(){
        return v;
    }

    public void setLength(Point v){
        length = Math.sqrt(v.getCoordinateX()*v.getCoordinateX() +
                v.getCoordinateY()*v.getCoordinateY() +
                v.getCoordinateZ()*v.getCoordinateZ());
    }
    public double getLength(){
        return length;
    }
    public void setU(Point v, double length){
        u.setCoordinateX(v.getCoordinateX() / length);
        u.setCoordinateY(v.getCoordinateY() / length);
        u.setCoordinateZ(v.getCoordinateZ() / length);
        u.setId("U"+id.charAt(1));
    }
    public Point getU(){
        return u;
    }

    public void setSetbackCoordinates(Point u){
        double f = 3.0;

        setback.setCoordinateX(u.getCoordinateX()*f + p1.getCoordinateX());
        setback.setCoordinateY(u.getCoordinateY()*f + p1.getCoordinateY());
        setback.setCoordinateZ(u.getCoordinateZ()*f + p1.getCoordinateZ());
        setback.setId("S"+id.charAt(1));

    }
    public Point getSetbackCoordinates(){
        return setback;
    }


    @Override
    public String toString() {
        return "Lines{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", v=" + v +
                ", length=" + length +
                ", setback=" + setback +
                '}';
    }
    public String customToString(){
        return "Line " + id + " is " + type + ". Its vector is: " + v.getCoordinateX() + " " + v.getCoordinateY() + " " + v.getCoordinateZ() + ", u: " + u.getCoordinateX() + " " + u.getCoordinateY() + " " + u.getCoordinateZ() + " \n" +
                "                 length is: " + length + " and our setback coordinates are: " + setback.getCoordinateX() + " " + setback.getCoordinateY() + " " + setback.getCoordinateZ() + " " + setback.getId();
    }
}
