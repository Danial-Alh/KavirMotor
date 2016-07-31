package UI;

import javax.swing.*;

public class VerticalPanel extends JPanel
{
    private MainFrame parent;
    private int size;
    private int numberOfRowsAdded;

    public VerticalPanel(MainFrame parent, int size)
    {
        this.parent = parent;
        this.size = size;
        this.numberOfRowsAdded = 0;

        setLayout(null);
        setSize(getWidth() * size, getHeight());
        setLocation(0, 0);
    }

    public void addRow(HorizontalPanel horizontalPanel)
    {
        horizontalPanel.setLocation(0, numberOfRowsAdded++ * parent.getHeight());
        add(horizontalPanel);
    }
}
