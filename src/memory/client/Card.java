package memory.client;

public class Card {
    private int no;
    private boolean revealed;
    public String bgpath;
    public String fgpath;
    private boolean taken;
    
    public Card(int number, String fg) {
        no = number;
        revealed = false;
        fgpath = fg;
        bgpath = "0";
        taken = false;
    }
    
    public Card(Card c) {
        Card card = new Card(c.no, c.fgpath);
        card.revealed = c.revealed;
    }
    
    public void reveal(){
        this.revealed = true;
    }
    
    public void unreveal(){
        this.revealed = false;
    }
    
    public boolean isRevealed() {
        if(this.revealed == true) {
            return true;
        }
        else return false;
    }
   
    public boolean equal(Card c){
        if (this.no == c.no)
        {
        return true;
        }
        else return false;
    }
    
    public static boolean equal(Card c1, Card c2){
        //if (c1.no == c2.no)
        if (c1.fgpath.equals(c2.fgpath)){
        return true;
        }
        else return false;
    }
    
    public String getPath(){
        String path = "";
        if(this.isRevealed()){
            path = this.fgpath+".jpg";
        }
        else path = this.bgpath+".jpg";
        return path;
    }
    
    public boolean isTaken(){
        if (taken) return true;
        else return false;
    }
    
    public void setTaken(boolean b){
        taken = b;
    }
}