import java.util.Scanner;

public class Main {

    public static int setSuits(Scanner keyboardOBJ){
        System.out.println("\nLet's build a deck of cards!");
        System.out.print("\nHow many suits are there in the deck? ");

        return keyboardOBJ.nextInt();
    }

    public static int setRanks(Scanner keyboardOBJ){
        System.out.print("\nHow many cards are in each suit? ");
        return keyboardOBJ.nextInt();
    }
    public static void analyzeDeck(DeckOfCards deck){
        int suits=deck.getSuits();
        int ranks=deck.getRanks();
        if (suits == 4 && ranks == 13) {
            System.out.println("\nA traditionalist, I see. Very good!");
        }
    }

    public static void deckMenu(DeckOfCards deck,int cardNumber, String userStrInput, Scanner keyboardOBJ) throws InterruptedException {
        System.out.println("\nDeck Information");
        System.out.println("The deck consists of "+deck.maximumValue()+" cards.");
        System.out.println("The lowest value for a card is: " + deck.minimumValue());
        System.out.println("The highest value of a card is: " + deck.maximumValue());
        System.out.println("The current top card is: " + deck.getTopCard(cardNumber));
        while(!userStrInput.equals("4")){
            System.out.println("\nOptions\n1.Shuffle\t2.Deal 1 hand\t3.Deal 100,000\t4.Quit");
            userStrInput=keyboardOBJ.nextLine();
            switch(userStrInput){
                case "1":
                    System.out.println("Shuffling!");
                    deck.shuffle();
                    deckMenu(deck,0,userStrInput,keyboardOBJ);
                    //clear the Scanner buffer
                    keyboardOBJ.nextLine();
                    break;
                case "2":
                    dealAHand(keyboardOBJ,deck);
                    keyboardOBJ.nextLine();
                    deckMenu(deck,0,userStrInput,keyboardOBJ);
                    break;
                case "3":
                    histogram(keyboardOBJ,deck);
                    keyboardOBJ.nextLine();
                    deckMenu(deck,0,userStrInput,keyboardOBJ);
                    break;
                case "4":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Enter 1, 2, 3 or 4");
                    deckMenu(deck,0,userStrInput,keyboardOBJ);
                    break;
            }

        }

    }
    public static void dealAHand(Scanner keyboardOBJ, DeckOfCards deckOne) throws InterruptedException {
        Card [] hand;
        System.out.println("Dealing a hand...");
        System.out.println("How many cards to deal? ");
        int numCards = keyboardOBJ.nextInt();
        hand=deckOne.deal(numCards);
        System.out.println("Your hand is:");
        for(Card card:hand){
            System.out.print(card+" ");
        }
        System.out.println("\nHand value: "+deckOne.getHandValue());
        Thread.sleep(3000);
    }

    public static void histogram(Scanner keyboardOBJ,DeckOfCards deckOne ) throws InterruptedException {
        int userNumInput;
        System.out.println("The histogramMethod");
        System.out.print("\nHow many cards in a hand? ");
        userNumInput= keyboardOBJ.nextInt();
        int [] histogramArray=deckOne.histogram(userNumInput);
        for (int i=0;i<histogramArray.length;i++){
            if (histogramArray[i] > 0) {
                String printString="";
                double numStars = Math.round((double) histogramArray[i]/100);
                if(numStars<1){
                    printString="-";
                }
                for(int j=1;j<=numStars;j++){
                    printString+="*";
                }
                System.out.println("Total of "+i+", "+histogramArray[i]+" times. \t"+printString);
                Thread.sleep(250);
            }

        }
        Thread.sleep(500);
    }

    public static void main(String[] args) throws InterruptedException {
        //set up the scanner object and the input variables
        Scanner keyboardOBJ = new Scanner(System.in);
        int suits;
        int ranks;
        String userStrInput="0";
        //set up the deck
        suits=setSuits(keyboardOBJ);
        ranks=setRanks(keyboardOBJ);
        DeckOfCards deckOne = new DeckOfCards(suits, ranks);
        //check to see if there are 4 suits with 13 ranks
        analyzeDeck(deckOne);
        //clear the Scanner buffer
        keyboardOBJ.nextLine();
        //display the menu
        deckMenu(deckOne,0,userStrInput,keyboardOBJ);
    }
}
