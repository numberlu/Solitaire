import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Tableau extends JPanel {
    public Color backgroundColor = new Color(14, 120, 71);
    public Card card = new Card();
    ArrayList<Card> tabCard;

    // Tableau stack
    public Tableau(ArrayList<Card> cards) {

        ArrayList<Card> tabCard = cards;

        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 810, 550);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 35, 30));
        this.initializeTableauBottom();
        this.initializeTableuStack();

    }

    void initializeTableauBottom() {
        for (int i = 1; i <= 7; i++) {
            ImageIcon bottomCard = card.getCardBottom();
            JLabel labelCard = new JLabel(bottomCard);
            this.add(labelCard);
        }
    }

    void initializeTableuStack() {
        card.createShuffledCardStack();

        // // Column loop
        // for (int i = 0; i < 7; i++) {
        //     // Row loop (number of cards on stack)
        //     for (int j = 0; j < 7; j++) {
                
        //         if (i > j) {
        //             // THE IDEA:
        //             // First, get the card from gettCardBottom, the  follow the
        //             // initializeTableauBottom  method to get card image, then set the bounds,
        //             // and do flowLayout. Dont forget to make an arraylist varible for each tableau 
        //             // stack

                    
        //             card.getCardBottom(); //pseudo code
        //             // Removing last element
        //             tabCard.remove(tabCard.size() - 1);

        //             card.isFaceUp = false;
        //         } else if (i == j) {
        //             tabCard.placeFaceUp(i, j, tabCard);
        //             card.isFaceUp = true;
        //         }
        //     }
        // }
    }
}
