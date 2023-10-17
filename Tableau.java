import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Tableau extends JPanel {
    public Color backgroundColor = new Color(14, 120, 71);
    public Card card;
    

    // Tableau stack
    public Tableau() {
        card = new Card(0, 'x');

        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 825, 550);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 55, 30));
        this.initializeTableauBottom();

    }

    void initializeTableauBottom() {
        for (int i = 1; i < 7; i++) {
            ImageIcon bottomCard = card.getCardBottom();
            JLabel labelCard = new JLabel(bottomCard);
            this.add(labelCard);
        }
    }

    void initializeTableauStack() {
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
