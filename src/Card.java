public class Card {
    private int suit;
    private int rank;
    private int value;
    public Card(int suit, int rank){
        this.suit=suit;
        this.rank=rank;
        this.value=this.suit*this.rank;
    }
    public int getSuit(){
        return this.suit;
    }
    public int getRank(){
        return this.rank;
    }
    public int getValue(){
        return this.value;
    }
    @Override
    public String toString() {
        return "S"+suit+"R"+rank; //("+value+"pts)";
    }
}
