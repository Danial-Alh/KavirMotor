package UI;

import javax.swing.*;
import java.awt.*;

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
        setSize(parent.getWidth(), parent.getHeight() * size);
        setLocation(0, 0);
        setBackground(Color.WHITE);

    }

    public void addRow(HorizontalPanel horizontalPanel)
    {
        horizontalPanel.setLocation(0, numberOfRowsAdded++ * parent.getHeight());
        add(horizontalPanel);
    }
}
