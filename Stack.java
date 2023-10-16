import java.util.ArrayList;
import java.util.Collections;

class Stack {

    public ArrayList<Card> stack;

    public ArrayList<Card> createShuffledCardStack() {
        for (int i = 1; i <= 13; i++) {
            Card cardS = new Card(i, 's');
            stack.add(cardS);
            Card cardC = new Card(i, 'c');
            stack.add(cardC);
            Card cardH = new Card(i, 'h');
            stack.add(cardH);
            Card cardD = new Card(i, 'd');
            stack.add(cardD);
        }
        Collections.shuffle(stack);
        return stack;
    }
}
