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


    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;
        this.currentImgIndex = 0;
        imagePanels = new ImagePanel[mainPaths.length];

        setupJframe();
    }

    private void setupJframe() {
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
//        setUndecorated(true);
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

    private void gotoNextImage() {
        if(currentImgIndex == imagePanels.length) {
            getContentPane().remove(imagePanels[currentImgIndex]);
            currentImgIndex++;
            getContentPane().add(imagePanels[currentImgIndex]);
        }
    }

    private void gotoPrevImage() {
        if(currentImgIndex == imagePanels.length) {
            getContentPane().remove(imagePanels[currentImgIndex]);
            currentImgIndex--;
            getContentPane().add(imagePanels[currentImgIndex]);
        }
    }

    private void stayOnThisImage() {
        imagePanels[currentImgIndex].setLocation(0, 100);
        repaint();
    }

    public void displacePanle(int distance)
    {
        imagePanels[currentImgIndex].setLocation(distance, 100);
        repaint();
    }
}
