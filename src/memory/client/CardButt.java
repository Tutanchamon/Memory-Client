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
/* *******************
 * public class CardButt extends JToggleButton implements ActionListener {
    
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
        this.setSelected(false);
        this.setIcon(icon);
        this.setSelectedIcon(icon2);
        ImageIcon dis = new ImageIcon("16.jpg");
        this.setDisabledIcon(dis);
        this.setDisabledSelectedIcon(dis);
        
        
        if (this.isSelected()) System.out.println("Przycisk jest wybrany :o");
        else System.out.println("Przycisk ustawiony jako wyłączony");
        addActionListener(this);
    }
    
    public boolean cardTaken(){
        if (karta.isTaken()) return true;
        else return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Wywołuje się actionPerformed z CardButt");
        if (karta.isRevealed()) System.out.println("Karta jest odkryta");
        else System.out.println("Karta jest zakryta");
       
        if (!this.isSelected()){
            if (karta.isRevealed()) {
                System.out.println("Karta jest odkryta - zasłaniam");
                karta.unreveal();
                if (karta.isRevealed()) System.out.println("Chyba się nie zasłoniła");
                else System.out.println("Karta zasłonięta poprawnie");
                System.out.println("karta.getPath() wynosi: "+karta.getPath());
                ImageIcon ikona = new ImageIcon(karta.getPath());
                ImageIcon img = new ImageIcon(karta.bgpath+".jpg");
                
                System.out.println("Szerokosc icon to: "+ikonabg.getIconWidth());
                if (karta.isTaken()){
                    ImageIcon dis = new ImageIcon("16.jpg");
                    System.out.println("Ktoś już zdobył tę kartę");
                    this.setDisabledIcon(dis);
                    this.setDisabledSelectedIcon(dis);
                    
                }
                else System.out.println("Nikt nie odkrył jeszcze tej karty");
            }        
        }
        if (this.isSelected()){
            if (!karta.isRevealed()){
                System.out.println("Karta jest zasłonięta - odkrywam");
                karta.reveal();
                if (!karta.isRevealed()) System.out.println("Chyba się nie odkryła");
                else System.out.println("Karta odsłonięta poprawnie");
                System.out.println("karta.getPath() wynosi: "+karta.getPath());
                ImageIcon ikona = new ImageIcon(karta.getPath());
                System.out.println("Wykonuję dodajWybor()");
                try {
                    Thread.sleep(500);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                    
                }
                this.setEnabled(false);
                GameWindow.dodajWybor(karta);
                
            
            
                ImageIcon img = new ImageIcon(karta.fgpath+".jpg");
            
                System.out.println("Szerokosc icon2 to: "+ikonafg.getIconWidth());
                
                if (karta.isTaken()) System.out.println("Ktoś już zdobył tę kartę");
                else System.out.println("Nikt nie odkrył jeszcze tej karty");
            }
        }
       // this.setIcon(icon);
       // this.setPressedIcon(icon2);
       // this.setSelectedIcon(icon2);
        
        //this.setText("Bla bla bla");
    }
    
}
/*******************/