package UI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TransparentFrontPanel extends JPanel implements MouseMotionListener, MouseListener {

    private MainFrame parent;

    public TransparentFrontPanel(MainFrame parent) {
        this.parent = parent;
        init();
    }

    private void init() {
        setSize(parent.getSize());
        setLocation(0,0);
        setOpaque(true);
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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
