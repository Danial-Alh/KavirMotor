package UI;

import javax.swing.*;

public class HorizontalPanel extends JPanel
{
    private MainFrame parent;
    private int size;
    private int numberOfPanelsAdded;

    public HorizontalPanel(MainFrame parent, int size)
    {
        this.parent = parent;
        this.size = size;
        this.numberOfPanelsAdded = 0;

        setLayout(null);
        setSize(getWidth() * size, getHeight());
        setLocation(0, 0);
    }

    public void addPanel(ImagePanel panel)
    {
        panel.setLocation(numberOfPanelsAdded++ * parent.getWidth(), 0);
        add(panel);
    }
}
