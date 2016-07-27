package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

class ImagePanel extends JPanel {

    private Image img;
    class ImageDetail
    {
        public Image img;
        public Image detailImg;
        public String mainPath;
        public String detailPath;

        public ImageDetail(String mianPath, String detailPath) {
            this.mainPath = mianPath;
            this.detailPath = detailPath;
            try {
                img = ImageIO.read(new File(mainPath));
                detailImg = ImageIO.read(new File(detailPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    ImageDetail imageDetail;

    public ImagePanel(String mainImgSrc, String detailImgSource) {
        imageDetail = new ImageDetail(mainImgSrc, detailImgSource);
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

    static class Listener implements MouseListener, MouseMotionListener {


        public void mousePressed(MouseEvent e) {
            Main.Main.pressed = true;
            Main.Main.x = e.getX();
            Main.Main.y = e.getY();

        }


        public void mouseReleased(MouseEvent e) {
            Main.Main.pressed = false;
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

            if (Main.Main.pressed == true) {
                //set panels location

                ///
                Main.Main.x = e.getX();
                Main.Main.y = e.getY();
            }
        }
    }
}
