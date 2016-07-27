package Main;

import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public String[] mainPaths, detailPaths;
    public ImagePanel[] imagePanels;
    public static boolean pressed;
    public static int x, y;
    private int currentImgIndex;
    private JPanel mainPanel;
    private int offset;

    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;
        this.currentImgIndex = 0;
        this.offset = 0;
        imagePanels = new ImagePanel[mainPaths.length];

        setupJframe();
    }

    private void setupJframe() {
//        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setSize(1920,1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);



        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(getWidth(), getHeight());
        mainPanel.setLocation(0,0);

        for (int i = 0; i < imagePanels.length; i++) {

            imagePanels[i] = new ImagePanel(this, mainPaths[i], detailPaths[i]);
            imagePanels[i].setLocation(i*600, 0);
             mainPanel.add(imagePanels[i]);
        }

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    public void gotoNextImage() {
        if(currentImgIndex == imagePanels.length) {
            currentImgIndex++;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int width = getWidth();
                    while(width > 0)
                    {
                        try {
                            mainPanel.setLocation(offset, 100);
                            repaint();
                            offset-=10;
                            width-=10;
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mainPanel.setLocation(currentImgIndex*getWidth(), 100);
                }
            }).start();
        }
    }

    public void gotoPrevImage() {
        if(currentImgIndex == imagePanels.length) {
            currentImgIndex--;
        }
    }

    public void stayOnThisImage() {
        imagePanels[currentImgIndex].setLocation(0, 100);
        repaint();
    }

    public void displacePanel(int distance)
    {
        imagePanels[currentImgIndex].setLocation(distance, 100);
        repaint();
    }
}
