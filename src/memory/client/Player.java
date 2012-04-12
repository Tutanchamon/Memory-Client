package memory.client;

public class Player {
    private int noCards;
    private int noPlayer;
    
    public Player() {
        noCards = 0;
        noPlayer = 0;
    }
    
    public void increaseCards() {
        this.noCards += 2;
    }
    
    public int getPlayer() {
        return this.noPlayer;
    }
    
    public int getCards() {
        return this.noCards;
    }
    
    public int wins(Player p) {
        if (this.getCards() > p.getCards()) return 1;
        else if (this.getCards() == p.getCards()) return 0;
        else return -1;
    }
}
