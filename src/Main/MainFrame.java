package Main;

import javafx.stage.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    public String[] mainPaths, detailPaths;
    public ImagePanel[] imagePanels;
    public static boolean pressed;
    public static int x, y;
    private int currentImgIndex;
    private JPanel mainPanel;
    private int offset;

    private Image kavirImg;

    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;
        this.currentImgIndex = 0;
        this.offset = 0;
        imagePanels = new ImagePanel[mainPaths.length];
        try {
            kavirImg = ImageIO.read(new File("kavir/kavir.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupJframe();
    }

    private void setupJframe() {
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);


        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(getWidth()*mainPaths.length, getHeight());
        mainPanel.setLocation(0, 0);

        for (int i = 0; i < imagePanels.length; i++) {

            imagePanels[i] = new ImagePanel(this, mainPaths[i], detailPaths[i]);
            imagePanels[i].setLocation(i * getWidth(), 0);
            mainPanel.add(imagePanels[i]);
        }

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    public void gotoNextImage() {
        if (currentImgIndex < imagePanels.length-1) {
            currentImgIndex++;
//            imagePanels[currentImgIndex].reset();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int width = getWidth();
                    while (width > 0) {
                        try {
                            mainPanel.setLocation(offset, 0);
                            repaint();
                            offset -= 10;
                            width -= 10;
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    offset = -currentImgIndex * getWidth();
                    mainPanel.setLocation(offset, 0);
                }
            }).start();
        }
    }

    public void gotoPrevImage() {
        if (currentImgIndex > 0) {
            currentImgIndex--;
//            imagePanels[currentImgIndex].reset();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int width = getWidth();
                    while (width > 0) {
                        try {
                            mainPanel.setLocation(offset, 0);
                            repaint();
                            offset += 10;
                            width -= 10;
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    offset = -currentImgIndex * getWidth();
                    mainPanel.setLocation(offset, 0);
                }
            }).start();
            imagePanels[currentImgIndex].reset();
        }
    }

    public void stayOnThisImage() {
        imagePanels[currentImgIndex].setLocation(0, 0);
        repaint();
    }

    public void displacePanel(int distance) {
        imagePanels[currentImgIndex].setLocation(distance, 0);
        repaint();
    }

//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        g.drawImage(kavirImg, getWidth()-kavirImg.getWidth(null),0, null);
//    }
}
