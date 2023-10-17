import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

class LaunchPage {
    public Color backgroundColor = new Color(14, 120, 71);

    // Creating objects
    public Tableau tableau = new Tableau(); // Changed JPanel to Tableau
    public JPanel deck = new JPanel();
    public Stock stock = new Stock();
    public Foundations foundations = new Foundations();

    ImageIcon cardBack = new ImageIcon(new ImageIcon("cards\\card back black.png")
         .getImage().getScaledInstance(73, 97, Image.SCALE_SMOOTH));

    JLabel back = new JLabel(cardBack);

    LaunchPage() {
       
        deck.setBackground(backgroundColor);
        deck.setBounds(0, 0, 315, 150);
        deck.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        deck.add(stock);
                
        deck.add(back);
        
        // Window setup
        JFrame frame = new JFrame();
        frame.setTitle("Solitaire");
        frame.setSize(825, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Does not allow change of window size
        frame.setResizable(false); 
        // Sets window to middle of screen
        frame.setLocationRelativeTo(null);


        // Adding stacks
        frame.add(foundations);
        frame.add(deck);
        frame.add(tableau);

        // Window icon set up
        ImageIcon image = new ImageIcon("cards\\playing-cards.png");
        frame.setIconImage(image.getImage());
        
    }
}
