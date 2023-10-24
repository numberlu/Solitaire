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

    // Tableau and deck take arguments of shuffled cards respectively
    public Tableau tableau = new Tableau(cards.tabStack);
    public Waste waste = new Waste(cards.deckStack, tableau);
    public Deck deck = new Deck();

    public Foundations foundations = new Foundations();
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
        layeredPane.add(foundations);
        deck.addMouseListener(this);
        layeredPane.add(deck);
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
            //check for possible moves if so waste isEmpty = true
            waste.removeCardFromWaste();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
}
