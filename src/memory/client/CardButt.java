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
    
    private Card karta;
    ImageIcon ikonabg;
    ImageIcon ikonafg;
    
    
    CardButt(Card c){
                       
        karta = c;
        String nazwapliku = c.getPath();
        ImageIcon icon = new ImageIcon(karta.bgpath+".jpg");
        ikonabg = icon;
        ImageIcon icon2 = new ImageIcon(karta.fgpath+".jpg");
        ikonafg = icon2;
        Dimension dim = new Dimension(80, 80);
        this.setPreferredSize(dim);
        this.setMinimumSize(dim);
        this.setIcon(icon);
        this.setSelectedIcon(icon2);
        
        addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Wywołuje się actionPerformed z CardButt");
       
       
        if (this.isSelected()){
            if (karta.isRevealed()) {
                System.out.println("Karta jest odkryta - zasłaniam");
                karta.unreveal();
                if (karta.isRevealed()) System.out.println("Chyba się nie zasłoniła");
                else System.out.println("Karta zasłonięta poprawnie");
                System.out.println("karta.getPath() wynosi: "+karta.getPath());
                ImageIcon ikona = new ImageIcon(karta.getPath());
                ImageIcon img = new ImageIcon(karta.bgpath+".jpg");
            
                System.out.println("Szerokosc icon to: "+ikonabg.getIconWidth());
        }        
        }
        if (!this.isSelected()){
        if (!karta.isRevealed()){
            System.out.println("Karta jest zasłonięta - odkrywam");
            karta.reveal();
            if (!karta.isRevealed()) System.out.println("Chyba się nie odkryła");
            else System.out.println("Karta odsłonięta poprawnie");
            System.out.println("karta.getPath() wynosi: "+karta.getPath());
            ImageIcon ikona = new ImageIcon(karta.getPath());
            
            
            ImageIcon img = new ImageIcon(karta.fgpath+".jpg");
            
            System.out.println("Szerokosc icon to: "+ikonafg.getIconWidth());
        }
        }
       // this.setIcon(icon);
       // this.setPressedIcon(icon2);
       // this.setSelectedIcon(icon2);
        
        //this.setText("Bla bla bla");
    }
    
}
