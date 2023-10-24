import java.util.ArrayList;
import java.util.Collections;

class Stack {
    public ArrayList<Card> cardStack = new ArrayList<>();

    // Tableau shuffled cards
    public ArrayList<Card> tabStack = new ArrayList<>();
    
    // Deck shuffled cards
    public ArrayList<Card> deckStack = new ArrayList<>();

    /**
     * Creates an ArrayList (a stack) of playing cards.
     */
    public void createCardStack() {
        for (int i = 1; i <= 13; i++) {
            Card cardS = new Card(i, 's');
            this.cardStack.add(cardS);
            Card cardC = new Card(i, 'c');
            this.cardStack.add(cardC);
            Card cardH = new Card(i, 'h');
            this.cardStack.add(cardH);
            Card cardD = new Card(i, 'd');
            this.cardStack.add(cardD);
        }
    }

    /**
     * Calls a method that creates an ArrayList of playing cards and shuffles it.
     */
    public void createShuffledCardStack() {
        createCardStack();
        Collections.shuffle(this.cardStack);

        // Shuffling cards for tableau
        for (int i = 0; i < 28; i++) {
            this.tabStack.add(this.cardStack.get(i));
        }

        // Shuffling cards for deck
        for (int j = 28; j < this.cardStack.size(); j++) {
            this.deckStack.add(this.cardStack.get(j));
        }
    }

    public void popCard() {
        // Removes the last element in the Array list
        cardStack.remove(cardStack.size() - 1);
    }
}
