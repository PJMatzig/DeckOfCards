import java.util.Arrays;

public class DeckOfCards {
    private int suits;
    private int ranks;
    private Card [] deck;
    private Card[] hand;

    //the constructor

    /**
     *
     * @param suits
     * @param ranks
     */
    public DeckOfCards (int suits,int ranks){
        this.suits=suits;
        this.ranks=ranks;
        this.deck=new Card[this.suits*this.ranks];
        int counter=0;
        for (int i=1;i<=this.suits;i++){
            for (int j=1;j<=this.ranks;j++){
                deck[counter]=new Card(i,j);
                counter++;
            }
        }
    }

    public int getSuits() {
        return suits;
    }

    public int getRanks() {
        return ranks;
    }

    public void shuffle(){
        int randomNumberOne;
        for(int i=0;i<deck.length;i++){
            randomNumberOne=(int)(Math.random()*deck.length);

            while(randomNumberOne==i){
                randomNumberOne=(int)(Math.random()*deck.length);

            }
            Card card1=deck[i];
            deck[i]=deck[randomNumberOne];
            deck[randomNumberOne]=card1;
        }
    }
    public Card [] deal(int numCards) {
        hand = new Card[numCards];
        Card [] tempDeck=new Card[deck.length];
        for (int i = 0; i < hand.length; i++) {
            hand[i] = deck[i];
        }
//        //put the cards in hand[] on the bottom of the tempDeck
//        for(int i= 0;i<hand.length;i++){
//            tempDeck[deck.length-1-i]=deck[i];
//            deck[i]=null;
//        }
//        //this code fills temp deck with the cards from deck[] that were not put on the bottom
//        //maintaining the proper order.
//        int counter=0;
//        for(Card card:deck){
//            if (card!=null){
//                tempDeck[counter]=card;
//                counter++;
//            }
//        }
//        deck=tempDeck;
        return hand;
    }
    public int[] histogram (int handSize) {
        int[] histoArray = new int[maximumValue()*handSize];

        for(int i=1;i<=100000;i++){
            shuffle();
            Card [] hand=deal(handSize);
            //System.out.println("hand: "+Arrays.toString(hand));
            int totalValue=0;
            for(Card card:hand){
               totalValue+=card.getValue();
               //System.out.println(totalValue);
            }
            histoArray[totalValue]+=1;
        }

        return histoArray;
    }
    public Card getTopCard(int cardNumber){
        return deck[cardNumber];
    }
    public int getHandValue(){
        int handValue=0;
        Card c1;
        for (Card card : hand) {
            c1 = card;
            handValue += c1.getValue();
        }
        return handValue;
    }


    public int minimumValue(){
        return 1;
    }

    public int maximumValue(){
        return suits*ranks;
    }
    @Override
    public String toString() {
       return "Deck size: "+suits*ranks+". Minimum card value: "+minimumValue()+". Maximum card value: "+maximumValue()+".";
    }
}
