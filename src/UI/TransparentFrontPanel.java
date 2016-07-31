package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.*;

public class TransparentFrontPanel extends JPanel implements MouseMotionListener, MouseListener
{

    private MainFrame parent;
    private Point startPoint;
    private Point endPoint;
    private Point currentPoint;
    private Boolean currentMovingAxis;

    public TransparentFrontPanel(MainFrame parent)
    {
        this.parent = parent;
        init();
    }

    private void init()
    {
        setSize(parent.getSize());
        setLocation(0, 0);
        setBackground(Color.BLUE);
        setOpaque(false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }


    @Override
    public synchronized void mouseDragged(MouseEvent e)
    {
        currentPoint = new Point(e.getX(), e.getY());
        double hortizalD = currentPoint.getX() - startPoint.getX();
        double verticalD = currentPoint.getY() - startPoint.getY();
        if (currentMovingAxis == null)
        {
            if (sqrt(pow(hortizalD, 2) + pow(verticalD, 2)) > 20)
            {

                if (abs(hortizalD) < abs(verticalD))
                    currentMovingAxis = Boolean.TRUE;
                else
                    currentMovingAxis = Boolean.FALSE;
            }
        } else
        {
            if (currentMovingAxis == Boolean.TRUE)
                parent.moveVert(currentPoint.getY() - startPoint.getY());
            else
                parent.moveHort(currentPoint.getX() - startPoint.getX());


        }
    }


    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e)
    {
        endPoint = new Point(e.getX(), e.getY());
        double hortizalD = endPoint.getX() - startPoint.getX();
        double verticalD = endPoint.getY() - startPoint.getY();
        Boolean tempBool = currentMovingAxis;
        if(tempBool != null)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    if (tempBool == Boolean.TRUE)
                    {
                        if (verticalD > 0) parent.gotoUpperRow();
                        if (verticalD < 0) parent.gotoLowerRow();
                    }
                    else
                    {
                        if (hortizalD > 0) parent.gotoPrevColumn();
                        if (hortizalD < 0) parent.gotoNextColumn();
                    }
                }
            }).start();
            currentMovingAxis = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}