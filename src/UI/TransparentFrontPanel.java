package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;

public class TransparentFrontPanel extends JPanel implements MouseMotionListener, MouseListener {

    private MainFrame parent;
    private Point startPoint;
    private Point endPoint;

    public TransparentFrontPanel(MainFrame parent) {
        this.parent = parent;
        init();
    }

    private void init() {
        setSize(parent.getSize());
        setLocation(0,0);
        setBackground(Color.BLUE);
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint=new Point(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint=new Point(e.getX(),e.getY());
        double hortizalD = endPoint.getX()-startPoint.getX();
        double verticalD = endPoint.getY()-startPoint.getY();
        if(abs(hortizalD)<abs(verticalD));
            //sweepVert();
        else
            if(hortizalD>0)parent.gotoNextImage();
            if(hortizalD<0)parent.gotoPrevImage();
        }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
};