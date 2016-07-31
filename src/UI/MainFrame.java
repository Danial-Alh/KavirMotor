package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame
{
    private VerticalPanel verticalPanel;
    private HorizontalPanel[] horizontalPanels;
    private TransparentFrontPanel frontPanel;

    public String[] mainPaths, detailPaths;
    public ImagePanel[] imagePanels;
    private ImagePanel detailPanel;

    private Point currentLocation;
    private final int rowNumbers = 2;
    private Image kavirImg;

    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException
    {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;
        this.currentLocation = new Point(0,0);

        imagePanels = new ImagePanel[mainPaths.length];
        try
        {
            kavirImg = ImageIO.read(new File("kavir/kavir.JPG"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        setupJframe();
    }

    private void setupJframe()
    {
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);

        horizontalPanels = new HorizontalPanel[rowNumbers];

        setupFirstRow();
        setupSecondRow();

        verticalPanel = new VerticalPanel(this, 2);
        getContentPane().add(verticalPanel);

        frontPanel = new TransparentFrontPanel(this);
        getContentPane().add(frontPanel);

        setVisible(true);
    }

    private void setupFirstRow()
    {
        horizontalPanels[0] = new HorizontalPanel(this, mainPaths.length);

        for (int i = 0; i < imagePanels.length; i++)
        {
            imagePanels[i] = new ImagePanel(this, mainPaths[i]);
            horizontalPanels[0].addPanel(imagePanels[i]);
        }

    }

    private void setupSecondRow()
    {
        horizontalPanels[1] = new HorizontalPanel(this, 1);

        detailPanel = new ImagePanel(this, detailPaths[0]);
        horizontalPanels[1].addPanel(detailPanel);
    }

    public void gotoNextImage()
    {
        if (currentLocation.getX() < imagePanels.length - 1)
        {
            currentLocation.x++;
//            imagePanels[currentImgIndex].reset();

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveRightLeft(true);
                }
            }).start();
        }
    }

    public void gotoPrevImage()
    {
        if (currentLocation.getX() > 0)
        {
            currentLocation.x--;
//            imagePanels[currentImgIndex].reset();

            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveRightLeft(false);
                }
            }).start();
        }
    }

    private synchronized void moveRightLeft(boolean isDirectionRight)
    {
        int direction = isDirectionRight ? -1 : 1;
        int width = getWidth();
        int offset = (int) (currentLocation.getX() * -1);
        while (width > 0)
        {
            try
            {
                horizontalPanels[(int) currentLocation.getY()].setLocation(offset, 0);
                repaint();
                offset += (direction * 10);
                width -= 10;
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        offset = (int) (-currentLocation.getX() * getWidth());
        verticalPanel.setLocation(offset, 0);
    }

    public void stayOnThisImage()
    {
        repaint();
    }

    public void displacePanel(int distance)
    {
        repaint();
    }

}
