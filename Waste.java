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
    Foundations foundations;
    
    Waste(ArrayList<Card> cards, Tableau tableau, Foundations foundations) {
        this.tableau = tableau;
        this.cards = cards;
        this.foundations = foundations;
        this.add(card);
        this.setBackground(backgroundColor);
        this.setBounds(137, 0, 228, 150);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
    }
    
    /**
     * Method checks if the waste is empty and adds a card.
     */
    void addCardToWaste() {
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

    /**
     * When waste is clicked, it moves the card to foundations, tableau or does nothing.
     * For now, moving to foundations and doing nothing is realized.
     */
    void removeCardFromWaste() {
        if (cardInWaste.number == 13) {
            if (tableau.kingToEmpty(cardInWaste)) {
                this.card.setIcon(cardInWaste.getCardBottom());
                this.cards.remove(cardInWaste);
                this.isEmpty = true;
                cardInWaste.isFaceUp = true;
                tableau.setCardOnTableau(cardInWaste, cardInWaste.col, 0);

                return;
            }
        }
        if (!foundations.addCardToFoundation(cardInWaste) 
                && !tableau.addCardToTableau(cardInWaste))  {
            return;
        }
        this.card.setIcon(cardInWaste.getCardBottom());
        this.cards.remove(cardInWaste);
        this.isEmpty = true;

    }
}
