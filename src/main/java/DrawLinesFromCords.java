import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DrawLinesFromCords extends JComponent {

    public List<Line2Draw> lines;

    public static class Line2Draw {
        final double x1;
        final double y1;
        final double x2;
        final double y2;
        final Color Color;

        public Line2Draw(double x1, double y1, double x2, double y2, Color Color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.Color = Color;

        }
    }


    /*Lines from buttons is stored inside LinkedList*/
    private final LinkedList<Line2Draw> Line2Draw = new LinkedList<Line2Draw>();


    public void clearLines() {
        Line2Draw.clear();
        repaint();
        /*Method for clearing lines from list*/
    }


    @Override
    protected void paintComponent(Graphics g) {
        /*Main part which draws lines on JFrame from lines list*/

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");
        RectangleCoordinates roof = new RectangleCoordinates();

        //Find smallest x, y, z
        double minX = 99999;
        double minY = 99999;

        double MAXX = -99999;
        double MAXY = -99999;
        double max = 0;
        int h = getWidth();
        int w = getHeight();

        for (Line2Draw line : Line2Draw) {
            if (minX > line.x1) {
                minX = line.x1;
            }
            if (minY > line.y1) {
                minY = line.y1;
            }
            if (minX > line.x2) {
                minX = line.x2;
            }
            if (minY > line.y2) {
                minY = line.y2;
            }

            if (MAXX < line.x1) {
                MAXX = line.x1;
            }
            if (MAXY < line.y1) {
                MAXY = line.y1;
            }
            if (MAXX < line.x2) {
                MAXX = line.x2;
            }
            if (MAXY < line.y2) {
                MAXY = line.y2;
            }

        }


        if (minX < 0) {
            minX = minX * -1;
        }
        if (minY < 0) {
            minY = minY * -1;
        }
        if (MAXX < 0) {
            MAXX = MAXX * -1;
        }
        if (MAXY < 0) {
            MAXY = MAXY * -1;
        }

        double scale;


        if (MAXX / w < MAXY / h) {
            scale = w / MAXY;
        } else {
            scale = h / MAXX;
        }


        if (scale > 0) {
            scale = scale - 1;
        }
        System.out.println(scale);
        System.out.println(w / MAXX);
        System.out.println(h / MAXY);
        for (Line2Draw line : Line2Draw) {
            g2d.setStroke(new BasicStroke(3));

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); /*Anialiasing ON*/


            g2d.setPaint(line.Color);
            g2d.draw(new Line2D.Double(
                    (line.x1 * scale) + minX * scale,
                    (line.y1 * scale) + minY * scale,
                    (line.x2 * scale) + minX * scale,
                    (line.y2 * scale) + minY * scale)
            );
            /*Draws Shape of Lines*/


        }

    }
    /*Adds lines to list */

    public void addLine(double x1, double y1, double x2, double y2, Color Color) {
        Line2Draw.add(new Line2Draw(x1, y1, x2, y2, Color));

        repaint();

    }


    public void Launch(List<Line> coordlist, List<Point> zoneOne, List<Point> zoneTwo, List<Point> zoneThree) {


        JFrame testFrame = new JFrame();                            /*Initializes JFrame*/
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final DrawLinesFromCords comp = new DrawLinesFromCords();
        comp.setPreferredSize(new Dimension(600, 400));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();                         /*Initializes Jpanel*/
        JButton newLineButton = new JButton("Roof");
        JButton clearButton = new JButton("Clear");
        JButton drawroof = new JButton("Draw roof with wind zones");
        JButton drawlayout = new JButton("Draw roof layout");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(drawroof);
        buttonsPanel.add(drawlayout);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);   /*Add buttonPanel to Jframe*/


        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");

        GetFaces faces = new GetFaces();

        List<Line> linesOfFaces = new ArrayList<>();

        linesOfFaces.addAll(faces.FacesFromCoords(data));

        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                for (Line i : coordlist) {

                    comp.addLine(i.getP1().getCoordinateX(), i.getP1().getCoordinateY(), i.getP2().getCoordinateX(), i.getP2().getCoordinateY(),
                            Color.BLUE);


                }
                for (int n = 0; n < data.getInfo().getRoofs().size(); n++) {

                    comp.addLine(
                            data.getInfo().getRoofs().get(n).getPoints().get(0).getCoordinateX(),
                            data.getInfo().getRoofs().get(n).getPoints().get(0).getCoordinateY(),
                            data.getInfo().getRoofs().get(n).getPoints().get(1).getCoordinateX(),
                            data.getInfo().getRoofs().get(n).getPoints().get(1).getCoordinateY(),
                            Color.BLACK);


                }

                for (int n = 0; n < linesOfFaces.size(); n++) {


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


                for (int i = 0; i < zoneOne.size(); i = i + 4) {

                    comp.addLine(
                            zoneOne.get(i).getCoordinateX(),
                            zoneOne.get(i).getCoordinateY(),
                            zoneOne.get(i + 1).getCoordinateX(),
                            zoneOne.get(i + 1).getCoordinateY(),
                            Color.GREEN);
                    comp.addLine(
                            zoneOne.get(i + 1).getCoordinateX(),
                            zoneOne.get(i + 1).getCoordinateY(),
                            zoneOne.get(i + 2).getCoordinateX(),
                            zoneOne.get(i + 2).getCoordinateY(),
                            Color.GREEN);
                    comp.addLine(
                            zoneOne.get(i + 2).getCoordinateX(),
                            zoneOne.get(i + 2).getCoordinateY(),
                            zoneOne.get(i + 3).getCoordinateX(),
                            zoneOne.get(i + 3).getCoordinateY(),
                            Color.GREEN);
                    comp.addLine(
                            zoneOne.get(i + 3).getCoordinateX(),
                            zoneOne.get(i + 3).getCoordinateY(),
                            zoneOne.get(i).getCoordinateX(),
                            zoneOne.get(i).getCoordinateY(),
                            Color.GREEN);
                    comp.addLine(
                            zoneOne.get(i + 1).getCoordinateX(),
                            zoneOne.get(i + 1).getCoordinateY(),
                            zoneOne.get(i + 3).getCoordinateX(),
                            zoneOne.get(i + 3).getCoordinateY(),
                            Color.GREEN);
                    comp.addLine(
                            zoneOne.get(i + 2).getCoordinateX(),
                            zoneOne.get(i + 2).getCoordinateY(),
                            zoneOne.get(i).getCoordinateX(),
                            zoneOne.get(i).getCoordinateY(),
                            Color.GREEN);


                }
                for (int i = 0; i < zoneTwo.size(); i = i + 4) {

                    comp.addLine(
                            zoneTwo.get(i).getCoordinateX(),
                            zoneTwo.get(i).getCoordinateY(),
                            zoneTwo.get(i + 1).getCoordinateX(),
                            zoneTwo.get(i + 1).getCoordinateY(),
                            Color.YELLOW);
                    comp.addLine(
                            zoneTwo.get(i + 1).getCoordinateX(),
                            zoneTwo.get(i + 1).getCoordinateY(),
                            zoneTwo.get(i + 2).getCoordinateX(),
                            zoneTwo.get(i + 2).getCoordinateY(),
                            Color.YELLOW);
                    comp.addLine(
                            zoneTwo.get(i + 2).getCoordinateX(),
                            zoneTwo.get(i + 2).getCoordinateY(),
                            zoneTwo.get(i + 3).getCoordinateX(),
                            zoneTwo.get(i + 3).getCoordinateY(),
                            Color.YELLOW);
                    comp.addLine(
                            zoneTwo.get(i + 3).getCoordinateX(),
                            zoneTwo.get(i + 3).getCoordinateY(),
                            zoneTwo.get(i).getCoordinateX(),
                            zoneTwo.get(i).getCoordinateY(),
                            Color.YELLOW);
                    comp.addLine(
                            zoneTwo.get(i + 1).getCoordinateX(),
                            zoneTwo.get(i + 1).getCoordinateY(),
                            zoneTwo.get(i + 3).getCoordinateX(),
                            zoneTwo.get(i + 3).getCoordinateY(),
                            Color.YELLOW);
                    comp.addLine(
                            zoneTwo.get(i + 2).getCoordinateX(),
                            zoneTwo.get(i + 2).getCoordinateY(),
                            zoneTwo.get(i).getCoordinateX(),
                            zoneTwo.get(i).getCoordinateY(),
                            Color.YELLOW);
                }

                for (int i = 0; i < zoneThree.size(); i = i + 4) {

                    comp.addLine(
                            zoneThree.get(i).getCoordinateX(),
                            zoneThree.get(i).getCoordinateY(),
                            zoneThree.get(i + 1).getCoordinateX(),
                            zoneThree.get(i + 1).getCoordinateY(),
                            Color.RED);
                    comp.addLine(
                            zoneThree.get(i + 1).getCoordinateX(),
                            zoneThree.get(i + 1).getCoordinateY(),
                            zoneThree.get(i + 2).getCoordinateX(),
                            zoneThree.get(i + 2).getCoordinateY(),
                            Color.RED);
                    comp.addLine(
                            zoneThree.get(i + 2).getCoordinateX(),
                            zoneThree.get(i + 2).getCoordinateY(),
                            zoneThree.get(i + 3).getCoordinateX(),
                            zoneThree.get(i + 3).getCoordinateY(),
                            Color.RED);
                    comp.addLine(
                            zoneThree.get(i + 3).getCoordinateX(),
                            zoneThree.get(i + 3).getCoordinateY(),
                            zoneThree.get(i).getCoordinateX(),
                            zoneThree.get(i).getCoordinateY(),
                            Color.RED);
                    comp.addLine(
                            zoneThree.get(i + 1).getCoordinateX(),
                            zoneThree.get(i + 1).getCoordinateY(),
                            zoneThree.get(i + 3).getCoordinateX(),
                            zoneThree.get(i + 3).getCoordinateY(),
                            Color.RED);
                    comp.addLine(
                            zoneThree.get(i + 2).getCoordinateX(),
                            zoneThree.get(i + 2).getCoordinateY(),
                            zoneThree.get(i).getCoordinateX(),
                            zoneThree.get(i).getCoordinateY(),
                            Color.RED);
                }

                for (int i = 0; i < data.getInfo().getRoofs().size(); i++) {

                    comp.addLine(
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateY(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateY(),
                            Color.BLACK);


                }
            }

        });

        drawlayout.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                comp.clearLines();
                double maxyVal1 = 0;
                double maxyVal2 = 0;
                double maxyVal = 0;

                for (int i = 0; i < data.getInfo().getRoofs().size(); i++) {

                    if (maxyVal1 < data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateY()) {
                        maxyVal1 = data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateY();
                    }
                    if (maxyVal2 < data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateY()) {
                        maxyVal2 = data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateY();
                    }
                }
                if (maxyVal1 < maxyVal2) {
                    maxyVal = maxyVal2;
                } else {
                    maxyVal = maxyVal1;
                }

                for (int i = 0; i < linesOfFaces.size(); i++) {


                    if (i > 1 && i % 3 == 0) {

                        for (int n = 0; n < coordlist.size(); n++) {

                            if (((Coplanar.equation_plane(
                                    linesOfFaces.get(i).getP1().getCoordinateX(),
                                    linesOfFaces.get(i).getP1().getCoordinateY(),
                                    linesOfFaces.get(i).getP1().getCoordinateZ(),
                                    linesOfFaces.get(i - 1).getP1().getCoordinateX(),
                                    linesOfFaces.get(i - 1).getP1().getCoordinateY(),
                                    linesOfFaces.get(i - 1).getP1().getCoordinateZ(),
                                    linesOfFaces.get(i - 2).getP2().getCoordinateX(),
                                    linesOfFaces.get(i - 2).getP2().getCoordinateY(),
                                    linesOfFaces.get(i - 2).getP2().getCoordinateZ(),
                                    coordlist.get(n).getP1().getCoordinateX(),
                                    coordlist.get(n).getP1().getCoordinateY(),
                                    coordlist.get(n).getP1().getCoordinateZ()
                            ))
                                    == Coplanar.equation_plane(
                                    linesOfFaces.get(i).getP2().getCoordinateX(),
                                    linesOfFaces.get(i).getP2().getCoordinateY(),
                                    linesOfFaces.get(i).getP2().getCoordinateZ(),
                                    linesOfFaces.get(i - 1).getP2().getCoordinateX(),
                                    linesOfFaces.get(i - 1).getP2().getCoordinateY(),
                                    linesOfFaces.get(i - 1).getP2().getCoordinateZ(),
                                    linesOfFaces.get(i - 2).getP1().getCoordinateX(),
                                    linesOfFaces.get(i - 2).getP1().getCoordinateY(),
                                    linesOfFaces.get(i - 2).getP1().getCoordinateZ(),
                                    coordlist.get(n).getP2().getCoordinateX(),
                                    coordlist.get(n).getP2().getCoordinateY(),
                                    coordlist.get(n).getP2().getCoordinateZ()
                            ))) {
                                comp.addLine(
                                        coordlist.get(n).getP1().getCoordinateY() + (maxyVal * (i / 4)),
                                        coordlist.get(n).getP1().getCoordinateZ() * -1,
                                        coordlist.get(n).getP2().getCoordinateY() + (maxyVal * (i / 4)),
                                        coordlist.get(n).getP2().getCoordinateZ() * -1,
                                        Color.BLACK
                                );

                            }


                        }


                    }


                }
            }
        });


        testFrame.pack();
        testFrame.setVisible(true);
    }

}