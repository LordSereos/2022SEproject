import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;


public class DrawLinesFromCords extends JComponent {

    private List<Line2Draw> lines;

    private static class Line2Draw {
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
        double minX=99999;
        double minY=99999;

        double maxx= -99999;
        double maxy= -99999;
        double max=0;
        Color DefaultColor= java.awt.Color.BLACK;
        int h= getWidth();
        int w= getHeight();

        for (Line2Draw line : Line2Draw) {
                if (minX > line.x1) {minX = line.x1;}
                if (minY > line.y1) {minY = line.y1;}
                if (minX > line.x2) {minX = line.x2;}
                if (minY > line.y2) {minY = line.y2;}
                
                if (maxx < line.x1) {maxx = line.x1;}
                if (maxy < line.y1) {maxy = line.y1;}
                if (maxx < line.x2) {maxx = line.x2;}
                if (maxy < line.y2) {maxy = line.y2;}

        }


            if(minX<0){minX= minX * -1;}
            if(minY<0){minY= minY * -1;}
            if(maxx<0){maxx= maxx * -1;}
            if(maxy<0){maxy= maxy * -1;}

            double scale;

            if(maxy<maxx){
                max=maxx;
            }
            else{
                max=maxy;
            }
            if(w>h){scale=h/max;}
            else{scale=w/max;}


            if (scale>0){scale=scale-1;}

            for (Line2Draw line : Line2Draw) {
            g2d.setStroke(new BasicStroke(3));

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); /*Anialiasing ON*/


                g2d.setPaint(line.Color);
            g2d.draw(new Line2D.Double(
                    (line.x1 * scale)+ minX*scale,
                    (line.y1 * scale)+ minY*scale,
                    (line.x2 * scale)+ minX*scale,
                    (line.y2 * scale)+ minY*scale)
            );
            /*Draws Shape of Lines*/




        
        }

    }
  /*Adds lines to list */

    public void addLine(double x1, double y1, double x2, double y2, Color Color) {
        Line2Draw.add(new Line2Draw(x1, y1, x2, y2, Color));

        repaint();

    }


    public void Launch(List<Line> coordlist) {

        System.out.println(coordlist.get(0));

        JFrame testFrame = new JFrame();                            /*Initializes JFrame*/
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final DrawLinesFromCords comp = new DrawLinesFromCords();
        comp.setPreferredSize(new Dimension(600, 400));
        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();                         /*Initializes Jpanel*/
        JButton newLineButton = new JButton("Draw from list");
        JButton clearButton = new JButton("Clear");
        JButton drawroof = new JButton("Draw roof from json");
        buttonsPanel.add(newLineButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(drawroof);
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);   /*Add buttonPanel to Jframe*/



        newLineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {



                for (Line i : coordlist){

                    comp.addLine(i.getP1().getCoordinateX(), i.getP1().getCoordinateY(), i.getP2().getCoordinateX(), i.getP2().getCoordinateY(), Color.BLACK);


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


                ReadFromJSON data = new ReadFromJSON();
                data.readPoints("./JSON_files/Data.json");

                for (int i = 0; i <data.getInfo().getRoofs().size(); i++) {

                    comp.addLine(
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(0).getCoordinateY(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateX(),
                            data.getInfo().getRoofs().get(i).getPoints().get(1).getCoordinateY(),
                            Color.GREEN );

                }
            }
        });




        testFrame.pack();
        testFrame.setVisible(true);
    }

}