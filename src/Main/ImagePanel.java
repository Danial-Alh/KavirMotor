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
int y;
int dis;
int lastY=0;

    void setimage(int distance) {
        this.dis=distance+lastY;

        this.repaint();
    }

    class ImageDetail {
        public Image img;
        public Image detailImg;
        public String mainPath;
        public String detailPath;

        public ImageDetail(String mainPath, String detailPath) {
            this.mainPath = mainPath;
            this.detailPath = detailPath;
            try {
                File mainImgFile = new File(mainPath);
                File detailImgFile = new File(detailPath);
                img = ImageIO.read(mainImgFile);
                detailImg = ImageIO.read(detailImgFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    ImageDetail imageDetail;
    MainFrame parent;

    public ImagePanel(MainFrame parent, String mainImgSrc, String detailImgSource) {
        this.parent = parent;
        imageDetail = new ImageDetail(mainImgSrc, detailImgSource);
        Dimension size = new Dimension(imageDetail.img.getWidth(null), imageDetail.img.getHeight(null));
//        setPreferredSize(size);
//        setMinimumSize(size);
//        setMaximumSize(size);
        setSize(this.parent.getSize());
        setLayout(null);
        addMouseListener(new Listener());
        addMouseMotionListener(new Listener());
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0, getWidth(), getHeight());
        g.drawImage(imageDetail.img,(this.getParent().getWidth()-imageDetail.img.getWidth(null))/2, dis, null);
        g.drawImage(imageDetail.detailImg,(this.getParent().getWidth()-imageDetail.detailImg.getWidth(null))/2, dis+this.getParent().getHeight(), null);
    }

    class Listener implements MouseListener, MouseMotionListener {


        public void mousePressed(MouseEvent e) {
        ImagePanel i=(ImagePanel)e.getSource();
        i.y=e.getY();
        }
        

        public void mouseReleased(MouseEvent e) {
            lastY=dis;
            boolean isLeftRightSwipe = true;
            if(isLeftRightSwipe)
            {
                if(dis > 0)
                    parent.gotoNextImage();
            }

        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
                ImagePanel i=(ImagePanel)e.getSource();
                i.setimage(-i.y+e.getY());
//                i.y=e.getY();
                
                
            
           
        }


        public void mouseMoved(MouseEvent e) {

           
            }
        }
    }

