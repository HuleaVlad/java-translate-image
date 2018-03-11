package javaswing;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 *
 */
public class JavaTestSwing {

    static JFrameWin jFrameWindow;

    public static class MyComponent extends JComponent{

        BufferedImage bufferedImage = null;
        Dimension myDimension = new Dimension(50, 50);

        public MyComponent() {
            try {
                long readImgTimeStart = System.currentTimeMillis();
                bufferedImage = ImageIO.read(this.getClass().getResource("duke.png"));
                int imageWidth = bufferedImage.getWidth();
                int imageHeight = bufferedImage.getHeight();
                myDimension = new Dimension(imageWidth * 2, imageHeight * 2);
                long readImgTimeStop = System.currentTimeMillis();
            } catch (IOException ex) {
                Logger.getLogger(JavaTestSwing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        long imageProcessingTimeStart = System.currentTimeMillis();
        @Override
        public Dimension getPreferredSize() {
            return myDimension;
        }

        @Override
        public Dimension getMaximumSize() {
            return myDimension;
        }

        @Override
        public Dimension getMinimumSize() {
            return myDimension;
        }


        @Override
        protected void paintComponent(Graphics g) {

            //g.drawImage(bufferedImage, 0, 0, null);c
            Graphics2D graphics2D;

            //2X
            graphics2D = (Graphics2D)g.create();
            graphics2D.drawImage(bufferedImage, 0, 0, null);

            //1X
            graphics2D = (Graphics2D)g.create();
            graphics2D.translate(30, 0);
            graphics2D.drawImage(bufferedImage, 0, 0, null);

            //0.5X
            graphics2D = (Graphics2D)g.create();
            graphics2D.translate(60, 100);
            graphics2D.drawImage(bufferedImage, 0, 0, null);

            //dispose Graphics2D object
            graphics2D.dispose();
        }
        long imageProcessingTimeStop = System.currentTimeMillis();
    }

    public static class JFrameWin extends JFrame{
        public JFrameWin(){
            long printImgTimeStart = System.currentTimeMillis();
            this.setTitle("Proiect Java");
            this.setSize(300, 300);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MyComponent myComponent = new MyComponent();

            Box horizontalBox = Box.createHorizontalBox();

            horizontalBox.add(myComponent);
            this.add(horizontalBox);
            long printImgTimeStop = System.currentTimeMillis();
        }
    }


    public static void main(String[] args){
        Runnable doSwingLater = new Runnable(){
            public void run() {
                jFrameWindow = new JFrameWin();
                jFrameWindow.setVisible(true);
           //     System.out.println("Timpul de citire al imaginii este: "+(readImgTimeStop - readImgTimeStart)/1000.0 );
           //     System.out.println("Timpul de prelucrare al imaginii este: "+ (imageProcessingTimeStop - imageProcessingTimeStart)/1000.0);
           //     System.out.println("Timpul de printare al imaginii este: "+ (printImgTimeStop - printImgTimeStart)/1000.0);
            }
        };
        SwingUtilities.invokeLater(doSwingLater);

    }

}