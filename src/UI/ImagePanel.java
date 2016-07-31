package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.abs;

class ImagePanel extends JPanel
{

    MainFrame parent;

    public Image img;
    public String imgPath;

    public ImagePanel(MainFrame parent, String imgPath)
    {
        this.parent = parent;
        this.imgPath = imgPath;
        try
        {
            img =  ImageIO.read(new File(imgPath));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        setSize(this.parent.getSize());
        setLayout(null);
    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.white);
        g.drawImage(img, (getWidth() - img.getWidth(null)) / 2, (getHeight() - img.getHeight(null)) / 2, null);
    }
}

