/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author blazej
 */
public class Game {
    /*******************************/
    private static int selected = 0;
    private static Card sCard1, sCard2;
    /*******************************/
    private static void getArrayElements(){
        int ilosc = 30;
        int[] tablica = new int[30];
        byte[] bufor = new byte[2];
        InputStream buf = MemoryClient.is;
        
        for (int i = 0; i < ilosc; i++){
            try {
                int received = buf.read(bufor, 0, bufor.length);
                System.out.println();
                char[] charArr = (new String(bufor)).toCharArray();
                String numer_karty = String.copyValueOf(charArr);
                double numer1 = Double.parseDouble(numer_karty);
                int numer2 = (int) numer1;
                tablica[i] = numer2;
                System.out.println("Proba numer: "+i+"\tString numer_karty: "+numer_karty+"\t int numer2: "+numer2+"\t tablica["+i+"]: "+tablica[i]);
                MemoryClient.tablica = tablica;
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            
        }
    }
   private static void fileLoop(){
       PrintWriter pr = MemoryClient.out;
       
       System.out.println("Wchodzę do pętli w fileLoop()");
       for (int i = 0; i < 16; i++){
           
           System.out.println("Wysyłam żądanie po plik "+i+".jpg");
           pr.println(i+".jpg");
           getFile(i+".jpg");
       }
   } 
   
   private static void getFile(String filename){
       double rozmiar;
       
       try {
           InputStream buf = MemoryClient.is;
      
           int count;
           int odebrano;
           byte[] buffer = new byte[8];
           
           
             FileOutputStream fos = new FileOutputStream(filename);
           BufferedOutputStream bos = new BufferedOutputStream(fos);
           
           // odbieranie rozmiaru pliku
           odebrano = buf.read(buffer, 0, 8);
           System.out.println();
           for (int i=0; i < buffer.length; i++){
                System.out.print(buffer[i]);
                char mychar = (char) buffer[i];
                System.out.print(mychar);
               }
               System.out.println();
               char[] charArr = (new String(buffer)).toCharArray();
               for (int j=0; j < charArr.length; j++){
                   System.out.print(charArr[j]);
               }
               System.out.println();
               String rozmiartemp = String.copyValueOf(charArr);
               System.out.println("Rozmiartemp: "+rozmiartemp);
               rozmiar = Double.parseDouble(rozmiartemp);
               System.out.println("Rozmiar pliku do pobrania wynosi: "+rozmiar);
               count = 0;
               
                int odebrano_razem = 0;
                int licznik = 0;
                int pozostalo = (int) rozmiar;
                System.out.println("Wchodzę do pętli pobierania pliku");
                while (odebrano_razem < rozmiar){
                    byte[] dane = new byte[10240];

                    if (pozostalo >= dane.length) licznik = buf.read(dane, 0, dane.length);
                    if (pozostalo < dane.length) licznik = buf.read(dane, 0, pozostalo);
                    if (licznik == 0 || licznik == -1) break;
                    else System.out.println("Pobrano "+licznik+" bajtów danych");
                    odebrano_razem += licznik;
                    pozostalo -= licznik;
                    System.out.println("Łącznie odebrano: "+odebrano_razem);
                    System.out.println("Pozostało "+pozostalo);
                    System.out.println("Dodawanie danych do pliku ");
                    bos.write(dane, 0, licznik);
               }
                if (odebrano_razem != rozmiar){
                    System.out.println("Błąd w odbiorze pliku");
                }
                else {
                    System.out.println("Plik odebrany poprawnie i zapisany jako "+filename);
                }
                System.out.println("Zamykam bos i fos");
                bos.close();
                fos.close();      
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
                catch (NumberFormatException ex){
                   System.out.append("Wywołał się NumberFormatException: ");
                   ex.printStackTrace();
                }
       
   }
   
   public static void dodajWybor(Card karta){
        
        selected++;
        if (selected == 1) sCard1 = karta;
                
        System.out.println("selected wynosi: "+selected);
        if (selected == 2) {
            sCard2 = karta;
            if (Card.equal(sCard1, sCard2)){
                System.out.println("Parka!");
            }
            else {
                System.out.println("To nie jest parka");
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
                cb.setEnabled(true);
                cb.setSelected(false);
                
            }
        }
    }
    
}
