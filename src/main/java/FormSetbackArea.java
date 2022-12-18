import java.util.ArrayList;
import java.util.List;

/**
 * A class where the calculations are made to form a correct line between setback points,
 * and add this line to the list of all lines.
 */
public class FormSetbackArea {

    private List<Line> lines;
    private List<Line> ridges = new ArrayList<>();
    private List<Line> rakes = new ArrayList<>();
    /**
     * ridgeRakeCluster - two-dimensional array, which stores the ridges and the RAKES that connect to a particular RIDGE
     * rectangles - list of rectangles, that are formed out of 4 points, 2 of which are ridge points.
     *              used for intermediary calculations - to find the correct line between setback points
     */
    private ArrayList<ArrayList<Point>> ridgeRakeCluster = new ArrayList<>();
    private ArrayList<ArrayList<Rectangle>> rectangles = new ArrayList<>();

    public FormSetbackArea(){
    }

    public FormSetbackArea(List<Point> points, List<Line> lines){
        this.lines = lines;
        setRidges(ridges, lines);
        setRakes(rakes, lines);
        setRidgeRakeCluster(ridgeRakeCluster, ridges, rakes);
        setRectangles(rectangles, ridgeRakeCluster);
        /**
         * When we have a list of rectangles on the roof, we need to add the parallel to the RIDGE line to the
         * list of all lines as the SETBACK_LINE
         *
         * Each ridge (i) has two rectangles (one on each side):
         * .get(0) and .get(1) represent different rectangles for one RIDGE
         */
        for (int i=0;i<rectangles.size();i++){
            findCoplanarVector(rectangles.get(i).get(0), lines);
            findCoplanarVector(rectangles.get(i).get(1), lines);
        }
    }

    public ArrayList<ArrayList<Rectangle>> getRectangles() {
        return rectangles;
    }

    public void setRectangles(ArrayList<ArrayList<Rectangle>> rectangles, ArrayList<ArrayList<Point>> ridgeRakeCluster) {
        for (int i=0; i < ridges.size(); i++){
            rectangles.add(new ArrayList<>());
        }

        for (ArrayList<Point> cluster : ridgeRakeCluster){
            int i=0;
            /**
             * r1 and r2 are the points that form the RIDGE - they have to be the first points in the cluster.
             */
            Point r1 = ridgeRakeCluster.get(i).get(0);
            Point r2 = ridgeRakeCluster.get(i).get(1);
            for (Point j : cluster.subList(2,6)){
                int n=2;
                for (Point z : cluster.subList(n+1,6)){
                    if (cluster.indexOf(j) < cluster.indexOf(z)){
                        if (pointsFormRectangle(r1, r2, j, z)){
                            rectangles.get(i).add(new Rectangle(r1, r2, j, z));
                        }
                    }
                }
                n++;
            }
            i++;
        }
    }

    public ArrayList<ArrayList<Point>> getRidgeRakeCluster() {
        return ridgeRakeCluster;
    }

    public void setRidgeRakeCluster(ArrayList<ArrayList<Point>> ridgeRakeCluster, List<Line> ridges, List<Line> rakes) {
        for (int i=0; i < ridges.size(); i++){
            ridgeRakeCluster.add(new ArrayList<>());
        }

        for (Line ridge : ridges){
            int i=0;
            ridgeRakeCluster.get(i).add(ridge.getP1());
            ridgeRakeCluster.get(i).add(ridge.getP2());
            for (Line rake : rakes){
                if (rake.getP1().getId().equals(ridge.getP1().getId()) || rake.getP1().getId().equals(ridge.getP2().getId()) || rake.getP2().getId().equals(ridge.getP1().getId()) || rake.getP2().getId().equals(ridge.getP2().getId())){
                    ridgeRakeCluster.get(i).add(rake.getSetbackCoordinates());
                }
            }
            i++;
        }
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Line> getRidges() {
        return ridges;
    }

    public void setRidges(List<Line> ridges, List<Line> lines) {
        for (Line i : lines){
            if (i.getType().equals("RIDGE")) ridges.add(i);
        }
    }

    public List<Line> getRakes() {
        return rakes;
    }

    public void setRakes(List<Line> rakes, List<Line> lines) {
        for (Line i : lines){
            if (i.getType().equals("RAKE")) rakes.add(i);
        }
    }

    public boolean pointsFormRectangle(Point r1, Point r2, Point s1, Point s2){
        /**
         * Complicated yet efficient way to find if 4 points in 3D space form a rectangle, not just connect randomly.
         *
         * When we pass parameters to this method, r1 and r2 are the RIDGE points, and s1 and s2 are the two setback
         * points. If both s1 and s2 are on one side of the RIDGE, then they form a rectangle, and we can connect
         * s1 and s2 with a line, which will represent the setback area that we are searching for.
         *
         * To find whether 4 points form a rectangle, we find the center of gravity of points in each dimension:
         * cx, cy, cz  <=>  (essentially the center of a rectangle)
         *
         * Then we calculate the square distances from center of mass to all 4 corners: dd1, dd2, dd3, dd4
         *
         * If they are equal, points form a rectangle. We are using float numbers and approximation on the level
         * of point coordinates themselves, so we cannot exactly check if distances are equal - that's why we approximate.
         */
        double cx, cy, cz;
        double dd1, dd2, dd3, dd4;

        cx = (r1.getCoordinateX() + r2.getCoordinateX() + s1.getCoordinateX() + s2.getCoordinateX()) / 4;
        cy = (r1.getCoordinateY() + r2.getCoordinateY() + s1.getCoordinateY() + s2.getCoordinateY()) / 4;
        cz = (r1.getCoordinateZ() + r2.getCoordinateZ() + s1.getCoordinateZ() + s2.getCoordinateZ()) / 4;

        dd1 = (cx - r1.getCoordinateX())*(cx - r1.getCoordinateX()) +
                (cy - r1.getCoordinateY())*(cy - r1.getCoordinateY()) +
                (cz - r1.getCoordinateZ())*(cz - r1.getCoordinateZ());
        dd2 = (cx - r2.getCoordinateX())*(cx - r2.getCoordinateX()) +
                (cy - r2.getCoordinateY())*(cy - r2.getCoordinateY()) +
                (cz - r2.getCoordinateZ())*(cz - r2.getCoordinateZ());
        dd3 = (cx - s1.getCoordinateX())*(cx - s1.getCoordinateX()) +
                (cy - s1.getCoordinateY())*(cy - s1.getCoordinateY()) +
                (cz - s1.getCoordinateZ())*(cz - s1.getCoordinateZ());
        dd4 = (cx - s2.getCoordinateX())*(cx - s2.getCoordinateX()) +
                (cy - s2.getCoordinateY())*(cy - s2.getCoordinateY()) +
                (cz - s2.getCoordinateZ())*(cz - s2.getCoordinateZ());

        if (Math.round(dd1*1000d)/1000d == Math.round(dd2*1000d)/1000d &&
                Math.round(dd1*1000d)/1000d == Math.round(dd3*1000d)/1000d &&
                Math.round(dd1*1000d)/1000d == Math.round(dd4*1000d)/1000d) return true;
        else return false;
    }

    public void findCoplanarVector(Rectangle rectangle,List<Line> lines){
        lines.add(new Line("L"+(lines.size()+1), "SETBACK_LINE", rectangle.getS1(), rectangle.getS2()));
    }

    @Override
    public String toString() {
        return "FormSetbackArea{" +
                ", ridges=" + ridges +
                ", rakes=" + rakes +
                '}';
    }
}
