import java.awt.Color;
import javax.swing.JPanel;

class Tableau extends JPanel {
    public Color backgroundColor = new Color(14, 120, 71);
    public static Card card;
    Stack stack = new Stack();

    // Tableau stack
    public Tableau() {
        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 825, 550);
        this.initializeTableuStack();
    }

    void initializeTableuStack() {
        stack.createShuffledCardStack();

        // for (int i = 0; i < 7; i++) {
        //     for (int j = 0; j < 7; j++) {
        //         if (i > j) {
        //             card.placeFaceDown(i, j); //pseudo code
        //             card.isFaceUp = false;
        //         } else if (i == j) {
        //             card.placeFaceUp(i, j);
        //             card.isFaceUp = true;
        //         }
        //     }
        // }
    }
}
