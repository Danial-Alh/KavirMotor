package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.abs;

public class MainFrame extends JFrame
{
    private final int numberOfRows = 2;
    private VerticalPanel verticalPanel;
    private HorizontalPanel[] horizontalPanels;
    private TransparentFrontPanel frontPanel;
    private String[] mainPaths, detailPaths;
    private ImagePanel[] imagePanels;
    private ImagePanel detailPanel;
    private int[] xOffsets;
    private int currentRow;
    private Image kavirImg;

    private Point offset;
    private Object condition;
    private Object semaphore;
    private boolean busy, isSemaphoreLocked;

    public MainFrame(String[] mainPaths, String[] detailPaths) throws HeadlessException
    {
        this.mainPaths = mainPaths;
        this.detailPaths = detailPaths;
        this.offset = new Point(0, 0);
        condition = new Object();
        semaphore = new Object();
        isSemaphoreLocked = busy = false;

        this.currentRow = 0;
        this.xOffsets = new int[numberOfRows];
        for (int i = 0; i < xOffsets.length; i++)
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

    public synchronized void gotoNextColumn()
    {
        while (busy)
        {
            try
            {
                condition.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        busy = true;

        if (xOffsets[currentRow] < horizontalPanels[currentRow].getNumberOfCols() - 1)
        {
            xOffsets[currentRow]++;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveRightLeft(true, -xOffsets[currentRow] * getWidth());
                }
            }).start();
        }

        busy = false;
        notifyAll();
    }

    public synchronized void gotoPrevColumn()
    {
        while (busy)
        {
            try
            {
                condition.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        busy = true;

        if (xOffsets[currentRow] > 0)
        {
            xOffsets[currentRow]--;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveRightLeft(false, -xOffsets[currentRow] * getWidth());
                }
            }).start();
        }

        busy = false;
        notifyAll();
    }

    public synchronized void gotoLowerRow()
    {
        while (isSemaphoreLocked)
        {
            try
            {
                semaphore.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        isSemaphoreLocked = true;

        if (currentRow < numberOfRows - 1)
        {
            currentRow++;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveUpDown(true, -currentRow * getHeight());
                }
            }).start();
        }

        isSemaphoreLocked = false;
        notifyAll();
    }

    public synchronized void gotoUpperRow()
    {
        while (isSemaphoreLocked)
        {
            try
            {
                semaphore.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        isSemaphoreLocked = true;

        if (currentRow > 0)
        {
            currentRow--;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    moveUpDown(false, -currentRow * getHeight());
                }
            }).start();
        }

        isSemaphoreLocked = false;
        notifyAll();
    }

    private synchronized void moveUpDown(boolean isDirectionDown, int heightDestination)
    {
        while (busy)
        {
            try
            {
                condition.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        busy = true;

        int direction = isDirectionDown ? -1 : 1;
        int tempDistance = abs(getYOriginOfThisPanel(direction) + offset.y - heightDestination);
        while (tempDistance > 0)
        {
            try
            {
                verticalPanel.setLocation(0, getYOriginOfThisPanel(direction) + offset.y);
                repaint();
                offset.y += (direction * 10);
                tempDistance -= 10;
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        verticalPanel.setLocation(0, getYOriginOfThisPanel(0));
        resetOffsets();

        busy = false;
        MainFrame.this.notifyAll();
    }

    private synchronized void moveRightLeft(boolean isDirectionRight, int widthDestination)
    {
        System.out.println("moveRL start busy: " + busy);

        int direction = isDirectionRight ? -1 : 1;
        int tempDistance = abs(getXOriginOfThisPanel(direction) + offset.x - widthDestination);
        while (tempDistance > 0)
        {
            try
            {
                horizontalPanels[currentRow].setLocation(getXOriginOfThisPanel(direction) + offset.x, 0);
                repaint();
                offset.x += (direction * 10);
                tempDistance -= 10;
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        horizontalPanels[currentRow].setLocation(getXOriginOfThisPanel(0), 0);
        resetOffsets();
        System.out.println("moveRL end busy: " + busy);

    }

    private void resetOffsets()
    {
        offset.x = offset.y = 0;
    }

    public synchronized void moveVert(double offset)
    {
        while (busy)
        {
            return;
//            try
//            {
//                condition.wait();
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
        }
        busy = true;

        if (offset > 0)
        {
            if (currentRow > 0)
            {
                this.offset.y = (int) offset;
                verticalPanel.setLocation(0, getYOriginOfThisPanel(0) + this.offset.y);
            }
        } else
        {
            if (currentRow < numberOfRows - 1)
            {
                this.offset.y = (int) offset;
                verticalPanel.setLocation(0, getYOriginOfThisPanel(0) + this.offset.y);
            }
        }

        busy = false;
        notifyAll();
    }

    public synchronized void moveHort(double offset)
    {
        System.out.println("moveHort busy: " + busy);
        while (busy)
        {
            return;
//            try
//            {
//                condition.wait();
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
        }
        busy = true;

        if (offset > 0)
        {
            if (xOffsets[currentRow] > 0)
            {
                this.offset.x = (int) offset;
                horizontalPanels[currentRow].setLocation(getXOriginOfThisPanel(0) + this.offset.x, 0);
            }
        } else
        {
            if (xOffsets[currentRow] < horizontalPanels[currentRow].getNumberOfCols() - 1)
            {
                this.offset.x = (int) offset;
                horizontalPanels[currentRow].setLocation(getXOriginOfThisPanel(0) + this.offset.x, 0);
            }

        }
        busy = false;
        notifyAll();
    }

    private int getXOriginOfThisPanel(int shiftValue)
    {
        return -(xOffsets[currentRow] + shiftValue) * getWidth();
    }

    private int getYOriginOfThisPanel(int shiftValue)
    {
        return -(currentRow + shiftValue) * getHeight();
    }
}
