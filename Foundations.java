import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Foundations extends JPanel{
    
    public Color backgroundColor = new Color(14, 120, 71);
    public Card card;

    public Foundations() {
        card = new Card(0, 'x');

        // Settings for foundations
        this.setBackground(backgroundColor);
        this.setBounds(325, 0, 525, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));
        this.initializeFoundation();
    }
    void initializeFoundation() {

        for (int i = 1; i < 5; i++) {
            card.number = i;
            ImageIcon fdCard = card.getFaceDown();
            JLabel labelCard = new JLabel(fdCard);
            this.add(labelCard);
        }
    }
}