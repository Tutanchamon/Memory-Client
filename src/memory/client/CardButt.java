/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

/**
 *
 * @author blazej
 */
public class CardButt extends JToggleButton implements ActionListener {
    
    CardButt(){
        
        
        ImageIcon icon = new ImageIcon("0.jpg");
        ImageIcon icon2 = new ImageIcon("0.jpg");
        Dimension dim = new Dimension(100, 100);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setIcon(icon);
        this.setSelectedIcon(icon2);
        
        addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Wywołuje się actionPerformed z CardButt");
        ImageIcon icon = new ImageIcon("0.jpg");
        ImageIcon icon2 = new ImageIcon("1.jpg");
        this.setIcon(icon);
        this.setPressedIcon(icon2);
        this.setSelectedIcon(icon2);
        System.out.println("Szerokosc icon to: "+icon.getIconWidth());
        CardButt btn = (CardButt) ae.getSource();
       // btn.setIcon(btn.getIcon()==ico1 ? ico2 : ico1);
        btn.setIcon(icon);
        //this.setText("Bla bla bla");
    }
    
}
