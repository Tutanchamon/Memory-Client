package memory.client;

public class Card {
    public int no;
    public boolean revealed;
    
    public Card(int number) {
        no = number;
        revealed = false;
    }
    
    public Card (Card c) {
        Card card = new Card(c.no);
        card.revealed = c.revealed;
    }
    
    public void reveal(){
        this.revealed = true;
    }
   
    public boolean equal(Card c){
        if (this.no == c.no)
        {
        return true;
        }
        else return false;
    }
    
    public static boolean equal(Card c1, Card c2){
        if (c1.no == c2.no)
        {
        return true;
        }
        else return false;
    }
}