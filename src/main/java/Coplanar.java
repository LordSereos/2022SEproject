


public class Coplanar {
    static boolean equation_plane(double x1, double y1, double z1, double x2, double y2, double z2,
                                  double x3, double y3, double z3, double x, double y, double z) {
        double a1 = x2 - x1;
        double b1 = y2 - y1;
        double c1 = z2 - z1;
        double a2 = x3 - x1;
        double b2 = y3 - y1;
        double c2 = z3 - z1;
        double a = b1 * c2 - b2 * c1;
        double b = a2 * c1 - a1 * c2;
        double c = a1 * b2 - b1 * a2;
        double d = (-a * x1 - b * y1 - c * z1);

        // equation of plane is: a*x + b*y + c*z = 0 #

        // checking if the 4th podouble   satisfies
        // the above equation
        boolean coplanar;
        if (a * x + b * y + c * z + d >= -0.01 && 0.01 >= (a * x + b * y + c * z + d)) {
            coplanar = true;

        } else {

            coplanar = false;

        }
        return coplanar;
    }
}