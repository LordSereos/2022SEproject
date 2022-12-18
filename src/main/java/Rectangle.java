/**
 * An intermediary class for creating object Rectangle, which is needed to form rectangles on the roof using two
 * points of RIDGE r1 r2 and other setback points, that we calculated in Lines class for every RAKE.
 * <p>
 * There can be a lot of combinations between RIDGE and setback points, and if rectangles can be formed, then the
 * combination is correct.
 */
public class Rectangle {
    private Point r1;
    private Point r2;
    private Point s1;
    private Point s2;

    public Rectangle() {
    }

    public Rectangle(Point r1, Point r2, Point s1, Point s2) {
        this.r1 = r1;
        this.r2 = r2;
        this.s1 = s1;
        this.s2 = s2;
    }

    public Point getR1() {
        return r1;
    }

    public void setR1(Point r1) {
        this.r1 = r1;
    }

    public Point getR2() {
        return r2;
    }

    public void setR2(Point r2) {
        this.r2 = r2;
    }

    public Point getS1() {
        return s1;
    }

    public void setS1(Point s1) {
        this.s1 = s1;
    }

    public Point getS2() {
        return s2;
    }

    public void setS2(Point s2) {
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "Rectangles{" +
                "r1=" + r1 +
                ", r2=" + r2 +
                ", s1=" + s1 +
                ", s2=" + s2 +
                '}';
    }
}
