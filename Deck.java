import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.JPanel;

class Deck extends Stack {

    public Color backgroundColor = new Color(14, 120, 71);
    JPanel deck = new JPanel();

    public Deck() {
        deck.setBackground(backgroundColor);
        deck.setBounds(0, 0, 315, 150);
        deck.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));

    }
}
