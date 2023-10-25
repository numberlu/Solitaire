import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class Tableau extends JPanel implements MouseInputListener{
    public Color backgroundColor = new Color(14, 120, 71);

    Foundations foundations;

    // For layering
    public JLayeredPane layeredPane = new JLayeredPane();

    ArrayList<Card> shuffledCards;

    // All stacks of tableau will be in tabStacks
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList<>();

    // Inidividual column stack
    ArrayList<Card> tabStack = new ArrayList<>();


    /**
     * Tableau board.
     * @param cards cards that are initialized on the board upon the start of the game.
     */
    public Tableau(ArrayList<Card> cards, Foundations foundations) {
        // Shuffled cards (28 cards in total)
        this.shuffledCards = cards;

        this.foundations = foundations;

        // Setting bounds for JLayeredPane
        layeredPane.setBounds(0, 0, 810, 550);

        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 810, 550);

        this.setLayout(null);
        this.initializeTableuStack();
    }

    
    /**.
     * Initialises the tableau stack when the game is first launched
     */
    void initializeTableuStack() {
        int index = 0;
        // Column loop (column stack)
        for (int col = 0; col < 7; col++) {

            // Row loop (number of cards on stack)
            for (int row = 0; row < col + 1; row++) {
                shuffledCards.get(index).col = col;
                shuffledCards.get(index).row = row;

                // Adding card into an individual tableau stack
                this.tabStack.add(this.shuffledCards.get(index));

                if (col > row) {
                    shuffledCards.get(index).label.setIcon(shuffledCards.get(index).getFaceDown());
                    shuffledCards.get(index).isFaceUp = false;

                } else if (row == col) { // Last card on that column stack
                    shuffledCards.get(index).isFaceUp = true;
                }
                
                setCardOnTableau(index, col, row);

                index++;
            }
            // Adding the individual arraylist (column stack) into tabStacks
            tabStacks.add(tabStack);

            // Clearing out all elements in tabStack
            tabStack.removeAll(tabStack);
        }
    }

    /**
     * Method is used to initialize board of the game and to move cards during the game.
     * @param index helps to return the correct card
     * @param col column of the board
     * @param row row of the board
     */
    public void setCardOnTableau(int index, int col, int row) {
        shuffledCards.get(index).label.setBounds(((73 * col) + (35 * (col + 1))),
                    row * 35, 73,  97);

        shuffledCards.get(index).label.addMouseListener(this);

        // Adding each card into JlayeredPane with priority (2nd argument)
        layeredPane.add(shuffledCards.get(index).label, Integer.valueOf(row));

        // Adding layeredPane into JPanel for display
        this.add(layeredPane);
    }

    /**
     * TODO This is the part that I failed miserably at.
     * I had an idea to make so that every column had it's own layered pane by doing
     * ArrayList 'JLayeredPane'* = new ArrayList<>(7); but that didn't work out the way i planned
     * I reckon we could achieve smth by using the tabStacks you have created, but my brain is 
     * shutting of for today/few hours
     * *(vs code doesn't like when i put smth between <> - it thinks it's html lol)
     * @param row row   
     * @param col column
     * @param index index
     */
    void removeCardFromTableau(int row, int col, int index) {
        // JLabel empty = new JLabel(new ImageIcon("cards\\emptycard."));
        // empty.setBounds(((73 * col) + (35 * (col + 1))),
        //             row * 35, 73,  97);
        // layeredPane.add(empty, Integer.valueOf(row));
        // this.add(layeredPane);
        this.layeredPane.remove(shuffledCards.get(index).label);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //finds which card i'm caressing with my cursor
        for (int index = 0; index < shuffledCards.size(); index++) {
            //checks if it got the right card and if it's face up
            if (e.getSource() == shuffledCards.get(index).label 
                            && shuffledCards.get(index).isFaceUp) {
                if (foundations.addCardToFoundation(shuffledCards.get(index))) {
                    removeCardFromTableau(shuffledCards.get(index).row,
                         shuffledCards.get(index).col, index);
                }
            }
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


