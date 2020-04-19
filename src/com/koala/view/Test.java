package com.koala.view;

import java.awt.*;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Test extends JFrame
{
    JPanel red = new ColorPanel(Color.red,300,300);
    JPanel grn = new ColorPanel(Color.green,100,300);
 
    public Test()
    {
//        RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS, 10);
//        rl.setFill(true);
//        JPanel panel = new JPanel( rl );
//        panel.add(red, new Float(1));
        JPanel panel = new JPanel( new BorderLayout(10, 10) );
        panel.add(red);
        panel.add(grn, BorderLayout.EAST);
            
        this.getContentPane().add(panel);   
        this.setVisible(true);   
        this.pack();   
    }   
        
        
    public static void main(String[] args)   
    {   
        new Test();   
    }   
}   
   
   
class ColorPanel extends JPanel   
{   
    public ColorPanel(Color color, int W, int H)   
    {   
        this.setBackground(color);   
        this.setPreferredSize(new Dimension(W,H));   
    }   
}
