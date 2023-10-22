import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class LaunchPage extends JFrame implements MouseInputListener{
    public Color backgroundColor = new Color(14, 120, 71);

    // Creating objects
    // Cards already shuffled
    public Card cards = new Card();

    public Tableau tableau = new Tableau(cards.tabStack); // Changed JPanel to Tableau
    public Deck deck = new Deck(cards.deckStack);

    public Foundations foundations = new Foundations();
    public Waste waste;

    LaunchPage() {
        // Adding components of the board to the frame
        this.add(tableau);
        this.add(foundations);
        this.add(deck);

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
        if (e.getSource() == deck) {  //TODO no idea how to reach waste from here lol
            waste.addCardToWaste();
        }

        //IDK how this method works
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }
}
