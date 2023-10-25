import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.event.MouseInputListener;

class LaunchPage extends JFrame implements MouseInputListener{
    public Color backgroundColor = new Color(21, 88, 67);

    // Creating objects
    // Cards already shuffled
    public Card cards = new Card();

    public Foundations foundations = new Foundations();

    // Tableau and waste take arguments of shuffled cards respectively
    public Tableau tableau = new Tableau(cards.tabStack, foundations);
    public Waste waste = new Waste(cards.deckStack, tableau, foundations);
    
    public Deck deck = new Deck();
    
    // JlayeredPane for tableau, foundation, deck and waste
    public JLayeredPane layeredPane = new JLayeredPane();

    /**
     * UI board.
     */
    LaunchPage() {
        // Adding components of the board to the frame

        layeredPane.setBounds(0, 0, 810, 700);
        
        // Adding the classes into JlayeredPane so that can overlap each other
        layeredPane.add(tableau);
        foundations.addMouseListener(this);
        layeredPane.add(foundations);
        deck.addMouseListener(this);
        layeredPane.add(deck);
        waste.addMouseListener(this);
        layeredPane.add(waste);

        // Adding JLayeredPane into JFrame
        this.add(layeredPane);

        // Window setup
        this.setTitle("Solitaire");
        this.setSize(810, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Does not allow change of window size
        this.setResizable(false); 
        // Sets window to middle of screen
        this.setLocationRelativeTo(null);

        // Window icon set up
        ImageIcon image = new ImageIcon("cards\\poker-cards.png");
        this.setIconImage(image.getImage());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == deck) {  
            waste.addCardToWaste();
        }
        if (e.getSource() == waste) {
            //removes card from waste if it is possible
            waste.removeCardFromWaste();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
