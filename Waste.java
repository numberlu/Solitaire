import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Waste extends JPanel{
    public Color backgroundColor = new Color(14, 120, 71);
    Card cardInWaste;
    JLabel card = new JLabel();
    boolean isEmpty = true;
    int index = 1;
    ArrayList<Card> cards = new ArrayList<>();
    Tableau tableau;
    
    Waste(ArrayList<Card> cards, Tableau tableau) {
        this.tableau = tableau;
        this.cards = cards;
        this.add(card);
        this.setBackground(backgroundColor);
        this.setBounds(137, 0, 315 - 137, 150);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
    }
    
    /**
     * Method checks if the waste is empty and adds a card.
     */
    void addCardToWaste() {
        System.out.println("hi");
        //if the card was removed, we remove that card(index) and stack moves up by one card
        if (isEmpty) { 
            index--;
        }
        //makes sure that we can reuse the deck after we reach its end
        if (index == cards.size()) {
            index = 0;
        }
        cardInWaste = cards.get(index);
        this.card.setIcon(cardInWaste.getFaceUp());
        isEmpty = false;
        index++;
    }

    void removeCardFromWaste() {
        this.isEmpty = true;
    }
}
