
package Main;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Main {

    private static JFrame frm;

    public static void main(String[] args) {


        //specifypaths
        String[] main = new String[]{
                "kavir/4507.Jpg",
                "kavir/8959.Jpg",
                "kavir/Aprilia-RSV4-RF-2016_latdx.jpg",
                "kavir/factory_works_z1.jpg",
                "kavir/RSV4+RR+black+(6).jpg"
        };
        String[] details = new String[]{
                "kavir/Capture(1).JPG",
                "kavir/Capture(1).JPG",
                "kavir/Capture(1).JPG",
                "kavir/Capture(1).JPG",
                "kavir/Capture(1).JPG"
        };
        ///

        frm = new MainFrame(main, details);
    }


}