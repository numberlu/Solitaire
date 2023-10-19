import java.util.ArrayList;
import java.util.Collections;

class Stack {
    public ArrayList<Card> cardStack = new ArrayList<>();

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
    }

    public void popCard() {
        // Removes the last element in the Array list
        cardStack.remove(cardStack.size() - 1);
    }
}
