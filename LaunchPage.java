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
    public JPanel tableau = new JPanel();
    public JPanel deck = new JPanel();
    public JLayeredPane stock = new JLayeredPane();
    public JPanel foundations = new JPanel();
    ImageIcon fdSpades = new ImageIcon("cards\\fpBase01.gif");
    ImageIcon fdHearts = new ImageIcon("cards\\fpBase02.gif");
    ImageIcon fdClubs = new ImageIcon("cards\\fpBase03.gif");
    ImageIcon fdDiamonds = new ImageIcon("cards\\fpBase04.gif");
    JLabel labelSpades = new JLabel(fdSpades);
    JLabel labelHearts = new JLabel(fdHearts);
    JLabel labelClubs = new JLabel(fdClubs);
    JLabel labelDiamonds = new JLabel(fdDiamonds);
    ImageIcon cardBack = new ImageIcon(new ImageIcon("cards\\card back black.png")
        .getImage().getScaledInstance(73, 97, Image.SCALE_SMOOTH));

    JLabel back = new JLabel(cardBack);

    LaunchPage() {
        tableau.setBackground(backgroundColor);
        tableau.setBounds(0, 150, 825, 550);
        
        deck.setBackground(backgroundColor);
        deck.setBounds(0, 0, 315, 150);
        deck.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));

        deck.add(stock);
        
        foundations.setBackground(backgroundColor);
        foundations.setBounds(315, 0, 515, 150);
        foundations.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        foundations.add(labelSpades);
        foundations.add(labelHearts);
        foundations.add(labelClubs);
        foundations.add(labelDiamonds);
        
        deck.add(back);
        
        JFrame frame = new JFrame();
        frame.setTitle("Solitaire");
        frame.setSize(825, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(foundations);
        frame.add(deck);
        frame.add(tableau);
        
        ImageIcon image = new ImageIcon("cards\\playing-cards.png");
        frame.setIconImage(image.getImage());
    }
}
