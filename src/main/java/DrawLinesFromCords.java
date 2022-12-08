import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;


//TO DO import Coordinates of lines from JSON

public class DrawLinesFromCords extends JComponent {

    private static class Line {
        final double x1;
        final double y1;
        final double x2;
        final double y2;

        public Line(double x1, double y1, double x2, double y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /*Lines from buttons is stored inside LinkedList*/
    private final LinkedList<Line2D.Double> lines = new LinkedList<Line2D.Double>();

    public void clearLines() {
        lines.clear();
        repaint();
        /*Method for clearing lines from list*/
    }


    @Override
    protected void paintComponent(Graphics g) {
        /*Main part which draws lines on JFrame from lines list*/
        int tempScale = 5; /*Temporary scale up*/
        int tempoffscale= 100;
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");

        //Find smallest x, y, z
        double minX = data.getInfo().getRoofs().get(0).getPoints().get(0).getCoordinateX();
        double minY = data.getInfo().getRoofs().get(0).getPoints().get(0).getCoordinateY();
        double minZ = data.getInfo().getRoofs().get(0).getPoints().get(0).getCoordinateY();
        
        for (int n = 0; n<2; n++) {
            for (int i = 0; i < data.getInfo().getRoofs().size(); i++) {


                if (minX > data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateX()) {
                    minX = data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateX();
                }

                if (minY > data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateY()) {
                    minY = data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateY();
                }

                if (minZ > data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateY()) {
                    minZ = data.getInfo().getRoofs().get(i).getPoints().get(n).getCoordinateY();

                }
            }
        }
        if(minX<0){minX= minX * -1;}
        if(minY<0){minY= minY * -1;}
        if(minZ<0){minZ= minZ * -1;}


        System.out.println(minX*tempScale);
        System.out.println(minY*tempScale);

        for (Line2D.Double line : lines) {
            g2d.setStroke(new BasicStroke(3));    /*Width of line*/
            g2d.setPaint(Color.BLACK);                  /*Color of line*/
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); /*Anialiasing ON*/
            g2d.draw(new Line2D.Double(
                    (line.x1 * tempScale)+ minX*tempScale,
                    (line.y1 * tempScale)+ minY*tempScale,
                    (line.x2 * tempScale) + minX*tempScale,
                    (line.y2 * tempScale)+ minY*tempScale));
            /*Draws Shape of Lines*/

        }
    }

    /*Adds lines to list */
    public void addLine(double x1, double y1, double x2, double y2) {
        lines.add(new Line2D.Double(x1, y1, x2, y2));
        repaint();

    }


    public void Launch(String[] args) {



        JFrame testFrame = new JFrame();                            /*Initializes JFrame*/
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final DrawLinesFromCords comp = new DrawLinesFromCords();
        comp.setPreferredSize(new Dimension(600, 400));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();                         /*Initializes Jpanel*/
        JButton newLineButton = new JButton("Draw rectangle");
        JButton clearButton = new JButton("Clear");
        JButton drawroof = new JButton("Draw roof");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(drawroof);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);   /*Add buttonPanel to Jframe*/



        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                /*coordinate of point1 x*/
                double[] arrx1 = new double[]{10.253362000016494, 10.049109839983284, 12.189552560006268, 12.393804720039478, 12.602265120041556};
                /*coordinate of point2 x*/
                double[] arrx2 = new double[]{10.049109839983284, 12.189552560006268, 12.393804720039478, 10.253362000016494, 12.398012960008344};
                /*coordinate of point1 y*/
                double[] arry1 = new double[]{8.299623438715935, 6.6723924789577715, 6.403721119835972, 8.030952079594135, 27.012781117856502};
                /*coordinate of point2 y*/
                double[] arry2 = new double[]{6.6723924789577715, 6.403721119835972, 8.030952079594135, 8.299623438715935, 25.38555015809834};






                /*for loops for the length of arrx1*/
                for (int i = 0; i < arrx1.length; i++) {

                    double x1 = (arrx1[i]);
                    double x2 = (arrx2[i]);
                    double y1 = (arry1[i]);
                    double y2 = (arry2[i]);
                    comp.addLine(x1, y1, x2, y2);


                }
            }
        });
        /*Clear lines */
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearLines();
            }
        });
        drawroof.addActionListener(new ActionListener() {

            /*Same as newline*/
            @Override
            public void actionPerformed(ActionEvent e) {
                //Negative coordinates go out of bounds
                //Need to offset(arr[]+offset)

                ReadFromJSON data = new ReadFromJSON();
                data.readPoints("./JSON_files/Data.json");

                for (int i = 0; i <data.getInfo().getRoofs().size(); i++) {

                    comp.addLine(
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateY(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateY());
                }
            }
        });

        testFrame.pack();
        testFrame.setVisible(true);
    }

}