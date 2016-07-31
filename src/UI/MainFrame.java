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

    private String[] mainPaths, detailPaths;
    private ImagePanel[] imagePanels;
    private ImagePanel detailPanel;

    private final int numberOfRows = 2;
    private int[] xOffsets;
    private int currentRow;
    private Image kavirImg;

    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException
    {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;

        this.currentRow = 0;
        this.xOffsets = new int[numberOfRows];
        for(int i = 0; i < xOffsets.length; i++)
            xOffsets[i] = 0;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setUndecorated(true);
        setLayout(null);

        horizontalPanels = new HorizontalPanel[numberOfRows];
        verticalPanel = new VerticalPanel(this, 2);

        setupFirstRow();
        setupSecondRow();


        frontPanel = new TransparentFrontPanel(this);
        getContentPane().add(frontPanel);
        getContentPane().add(verticalPanel);

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
        verticalPanel.addRow(horizontalPanels[0]);
    }

    private void setupSecondRow()
    {
        horizontalPanels[1] = new HorizontalPanel(this, 1);

        detailPanel = new ImagePanel(this, detailPaths[0]);
        horizontalPanels[1].addPanel(detailPanel);

        verticalPanel.addRow(horizontalPanels[1]);
    }

    public void gotoNextColumn()
    {
        if (xOffsets[currentRow] < horizontalPanels[currentRow].getNumberOfCols() - 1)
        {
            xOffsets[currentRow]++;
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

    public void gotoPrevColumn()
    {
        if (xOffsets[currentRow] > 0)
        {
            xOffsets[currentRow]--;
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

    public void gotoLowerRow()
    {
        if (currentRow < numberOfRows - 1)
        {
            currentRow++;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveUpDown(true);
                }
            }).start();
        }
    }

    public void gotoUpperRow()
    {
        if (currentRow > 0)
        {
            currentRow--;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveUpDown(false);
                }
            }).start();
        }
    }

    private synchronized void moveUpDown(boolean isDirectionDown)
    {
        int direction = isDirectionDown ? -1 : 1;
        int height = getHeight();
        int offset = (currentRow + direction) * -1 * getHeight();
        while (height > 0)
        {
            try
            {
                verticalPanel.setLocation(0, offset);
                repaint();
                offset += (direction * 10);
                height -= 10;
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        offset = -currentRow * getHeight();
        verticalPanel.setLocation(0, offset);
    }

    private synchronized void moveRightLeft(boolean isDirectionRight)
    {
        int direction = isDirectionRight ? -1 : 1;
        int width = getWidth();
        int offset = (xOffsets[currentRow] + direction) * -1 * getWidth();
        while (width > 0)
        {
            try
            {
                horizontalPanels[currentRow].setLocation(offset, 0);
                repaint();
                offset += (direction * 10);
                width -= 10;
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        offset = -xOffsets[currentRow] * getWidth();
        horizontalPanels[currentRow].setLocation(offset, 0);
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
