/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author blazej
 */
public class MemoryClient {

    /**
     * @param args the command line arguments
     */
    
    // atrybuty klasy głównej
   public static BufferedReader in;
   public static PrintWriter out;
   public static Socket sock;
   public static InputStream is;
   public static int[] tablica;
   public static int numergracza;
    
            
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Zła ilość argumentów. ");
            System.exit(1);
        }
        
        
        SocketConnection sc = new SocketConnection (args[0], args[1]);
        //in = sc.getIn();
        is = sc.getIs();
        out = sc.getOut();
        sock = sc.getSock();
        GlowneOkienko go = new GlowneOkienko();
        go.setVisible(true);
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.uzupelnijKarty();
        gw.wyswietlPrzyciski(30);
        
    }
}
