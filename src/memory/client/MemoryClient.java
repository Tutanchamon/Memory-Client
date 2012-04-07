/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.client;

import java.io.BufferedReader;
import java.io.PrintWriter;

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
    
            
    public static void main(String[] args) {
        if (args.length < 2){
            System.out.println("Zła ilość argumentów. ");
            System.exit(1);
        }
        
        
        SocketConnection sc = new SocketConnection (args[0], args[1]);
        in = sc.getIn();
        out = sc.getOut();
        GlowneOkienko go = new GlowneOkienko();
        go.setVisible(true);
    }
}
