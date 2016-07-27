import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

import static java.lang.Math.abs;


public class JavaApplication3 {

    public static boolean pressed;
    public static ImagePanel panelshare;
    public static int x, y;

    public static void main(String[] args) {

    }


    public static void gotoNextImage() {

    }

    public static void alignThisImage() {

    }
}


////////////////////////////////////////////////////////////


class ImagePanel extends JPanel {

    private Image img;


//  public  void setimage(Image img){
// this.img = img; 
// this.repaint();
//  }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        addMouseListener(new Listener());
        addMouseMotionListener(new Listener());
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);

    }
}
/////////////////////////////////////////////


class Listener implements MouseListener, MouseMotionListener {

    class MouseMovingVector {
        public Point startPoint;
        public Point endPoint;

        public MouseMovingVector(Point startPoint, Point endPoint) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }
    }

    private MouseMovingVector mouseMovingVector;
    public Listener() {
        mouseMovingVector = new MouseMovingVector(null, null);
    }

    public void mousePressed(MouseEvent e) {
        JavaApplication3.pressed = true;
        JavaApplication3.x = e.getX();
        JavaApplication3.y = e.getY();
        mouseMovingVector.startPoint = new Point(e.getX(), e.getY());
    }


    public void mouseReleased(MouseEvent e) {
        JavaApplication3.pressed = false;
        mouseMovingVector.endPoint = new Point(e.getX(), e.getY());

        if(abs(mouseMovingVector.startPoint.x-mouseMovingVector.endPoint.x) > 100)
            JavaApplication3.gotoNextImage();
        else
            JavaApplication3.alignThisImage();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }


    public void mouseMoved(MouseEvent e) {

        if (JavaApplication3.pressed == true) {
            //set panels location
            JavaApplication3.x = e.getX();
            JavaApplication3.y = e.getY();
        }
    }
}
