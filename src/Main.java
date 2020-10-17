import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Frame {

    double width = 800;
    ArrayList<Point2D.Double> list = new ArrayList<>();


    public void points() {
        int numPoint = 400;
        for (int i = 0; i < numPoint; i++) {
            int min = 23;
            double max = 23 + width;
            Random rand = new Random();
            double x = Math.random() * width;
            double y = Math.random() * (max - min + 1) + min;
            Point2D.Double temp = new Point2D.Double(x, y);
            list.add(temp);
        }
    }


    public void paint(Graphics g) {
        g.fillRect(0, 0, 1000, 1000);
        calculatingPI(g);
        for (Point2D p : list) {
            if (inCircle((Point2D.Double) p)) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.blue);
            }
            g.drawLine((int) p.getX(), (int) p.getY(), (int) p.getX(), (int) p.getY());
        }
    }


    public void calculatingPI(Graphics g) {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i<500; i++) {
            points();
            double inCircle = 0;
            double inRect = list.size();
            for (Point2D p : list) {
                if (inCircle((Point2D.Double) p)) {
                    inCircle++;
                }
            }
            double ratio = inRect / inCircle;
            values.add(4/ratio);
        }
        System.out.print("PI = " + average(values)+ " ");
    }

    public double average (ArrayList<Double> values) {
        double count = 0;
        for (Double value : values) {
            count+=value;
        }
        return count/values.size();
    }





    public boolean inCircle(Point2D.Double p) {
        Point2D.Double center = new Point2D.Double(width / 2d, width / 2d + 23);
        return center.distance(p) <= width / 2d;
    }


    public static void main(String[] args) {
        Frame frame = new Main();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        // circle coordinates.
        frame.setSize(800, 1000);
        frame.setVisible(true);
    }
}
