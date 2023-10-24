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
    JLabel back = new JLabel(cardBack);

    /**
     * Sets Deck's (JPanel's) interface.
     */
    public Deck() {
        this.setBackground(backgroundColor);
        this.setBounds(0, 0, 137, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));
        this.add(back);
    }
}
