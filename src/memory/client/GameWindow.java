/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GameWindow.java
 *
 * Created on 2012-04-13, 13:05:35
 */
package memory.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


/**
 *
 * @author blazej
 */
public class GameWindow extends javax.swing.JFrame {
    
    /*******************************************************/
    /*************WEWNĘTRZNA KLASA GAMETHREAD***************/
    public class GameThread extends Thread {
        
        @Override
        public void run(){
            System.out.println("Wątek się uruchamia się");
            int numer1, numer2, numergracza, nastepny;
            double numera, numerb, numergraczab, nastepnyb;
            byte[] bufor = new byte[64];
            
            while (Game.getTaken() < 16){
                InputStream buf = MemoryClient.is;
                
                try {
                    
                                        
                    int received = buf.read(bufor, 0, bufor.length);
                    if (received <= 0) {
                        System.out.println("Nic nie otrzymałem");
                        break;
                    }
                    
                    System.out.println();
                    char[] charArr = (new String(bufor)).toCharArray();
                    String wiadomosc = String.copyValueOf(charArr);
                    System.out.println("Otrzymałem wiadomość: "+wiadomosc);
                    String[] messages = wiadomosc.split("\\|");
                                    
                    System.out.println("messages[0]: "+messages[0]);
                    System.out.println("messages[1]: "+messages[1]);
                    System.out.println("messages[2]: "+messages[2]);
                    System.out.println("messages[3]: "+messages[3]);
                    
                    // parse the message
                        try {
                        numergraczab = Double.parseDouble(messages[0]);
                        numera = Double.parseDouble(messages[1]);
                        numerb = Double.parseDouble(messages[2]);
                        nastepnyb = Double.parseDouble(messages[3]);
                        numergracza = (int) numergraczab;
                        numer1 = (int) numera;
                        numer2 = (int) numerb;
                        nastepny = (int) nastepnyb;
                        
                        System.out.println("Ruch wykonywał gracz "+numergracza+", wybrał karty "+numer1+" i "
                                + numer2+". Następny ruch wykona "+ nastepny);
                        }
                        
                        catch(NumberFormatException ex){
                            ex.printStackTrace();
                        }
                    //
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    /*******************************************************/
    /*******************************************************/
    
    /*******************************************************/
    /**************WEWNĘTRZNA KLASA CARDBUTT****************/
    public class CardButt extends JToggleButton implements ActionListener {
    
    private int pozycja;
    private Card karta;
    ImageIcon ikonabg;
    ImageIcon ikonafg;
    
    
    CardButt(Card c, int number){
        pozycja = number;               
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
        this.setDisabledIcon(icon2);
        this.setDisabledSelectedIcon(icon2);
        
        
       // if (this.isSelected()) System.out.println("Przycisk jest wybrany :o");
       // else System.out.println("Przycisk ustawiony jako wyłączony");
        addActionListener(this);
    }
    
    public int getPosition(){
        return pozycja;
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
                GameWindow.this.dodajWybor(karta, pozycja);
                                            
                ImageIcon img = new ImageIcon(karta.fgpath+".jpg");
            
                System.out.println("Szerokosc icon2 to: "+ikonafg.getIconWidth());
                
                if (karta.isTaken()) System.out.println("Ktoś już zdobył tę kartę");
                else System.out.println("Nikt nie odkrył jeszcze tej karty");
            }
        }
    }
    
}
    /*******************************************************/
    /*******************************************************/
    
    
    public static Vector<Card> deal = new Vector<Card>();
    private static int selected = 0;
    private static Card sCard1, sCard2;
    public static int sNumber1, sNumber2;
    private static CardButt[] butts = new CardButt[30];
    private GameThread gt = new GameThread();
    

    /** Creates new form GameWindow */
    public GameWindow() {
        initComponents();
        this.jLabel2.setText(Game.oplayer1);
        this.jLabel3.setText(Game.oplayer2);
        gt.start();
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Memory");
        setMinimumSize(new java.awt.Dimension(700, 700));

        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Twój wynik:");
        jPanel2.add(jLabel1, new java.awt.GridBagConstraints());

        jLabel2.setText("jLabel2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setText("jLabel3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Ruch:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel2.add(jLabel4, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void uzupelnijKarty(){
        for (int i = 0; i < 30; i++){
            Card c = new Card(i, String.valueOf(MemoryClient.tablica[i]));
            deal.add(c);
        }
    }
    
    public void setLabelText(String text){
        this.jLabel1.setText(text);
    }
    
    public void dodajWybor(Card karta, int numer){
        
        selected++;
        if (selected == 1) {
            sCard1 = karta;
            sNumber1 = numer;
        }
        System.out.println("selected wynosi: "+selected);
        
        // w przypadku, gdy karty są parą
        if (selected == 2) {
            sCard2 = karta;
            sNumber2 = numer;
            if (Card.equal(sCard1, sCard2)){
                System.out.println("Parka!");
                this.zwiekszWynik();
                sCard1.setTaken(true);
                sCard2.setTaken(true);
                this.sendChoice(Game.numergracza, sNumber1, sNumber2, Game.numergracza);
            }
            else {
                System.out.println("To nie jest parka");
                this.sendChoice(Game.numergracza, sNumber1, sNumber2, (Game.numergracza+1) %3);
            }
            selected = 0;
            try {
                Thread.sleep(2000);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            for (Card c : deal){
                c.unreveal();
            }
            for (CardButt cb : butts){
                if (cb.cardTaken()){
                    cb.setEnabled(false);
                    cb.setSelected(true);
                }
                else {
                    cb.setEnabled(true);
                    cb.setSelected(false);
                }
                
            }
        }
    }
    
    public void wyswietlPrzyciski(int ilosc){
        
        jPanel1.setLayout(new java.awt.GridLayout(6, 5, 0, 0));
        
        for (int i = 0; i < 30; i++){
            butts[i] = new CardButt(deal.get(i), i);
            this.jPanel1.add(butts[i]);
        }  
    }
    
    private int getSelected(){
        return selected;
    }
    
    public void zwiekszWynik(){
        Game.increaseTaken(Game.numergracza);
        int wynike = Game.getMyScore();
        this.jLabel1.setText("Twój wynik: "+String.valueOf(wynike));
    }
    
    public static void sendChoice(int numer, int pozycja1, int pozycja2, int zmianakolejki){
       System.out.println("Wysyłam ciąg znaków: "+numer+"|"+pozycja1+"|"+pozycja2+"|"+zmianakolejki);
       PrintWriter pr = MemoryClient.out;
       pr.println(numer+"|"+pozycja1+"|"+pozycja2+"|"+zmianakolejki);
                  
   }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
