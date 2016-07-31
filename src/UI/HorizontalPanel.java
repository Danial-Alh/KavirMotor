package UI;

import javax.swing.*;
import java.awt.*;

public class HorizontalPanel extends JPanel
{
    private MainFrame parent;
    private int numberOfCols;
    private int numberOfPanelsAdded;

    public HorizontalPanel(MainFrame parent, int numberOfCols)
    {
        this.parent = parent;
        this.numberOfCols = numberOfCols;
        this.numberOfPanelsAdded = 0;

        setLayout(null);
        setSize(parent.getWidth() * numberOfCols, parent.getHeight());
        setLocation(0, 0);
        setBackground(Color.red);
    }

    public void addPanel(ImagePanel panel)
    {
        panel.setLocation(numberOfPanelsAdded++ * parent.getWidth(), 0);
        add(panel);
    }

    public int getNumberOfCols()
    {
        return numberOfCols;
    }
}
