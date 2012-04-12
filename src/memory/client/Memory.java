package memory.client;
import java.io.*;
import java.util.Vector;
import java.util.Random;

public class Memory {
    public static Vector<Player> players = new Vector<Player>();
    public static Vector<Player> winners = new Vector<Player>();
    public static Vector<Card> deck = new Vector<Card>();
    public static int pairs = 15;
    public static Vector<Card> deal = new Vector<Card>();

    public static void initialise(){
        int gracze = 0;
        
        while (gracze < 2 || gracze > 4){
            System.out.println("Specify the number of players: ");
            try{
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                String s = br.readLine();
                gracze = Integer.parseInt(s);
            }
            catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            if (gracze < 2 || gracze > 4) {
                System.out.println("You set " + gracze + "players. There can only be 2, 3 or 4 players.");
            }
            else break;
        }
        System.out.println("There are " + gracze + " players.");
        
        for (int i = 0; i < gracze; i++) {
            Player player = new Player();
            players.add(player);
        }
    }

    public static void score(){
        int maxCards = players.elementAt(0).getCards();
        for (int i=1; i < players.size(); i++) {
            if (players.elementAt(i).getCards() > maxCards){
                maxCards = players.elementAt(i).getCards();
            }
        }
        for (int i=0; i < players.size(); i++) {
            if (players.elementAt(i).getCards() == maxCards){
                winners.add(players.elementAt(i));
            }
        }
    }
    
    public static int howManyCardsLeft() {
        int left = 0;
        for (int i = 0; i < deal.size(); i++) {
            if (!deal.elementAt(i).revealed) {
                left++;
            }
        }
        return left;
    }
    
    public static void createDeck(int pairs) {
        for (int i=1; i < 2*pairs; i++){
            Card card1 = new Card(i);
            Card card2 = new Card(card1);
            deck.add(card1);
            deck.add(card2);
        }
    }
    
    public static void dealCards(){
        
    }
    
    public static void main(String[] args) {
        int card1 = 0;
        int card2 = 0;
        
        initialise();
        createDeck(pairs);
        dealCards();
        while (howManyCardsLeft() > 0) {
            for (int i = 1; i <= players.size(); i++){
                do {
                    System.out.print("It's Player" + i + "'s turn.");
                    try{
                        InputStreamReader isr = new InputStreamReader(System.in);
                        BufferedReader br = new BufferedReader(isr);
                        String s = br.readLine();
                        card1 = Integer.parseInt(s);
                    }
                    catch(IOException e){
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    try{
                        InputStreamReader isr = new InputStreamReader(System.in);
                        BufferedReader br = new BufferedReader(isr);
                        String s = br.readLine();
                        card2 = Integer.parseInt(s);
                    }
                    catch(IOException e){
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    if (deal.elementAt(card1).equal(deal.elementAt(card2))){
                        players.elementAt(i).increaseCards();
                        deal.elementAt(card1).reveal();
                        deal.elementAt(card2).reveal();
                    }
                    if (howManyCardsLeft() == 0) {
                        break;
                    }
                } while(deal.elementAt(card1).equal(deal.elementAt(card2)));
                if (howManyCardsLeft() == 0) {
                    break;
                }
            }
            if (howManyCardsLeft() == 0) {
                break;
            }
        score();
        if (winners.size() > 1) {
            System.out.print("The winners are: ");
        }
        else System.out.print("The winner is: ");
        
        for (int i = 1; i <= winners.size(); i++) {
            System.out.print("Player " + winners.elementAt(i).getPlayer());
        }
        
        if (winners.size() == 0) {
            System.out.print("An unexpected error occurred - there are no winners.");
        }
        }
    }
}
