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
    public JLayeredPane layeredPane = new JLayeredPane();

    ArrayList<Card> tabCards;

    // All stacks of tableau
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList();

    // Inidividual column stack
    ArrayList<Card> tabStack = new ArrayList();
      


    // Tableau stack
    public Tableau(ArrayList<Card> cards) {

        // Shuffled cards (28 cards in total)
        this.tabCards = cards;


        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 810, 550);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 35, 30));
        this.initializeTableauBottom();

        this.setLayout(null);
        this.initializeTableuStack();

    }

    void initializeTableauBottom() {
        for (int i = 1; i <= 7; i++) {
            ImageIcon bottomCard = card.getCardBottom();
            JLabel labelCard = new JLabel(bottomCard);
            this.add(labelCard);
        }
    }

    void displayInitialTableauStack(int col, int row) {
        ImageIcon bottomCard = card.getFaceDown();
        JLabel cardJLabel = new JLabel(bottomCard);
        cardJLabel.setBounds(((73 * col) + (35 * (col + 1))), row * 35, 73,  97);
        this.add(cardJLabel);
    }
    
    void initializeTableuStack() {

        // Column loop (column stack)
        for (int i = 0; i < 7; i++) {

            // Row loop (number of cards on stack)
            for (int j = 0; j < i + 1; j++) {


                // Adding card into an individual tableau stack
                this.tabStack.add(this.tabCards.get(0));
                // Removing that card form the shuffled cards
                this.tabCards.remove(0);

                this.displayInitialTableauStack(i, j);
                

                
                // When there are more cards above
                // if (i == j) {

                    
                // }
                // if (i > j) {              
                //     // card.getCardBottom(); //pseudo code
                //     // tabStack.get(j).isFaceUp = false;

                // } else if (i == j) {
                //     // tabCards.placeFaceUp(this.tabCards.get(0));
                //     card.isFaceUp = true;
                //     tabStacks.add(tabStack);
                // }
            }
            tabStacks.add(tabStack);
            tabStack.removeAll(tabStack);
        }
    }
}


