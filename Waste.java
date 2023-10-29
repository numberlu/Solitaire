import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class deals with intializing and actions happening in waste.
 */
public class Waste extends JPanel {
    public Color backgroundColor = new Color(14, 120, 71);
    Card cardInWaste;
    JLabel card = new JLabel();
    boolean isEmpty = true;
    int index = 1;
    ArrayList<Card> cards = new ArrayList<>();
    Tableau tableau;
    Foundations foundations;
    Deck deck;
    
    /**
     * Initializes waste to be used in LaunchPage.
     * @param cards cards in the deck
     * @param tableau the board of the game
     * @param foundations the piles 
     * @param deck deck
     */
    Waste(ArrayList<Card> cards, Tableau tableau, Foundations foundations, Deck deck) {
        this.tableau = tableau;
        this.cards = cards;
        for (int index = 0; index < cards.size(); index++) {
            this.cards.get(index).isFaceUp = true;
        }
        this.foundations = foundations;
        this.deck = deck;
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

        if (index == cards.size() - 1) {
            deck.endOfDeck();
        } else if (index == cards.size()) {
            index = 0;
            deck.startOfDeck();
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
        for (int col = 0; col < 7; col++) {
            if (tableau.kingToEmpty(cardInWaste, col)) {
                // King moved to an empty column in the tableau
                tableau.setCardOnTableau(cardInWaste, col, 0);
                tableau.tabStacks.get(col).add(cardInWaste);
                cardInWaste.col = col;
                cardInWaste.row = 0;
                
                // Make the card interactable (add mouse listener)
                cardInWaste.label.addMouseListener(tableau);
                
                // Update GUI
                tableau.tabLayeredPanes.get(col).repaint();
                
                // Remove the card from the waste
                this.card.setIcon(cardInWaste.getCardBottom());
                this.cards.remove(cardInWaste);
                this.isEmpty = true;
                
                // Exit the method after moving the card
                return;
            }
        }

        if (!foundations.addCardToFoundation(cardInWaste)
                && !tableau.addCardToTableau(cardInWaste))  {
            return;
        }

        cardInWaste.label.addMouseListener(tableau);
        this.card.setIcon(cardInWaste.getCardBottom());
        this.cards.remove(cardInWaste);
        this.isEmpty = true;
    }
}
