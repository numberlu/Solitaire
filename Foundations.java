import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Foundations extends JPanel{
    
    public Color backgroundColor = new Color(14, 120, 71);
    public Card card;

    public Foundations(ArrayList<Card> cards) {
        card = new Card();

        // Settings for foundations
        this.setBackground(backgroundColor);
        this.setBounds(315, 0, 595, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.initializeFoundation();
    }

    void initializeFoundation() {

        for (int i = 1; i < 5; i++) {
            card.number = i;
            ImageIcon fdCard = card.getFoundations();
            JLabel labelCard = new JLabel(fdCard);
            this.add(labelCard);
        }
    }
}
