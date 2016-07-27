
package javaapplication3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.nio.file.Paths;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class JavaApplication3 {

   public static boolean pressed;
   public static ImagePanel[] imgs;
   public static ImagePanel[] details;
   public static String[] paths;
   public static int x,y;
    
   
   
   public void move(imagepann)
    public static void main(String[] args) {
        
          
        imgs=new ImagePanel[10];
        details=new ImagePanel[10];
        //specifypaths
        
        ///
        
        JFrame frm = new JFrame();
        frm.setSize(500, 500);
        
        //frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.addMouseListener( new Listener());
        frm.addMouseMotionListener(new Listener());
        frm.setBackground(Color.WHITE);
      ImagePanel i= new ImagePanel(new ImageIcon("E:\4507.jpg").getImage());
      i.setVisible(true);
      frm.getContentPane().add(i);
      //frm.pack();
       frm.setVisible(true);
//      for (int i=0;i<=9;i++){
//          
//          imgs[i]=new ImagePanel(new ImageIcon("E:\4507.jpg").getImage());
//          imgs[i].setLocation(i*600, 0);
//          imgs[i]=new ImagePanel(new ImageIcon("E:\4507.jpg").getImage());
//          imgs[i].setLocation(i*600, 600);
//          
//          frm.getContentPane().add(imgs[i]);
//        //frm.pack();
//        frm.setVisible(true);
//      } 


    }
    
}





////////////////////////////////////////////////////////////


 class ImagePanel extends JPanel {

 private Image img;
 
    
//  public  void setimage(Image img){
// this.img = img; 
// this.repaint();
//  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
      addMouseListener(new Listener());
      addMouseMotionListener(new Listener());
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
    
  }

}
/////////////////////////////////////////////


    
    class Listener implements MouseListener , MouseMotionListener{

        
        

        
        public void mousePressed(MouseEvent e) {
            JavaApplication3.pressed=true;
            JavaApplication3.x=e.getX();
            JavaApplication3.y=e.getY();
  
        }

        
        public void mouseReleased(MouseEvent e) {
               JavaApplication3.pressed=false;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}

        
        public void mouseMoved(MouseEvent e) {
         
            if (JavaApplication3.pressed==true){
                //set panels location
                
                ///
            JavaApplication3.x=e.getX();
            JavaApplication3.y=e.getY();
            }
        }
    }