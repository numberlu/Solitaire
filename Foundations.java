import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class deals with initializing foundations and actions performed to or in them.
 */
public class Foundations extends JPanel {
    
    public Color backgroundColor = new Color(14, 120, 71);
    int spades = 0;
    int hearts = 0;
    int diamonds = 0;
    int clubs = 0;
    JLabel spadesLabel = new JLabel(new ImageIcon("cards\\fpBase01.gif"));
    JLabel heartsLabel = new JLabel(new ImageIcon("cards\\fpBase02.gif"));
    JLabel clubsLabel = new JLabel(new ImageIcon("cards\\fpBase03.gif"));
    JLabel diamondsLabel = new JLabel(new ImageIcon("cards\\fpBase04.gif"));
    ArrayList<Card> cardsInFoundation = new ArrayList<>();

    public Foundations() {
        // Settings for foundations
        this.setBackground(backgroundColor);
        this.setBounds(365, 0, 545, 150);
        this.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING, 20, 20));
        updateFoundations();
    }

    void updateFoundations() {
        this.add(spadesLabel);
        this.add(heartsLabel);
        this.add(clubsLabel);
        this.add(diamondsLabel);
    }

    public boolean addCardToFoundation(Card card) {
        boolean hasCardMoved = false;

        switch (card.symbol) {
            case ('s'): {
                if (card.number == spades + 1) {
                    this.spadesLabel.setIcon(card.image);
                    this.spades++;
                    hasCardMoved = true;
                }
                break;
            }
            case ('h'): {
                if (card.number == hearts + 1) {
                    this.heartsLabel.setIcon(card.image);
                    this.hearts++;
                    hasCardMoved = true;
                }
                break;
            }
            case ('c'): {
                if (card.number == clubs + 1) {
                    this.clubsLabel.setIcon(card.image);
                    this.clubs++;
                    hasCardMoved = true;
                }
                break;
            }
            case ('d'): {
                if (card.number == diamonds + 1) {
                    this.diamondsLabel.setIcon(card.image);
                    this.diamonds++;
                    hasCardMoved = true;
                }
                break;
            }
            default:
                break;
        }
        updateFoundations();

        if (hasCardMoved) {
            cardsInFoundation.add(card);
        }

        return hasCardMoved;
    }
    
}