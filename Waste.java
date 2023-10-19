import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Waste extends JLayeredPane{
    int cardsInWaste;
    int index;
    Stack stack;
    Waste() {
        this.setBackground(Color.red);
        this.setBounds(100, 0, 215, 150);
    }

    void addCardToWaste() {
        System.out.println("Hello");
        JLabel card1 = new JLabel(stack.cardStack.get(0).image);
        this.add(card1);
    }

    void returnCardToDeck() {

    }

    int getCardsInWaste() {
        return cardsInWaste;
    }
}
