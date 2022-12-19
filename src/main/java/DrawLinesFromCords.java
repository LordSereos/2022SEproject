import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

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


    /*Lines from buttons are stored inside LinkedList*/
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
        int h = getWidth()-80;
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

        for (Line2Draw line : Line2Draw) {
            g2d.setStroke(new BasicStroke(3));

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); /*Anialiasing ON*/


            g2d.setPaint(line.Color);
            g2d.draw(new Line2D.Double(
                    8+(line.x1 * scale) + minX * scale,
                    8+(line.y1 * scale) + minY * scale,
                    8+(line.x2 * scale) + minX * scale,
                    8+(line.y2 * scale) + minY * scale)
            );
            /*Draws Shape of Lines*/


        }

    }
    /*Adds lines to list */

    public void addLine(double x1, double y1, double x2, double y2, Color Color) {
        Line2Draw.add(new Line2Draw(x1, y1, x2, y2, Color));

        repaint();

    }


    public static void getSaveSnapShot(Component component, String fileName) throws Exception {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_BGR);
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage croppedImage = image.getSubimage(0, 0, width-210, height-40);
        // paints into image's Graphics
        component.paint(croppedImage.getGraphics());
        // write the captured image as a PNG
        ImageIO.write(croppedImage, "png", new File(fileName));
    }

    public static void SaveImgFromButton(String view, JFrame frame){
        if(view != "") {
            String img = "./SolarPanel/Images/" + view + ".png";
            try {
                getSaveSnapShot(frame.getContentPane(), img);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else {
            System.out.println("Current View is empty!");
        }
    }

    public void Launch(List<Line> coordlist, List<Point> zoneOne, List<Point> zoneTwo, List<Point> zoneThree) throws Exception{


        JFrame frame = new JFrame();                            /*Initializes JFrame*/
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final DrawLinesFromCords comp = new DrawLinesFromCords();
        comp.setPreferredSize(new Dimension(600, 400));
        frame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();                         /*Initializes Jpanel*/
        JButton drawRoofWithFireEscape = new JButton("Roof");
        JButton clearButton = new JButton("Clear");
        JButton drawRoofWithWindZones = new JButton("Draw roof with wind zones");
        JButton drawLayout = new JButton("Draw roof layout");
        JButton saveImgButton = new JButton("Save Images");
        JButton formatPdfButton = new JButton("Format Pdf");
        buttonsPanel.add(drawRoofWithFireEscape);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(drawRoofWithWindZones);
        buttonsPanel.add(drawLayout);
        buttonsPanel.add(saveImgButton);
        buttonsPanel.add(formatPdfButton);
        JPanel checkBoxesPanel = new JPanel();
        checkBoxesPanel.setLayout(new BoxLayout(checkBoxesPanel, BoxLayout.Y_AXIS));
        checkBoxesPanel.setSize(200, 400);
        JCheckBox roofLayoutBox = new JCheckBox("Layout");
        JCheckBox roofFireEscapeBox = new JCheckBox("Fire Ventilation");
        JCheckBox roofWindZonesBox = new JCheckBox("Wind Zones");
        JCheckBox roofFireEscapeWindZoneBox = new JCheckBox("Fire Ventilation and Wind Zones");
        checkBoxesPanel.add(new JLabel("What Images do you want to save?"));
        checkBoxesPanel.add(roofLayoutBox);
        checkBoxesPanel.add(roofFireEscapeBox);
        checkBoxesPanel.add(roofWindZonesBox);
        checkBoxesPanel.add(roofFireEscapeWindZoneBox);
        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);   /*Add buttonPanel to Jframe*/
        frame.getContentPane().add(checkBoxesPanel, BorderLayout.EAST);   /*Add checkBoxesPanel to Jframe*/

        ReadFromJSON data = new ReadFromJSON();
        data.readPoints("./JSON_files/Data.json");

        GetFaces faces = new GetFaces();

        List<Line> linesOfFaces = new ArrayList<>();

        linesOfFaces.addAll(faces.FacesFromCoords(data));

        final String[] currView = {"empty"};
        final String[] allView = {"Roof"};

        drawRoofWithFireEscape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currView[0] == "Layout")
                {
                    comp.clearLines();
                    allView[0] = "Roof";
                }
                if (currView[0] == "empty")
                {
                    allView[0] = "Roof";
                }
                currView[0] = ", Fire Ventilation Setbacks";
                allView[0] += currView[0];

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

            }
        });
        /*Clear lines */
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currView[0] = "empty";
                allView[0] = "Roof";
                comp.clearLines();
            }
        });
        drawRoofWithWindZones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currView[0] == "Layout")
                {
                    comp.clearLines();
                    allView[0] = "Roof";
                }
                if (currView[0] == "empty")
                {
                    allView[0] = "Roof";
                }
                currView[0] = ", Wind Zones";
                allView[0] += currView[0];

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
                    for (int i = 0; i< zoneTwo.size(); i = i + 4) {
                        if (i + 6 < zoneTwo.size()) {
                            comp.addLine(
                                    zoneTwo.get(i).getCoordinateX(),
                                    zoneTwo.get(i).getCoordinateY(),
                                    zoneTwo.get(i + 6).getCoordinateX(),
                                    zoneTwo.get(i + 6).getCoordinateY(),
                                    Color.YELLOW);

                        }
                        if (i + 4 < zoneTwo.size())
                            comp.addLine(
                                    zoneTwo.get(i + 1).getCoordinateX(),
                                    zoneTwo.get(i + 1).getCoordinateY(),
                                    zoneTwo.get(i + 4).getCoordinateX(),
                                    zoneTwo.get(i + 4).getCoordinateY(),
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

        drawLayout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currView[0] = "Layout";
                allView[0] = "Roof " + currView[0];
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

        // Save Image
        saveImgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearButton.doClick();
                if(roofLayoutBox.isSelected()) {
                    clearButton.doClick();
                    drawLayout.doClick();
                    SaveImgFromButton(allView[0], frame);
                }

                if(roofFireEscapeBox.isSelected()) {
                    clearButton.doClick();
                    drawRoofWithFireEscape.doClick();
                    SaveImgFromButton(allView[0], frame);
                }

                if(roofFireEscapeWindZoneBox.isSelected()) {
                    clearButton.doClick();
                    drawRoofWithFireEscape.doClick();
                    drawRoofWithWindZones.doClick();
                    SaveImgFromButton(allView[0], frame);
                }

                if(roofWindZonesBox.isSelected()) {
                    clearButton.doClick();
                    drawRoofWithWindZones.doClick();
                    SaveImgFromButton(allView[0], frame);
                }
            }
        });

        FormatPdf formatPdf = new FormatPdf();
        final String[] dialogMessage = {new String()};
        formatPdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogMessage[0] = formatPdf.PrintPdf();
                JOptionPane.showMessageDialog(frame, dialogMessage[0], "Info",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

}