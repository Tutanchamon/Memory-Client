/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author blazej
 */
public class SocketConnection {
    
    // atrybuty klasy
    Socket gniazdo = null;
    PrintWriter out = null;
    BufferedReader in = null;
    InputStream is = null;
    
    // konstruktor klasy
    SocketConnection(String address, String port){
        try {
            int pn = Integer.parseInt(port);
            gniazdo = new Socket(address, pn);
            out =  new PrintWriter(gniazdo.getOutputStream(), true);
           // in = new BufferedReader(new InputStreamReader(gniazdo.getInputStream()));
            is = gniazdo.getInputStream();
            System.out.print("Uzyskałem połączenie z: "+address+":"+pn);
            
        }
        catch (UnknownHostException e) {
            System.err.println("Nieznany host");
            System.exit(1);
        }
      
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }
        
    }
    
    public PrintWriter getOut(){
        return out;
    }
    
    public BufferedReader getIn(){
        return in;
    }
    
    public InputStream getIs(){
        return is;
    }
    
    public Socket getSock(){
        return gniazdo;
    }
    
    public void close(){
        try {
            out.close();
            in.close();
            gniazdo.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
