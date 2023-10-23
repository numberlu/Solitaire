import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

class Tableau extends JPanel {
    public Color backgroundColor = new Color(14, 120, 71);
    public Card card = new Card();
    // For layering
    public JLayeredPane layeredPane = new JLayeredPane();
    

    ArrayList<Card> shuffledCards;

    // All stacks of tableau will be in tabStacks
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList();

    // Inidividual column stack
    ArrayList<Card> tabStack = new ArrayList();
      


    // Tableau stack
    public Tableau(ArrayList<Card> cards) {

        // Shuffled cards (28 cards in total)
        this.shuffledCards = cards;

        // Setting bounds for JLayeredPane
        layeredPane.setBounds(0, 0, 810, 550);

        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 810, 550);
        
        // this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 35, 30));
        // this.initializeTableauBottom();

        this.setLayout(null);
        this.initializeTableuStack();

    }
    
    /////////////////////////////////////////////
    // Not sure if this class is needed//
    /////////////////////////////////////////////
    void initializeTableauBottom() {
        for (int i = 1; i <= 7; i++) {
            ImageIcon bottomCard = card.getCardBottom();
            JLabel labelCard = new JLabel(bottomCard);
            this.add(labelCard);
        }
    }

    /**.
     * 
     * This will set the cards positions accordingly when first launching the game.
     * 
     * 
     * @param col Current Tableau column
     * @param row Number of current card on stack
     * @param currentCard card with its attributes
     */
    void displayInitialTableauStack(int col, int row, Card currentCard) {
        // When the cards are not at the top of the stack
        if (col > row) {
            // Getting card facedown image
            ImageIcon bottomCard = currentCard.getFaceDown();
            JLabel cardJLabel = new JLabel(bottomCard);

            // Setting the bounds for each card (Position of card, Dimensions of card)
            cardJLabel.setBounds(((73 * col) + (35 * (col + 1))), row * 35, 73,  97);

            // Adding each card into JlayeredPane with priority (2nd argument)
            layeredPane.add(cardJLabel, Integer.valueOf(row));

            // Adding layeredPane into JPanel for display
            this.add(layeredPane);
        
        // Last card on that column stack
        } else if (row == col) {
            // Getting card facedown image
            ImageIcon topCard = currentCard.getFaceUp();
            JLabel cardJLabel = new JLabel(topCard);

            // Setting the bounds for each card (Position of card, Dimensions of card)
            cardJLabel.setBounds(((73 * col) + (35 * (col + 1))), row * 35, 73,  97);

            // Adding each card into JlayeredPane with priority (2nd argument)
            layeredPane.add(cardJLabel, Integer.valueOf(row));

            // Adding layeredPane into JPanel for display
            this.add(layeredPane);
        }
    }
    
    /**.
     * Initialises the tableau stack when the game is first launched
     */
    void initializeTableuStack() {

        // Column loop (column stack)
        for (int i = 0; i < 7; i++) {

            // Row loop (number of cards on stack)
            for (int j = 0; j < i + 1; j++) {


                // Adding card into an individual tableau stack
                this.tabStack.add(this.shuffledCards.get(0));

                // To display cards
                this.displayInitialTableauStack(i, j, this.shuffledCards.get(0));

                // Removing that card form the shuffled cards
                this.shuffledCards.remove(0);
     
            }
            // Adding the individual arraylist (column stack) into tabStacks
            tabStacks.add(tabStack);

            // Clearing out all elements in tabStack
            tabStack.removeAll(tabStack);
        }
    }
}


