package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by online on 7/27/2016.
 */
public class MainFrame extends JFrame {
    public static boolean pressed;
    public String[] paths;
    public ImagePanel[] imagePanels;
    public static int x, y;


    public MainFrame(String[] paths) throws HeadlessException {
        this.paths = paths;
        setupJframe();

    }

    private void setupJframe() {
        setSize(500, 500);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(new ImagePanel.Listener());
        addMouseMotionListener(new ImagePanel.Listener());
        setBackground(Color.WHITE);
//        ImagePanel i = new ImagePanel(new ImageIcon("E:\4507.jpg").getImage());
//        i.setVisible(true);
//        getContentPane().add(i);
        //pack();
        setVisible(true);
        for (int i=0;i<=9;i++){

            imagePanels[i]=new ImagePanel("kavir/1.JPG", "kavir/4507.JPG");
            imagePanels[i].setLocation(i*600, 0);

            getContentPane().add(imagePanels[i]);
            //pack();
            setVisible(true);
        }
    }
}
