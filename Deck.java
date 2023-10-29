import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Deck extends JPanel {

    public Color backgroundColor = new Color(14, 120, 71);
    ImageIcon cardBack = new ImageIcon(new ImageIcon("cards\\card back black.png")
         .getImage().getScaledInstance(73, 97, Image.SCALE_SMOOTH));
    ImageIcon empty = new ImageIcon("cards\\endofdeck.png");
    JLabel back;

    /**
     * Sets Deck's (JPanel's) interface.
     */
    public Deck() {
        this.setBackground(backgroundColor);
        this.setBounds(0, 0, 137, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));
        this.back = new JLabel(cardBack);
        this.add(back);
    }

    /**
     * Method paints an circular arrow to indicate the end of the deck.
     */
    void endOfDeck() {
        back.setIcon(empty);
        back.repaint();
    }

    /**
     * Method paints a face-down card to indicate the start of the deck.
     */
    void startOfDeck() {
        back.setIcon(cardBack);
        back.repaint();
    }


}
