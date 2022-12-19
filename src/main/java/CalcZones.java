import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class CalcZones {
    MeanRoofHeight mrh = new MeanRoofHeight();
    Point pnt = new Point();
    DecimalFormat df = new DecimalFormat("#.###");

    public Point calc_vector_coordn(Point p1, Point p2) {
        Point v = new Point();
        v.setCoordinateX(p2.getCoordinateX() - p1.getCoordinateX());
        v.setCoordinateY(p2.getCoordinateY() - p1.getCoordinateY());
        v.setCoordinateZ(p2.getCoordinateZ() - p1.getCoordinateZ());
        return v;
    }

    public double calc_corner_degrees(Point com_point, Edge e1, Edge e2) {
        Point v1 = calc_vector_coordn(com_point, find_end_point(com_point, e1));
        Point v2 = calc_vector_coordn(com_point, find_end_point(com_point, e2));
        double acos = Math.acos(((v1.getCoordinateX() * v2.getCoordinateX() + v1.getCoordinateY() * v2.getCoordinateY() +
                v1.getCoordinateZ() * v2.getCoordinateZ())) / (pnt.calculateDistance(v1) * pnt.calculateDistance(v2)));
        return Math.toDegrees(acos);
    }

    public Point calc_point(Point p1, Point p2, double width) {
        Point p = new Point();
        p.setCoordinateX(p1.getCoordinateX() + ((p2.getCoordinateX() - p1.getCoordinateX()) * width / pnt.calculateDistance(calc_vector_coordn(p1, p2))));
        p.setCoordinateY(p1.getCoordinateY() + ((p2.getCoordinateY() - p1.getCoordinateY()) * width / pnt.calculateDistance(calc_vector_coordn(p1, p2))));
        p.setCoordinateZ(p1.getCoordinateZ() + ((p2.getCoordinateZ() - p1.getCoordinateZ()) * width / pnt.calculateDistance(calc_vector_coordn(p1, p2))));
        return p;
    }

    public Point calc_midpoint(Point p1, Point p2) {
        Point p = new Point();
        p.setCoordinateX(p1.getCoordinateX() + (p2.getCoordinateX() - p1.getCoordinateX()) / 2);
        p.setCoordinateY(p1.getCoordinateY() + (p2.getCoordinateY() - p1.getCoordinateY()) / 2);
        p.setCoordinateZ(p1.getCoordinateZ() + (p2.getCoordinateZ() - p1.getCoordinateZ()) / 2);
        return p;
    }

    public Point find_end_point(Point common_point, Edge e) {
        Point end_point = new Point();
        if (!common_point.getId().equals(e.getPoints().get(0).getId())) end_point = e.getPoints().get(0);
        if (!common_point.getId().equals(e.getPoints().get(1).getId())) end_point = e.getPoints().get(1);
        return end_point;
    }

    public Point find_common_point(Edge e1, Edge e2) {
        Point common_point = new Point();
        for (Point p1 : e1.getPoints()) {
            for (Point p2 : e2.getPoints()) {
                if (p1.getId().equals(p2.getId()))
                    common_point = p1;
            }
        }
        return common_point;
    }

    public Point calc_wind_zone_corner_point(Point p1, Point midpoint) {
        Point p = new Point();
        p.setCoordinateX(midpoint.getCoordinateX() * 2 - p1.getCoordinateX());
        p.setCoordinateY(midpoint.getCoordinateY() * 2 - p1.getCoordinateY());
        p.setCoordinateZ(midpoint.getCoordinateZ() * 2 - p1.getCoordinateZ());
        return p;
    }

    public Point calc_third_zone_point(Point g, Point b, Point c, double gl) {
        Point l = new Point();
        l.setCoordinateZ(g.getCoordinateZ());
        l.setCoordinateX(g.getCoordinateX() + (c.getCoordinateX() - b.getCoordinateX()) * gl / pnt.calculateDistance(calc_vector_coordn(b, c)));
        l.setCoordinateY(g.getCoordinateY() + (c.getCoordinateY() - b.getCoordinateY()) * gl / pnt.calculateDistance(calc_vector_coordn(b, c)));
        return l;
    }

    public void find_first_zone_points(double width, Point com_point, Edge e1, Edge e2, ArrayList<Point> first_zone) {
        double corner_degree = (double) Math.round(calc_corner_degrees(com_point, e1, e2) * 1000) / 1000;
        if (corner_degree <= 90) {
            double ad = Math.sqrt(Math.pow(((Math.sin(Math.PI / 180 * (90 - corner_degree)) * width) /
                    Math.sin(Math.PI / 180 * corner_degree)), 2) + Math.pow(width, 2));
            Point end_point1 = find_end_point(com_point, e1);
            Point end_point2 = find_end_point(com_point, e2);
            Point d = calc_point(com_point, end_point1, ad);
            Point e = calc_point(com_point, end_point2, ad);
            Point midpoint = calc_midpoint(d, e);
            Point first_zone_corner_point = calc_wind_zone_corner_point(com_point, midpoint);
            first_zone_corner_point.setId(e1.getId() + e2.getId());
            first_zone.add(first_zone_corner_point);
        }
        if (corner_degree > 90 && !(e1.getType().equals("RIDGE") && e2.getType().equals("VALLEY") || e2.getType().equals("RIDGE") && e1.getType().equals("VALLEY"))) {
            double af = Math.sqrt(Math.pow(((Math.sin(Math.PI / 180 * (corner_degree - 90)) * width) /
                    Math.sin(Math.PI / 180 * (90 - (corner_degree - 90)))), 2) + Math.pow(width, 2));
            Point back_point1 = find_end_point(com_point, e1);
            Point back_point2 = find_end_point(com_point, e2);
            Point e = calc_point(com_point, back_point1, af);
            Point f = calc_point(com_point, back_point2, af);
            Point midpoint = calc_midpoint(e, f);
            Point first_zone_point = calc_wind_zone_corner_point(com_point, midpoint);
            first_zone_point.setId(e1.getId() + e2.getId());
            first_zone.add(first_zone_point);
        }
    }

    public void find_third_zone_points(double zone_width, Point com_point, Edge e1, Edge e2, ArrayList<Point> third_zone) {
        if (!(e1.getType().equals("RIDGE") && e2.getType().equals("HIP") ||
                (e1.getType().equals("VALLEY") || e2.getType().equals("VALLEY")) ||
                e2.getType().equals("RIDGE") && e1.getType().equals("HIP") ||
                e1.getType().equals(e2.getType()))) {
            Point end_point1 = find_end_point(com_point, e1);
            Point end_point2 = find_end_point(com_point, e2);
            double corner_degree = (double) Math.round(calc_corner_degrees(com_point, e1, e2) * 1000) / 1000;
            if (corner_degree > 45 && corner_degree <= 90) {
                double bg = Math.sqrt(Math.pow(((Math.sin(Math.PI / 180 * (90 - corner_degree)) * zone_width) /
                        Math.sin(Math.PI / 180 * corner_degree)), 2) + Math.pow(zone_width, 2));
                double gl = zone_width - (Math.sin(Math.PI / 180 * (90 - corner_degree)) * zone_width) / Math.sin(Math.PI / 180 * corner_degree);
                if (e1.getType().equals("HIP") || e1.getType().equals("RAKE")) {
                    Point g = calc_point(com_point, end_point1, bg);
                    Point h = calc_point(com_point, end_point2, zone_width);
                    Point l = calc_third_zone_point(g, com_point, end_point2, gl);
                    g.setId(e1.getId() + e2.getId() + "-T1");
                    third_zone.add(g);
                    h.setId(e1.getId() + e2.getId() + "-T2");
                    third_zone.add(h);
                    l.setId(e1.getId() + e2.getId() + "-T3");
                    third_zone.add(l);
                } else {
                    Point g = calc_point(com_point, end_point2, bg);
                    Point h = calc_point(com_point, end_point1, zone_width);
                    Point l = calc_third_zone_point(g, com_point, end_point1, gl);
                    g.setId(e1.getId() + e2.getId() + "-T1");
                    third_zone.add(g);
                    h.setId(e1.getId() + e2.getId() + "-T2");
                    third_zone.add(h);
                    l.setId(e1.getId() + e2.getId() + "-T3");
                    third_zone.add(l);
                }
                third_zone.add(com_point);
            }
            if (corner_degree <= 45) {
                double ag = sqrt(Math.pow(((Math.sin(Math.PI / 180 * corner_degree) * zone_width) /
                        Math.sin(Math.PI / 180 * (90 - corner_degree))), 2) + Math.pow(zone_width, 2));
                if (e1.getType().equals("HIP") || e1.getType().equals("RAKE")) {
                    Point g = calc_point(com_point, end_point1, ag);
                    Point h = calc_point(com_point, end_point2, zone_width);
                    g.setId(e1.getId() + e2.getId() + "-T1");
                    third_zone.add(g);
                    h.setId(e1.getId() + e2.getId() + "-T2");
                    third_zone.add(h);
                } else {
                    Point g = calc_point(com_point, end_point2, ag);
                    Point h = calc_point(com_point, end_point1, zone_width);
                    g.setId(e1.getId() + e2.getId() + "-T1");
                    third_zone.add(g);
                    h.setId(e1.getId() + e2.getId() + "-T2");
                    third_zone.add(h);
                }
                third_zone.add(com_point);
            }
        }

    }

    public void calc_zones(ArrayList<Edge> roof_edges, ArrayList<Point> first_zone, ArrayList<Point> third_zone) {
        double zone_width = mrh.getWidthOfWindZone(roof_edges);
        for (int i = 0; i < roof_edges.size() - 1; i++) {
            for (int j = i + 1; j < roof_edges.size(); j++) {
                Point com_point = find_common_point(roof_edges.get(i), roof_edges.get(j));
                if (com_point.getId() != null && (!roof_edges.get(i).getType().equals(roof_edges.get(j).getType()) || roof_edges.get(j).getType().equals("HIP") &&
                        roof_edges.get(i).getType().equals("HIP"))) {
                    find_first_zone_points(zone_width, com_point, roof_edges.get(i), roof_edges.get(j), first_zone);
                    find_third_zone_points(zone_width, com_point, roof_edges.get(i), roof_edges.get(j), third_zone);
                }
            }
        }
    }

    public void print_zone(ArrayList<Point> zone) {
        for (Point p : zone) {
            System.out.println(p.toString());
        }
    }
}