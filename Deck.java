import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Deck extends JPanel {

    public Color backgroundColor = new Color(14, 120, 71);
    public Waste waste = new Waste();
    ImageIcon cardBack = new ImageIcon(new ImageIcon("cards\\card back black.png")
         .getImage().getScaledInstance(73, 97, Image.SCALE_SMOOTH));
    JLabel back = new JLabel(cardBack);

    //gets the rest of the cards upon Tableau's initialization
    public ArrayList<Card> playingCards = new ArrayList<>(); 

    /**
     * Sets Deck's (JPanel's) interface.
     */
    public Deck(ArrayList<Card> cards) {
        this.setBackground(backgroundColor);
        this.setBounds(0, 0, 315, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));
        this.add(back);
        this.add(waste);
    }
}
