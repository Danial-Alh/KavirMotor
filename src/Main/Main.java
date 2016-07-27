
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
                "kavir/8959.Jpg"
        };
        String[] details = new String[]{
                "kavir/Capture.JPG",
                "kavir/Capture.JPG"
        };
        ///

        frm = new MainFrame(main, details);
    }


}