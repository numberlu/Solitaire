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

    ArrayList<Card> cards;

    // All stacks of tableau will be in tabStacks
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList<>();

    /**
     * Tableau board.
     * @param cards cards that are initialized on the board upon the start of the game.
     */
    public Tableau(ArrayList<Card> cards, Foundations foundations) {
        // Shuffled cards (28 cards in total)
        this.cards = cards;

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

            ArrayList<Card> tabStack = new ArrayList<>();
            // Row loop (number of cards on stack)
            for (int row = 0; row < col + 1; row++) {
                cards.get(index).col = col;
                cards.get(index).row = row;

                // Adding card into an individual tableau stack
                tabStack.add(this.cards.get(index));

                if (col > row) {
                    cards.get(index).label.setIcon(cards.get(index).getFaceDown());
                    cards.get(index).isFaceUp = false;

                } else if (row == col) { // Last card on that column stack
                    cards.get(index).isFaceUp = true;
                }
                
                setCardOnTableau(cards.get(index), col, row);

                index++;
            }
            // Adding the individual arraylist (column stack) into tabStacks
            this.tabStacks.add(tabStack);

            //DO NOT REMOVE.TABSTACK <33
        }
    }

    /**
     * Method is used to initialize board of the game and to move cards during the game.
     * @param index helps to return the correct card
     * @param col column of the board
     * @param row row of the board
     */
    public void setCardOnTableau(Card card, int col, int row) {
        card.label.setBounds(((73 * col) + (35 * (col + 1))),
                    row * 35, 73,  97);

        card.label.addMouseListener(this);

        // Adding each card into JlayeredPane with priority (2nd argument)
        layeredPane.add(card.label, Integer.valueOf(row));
        //System.out.println("rank" + row);

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
        this.layeredPane.remove(cards.get(index).label);
    }

    /**
     * Method moves the card on the tableau from waste (or the tableau NOT REALIZED).
     * @param card card that is moved
     * @return whether the card was moved
     */
    boolean addCardToTableau(Card card) {
        for (int col = 0; col < 7; col++) {
            int last = this.tabStacks.get(col).size() - 1;
            System.out.println(tabStacks.get(col).get(last).directory);

            if (canPlace(tabStacks.get(col).get(last), card)) {
                //updates card's position
                card.col = tabStacks.get(col).get(last).col;
                card.row = tabStacks.get(col).get(last).row + 1;

                //places the card on the board
                setCardOnTableau(card, card.col, card.row);

                //adding the card to the organized arraylist based on column
                tabStacks.get(col).add(last + 1, card);
                
                //adding to all of the cards that are on the tableau
                cards.add(card);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks whether it is possible to place clicked card on another.
     * The card's (user wants to place) numerical value has to be equal to 
     * placement's numerical value + 1
     * @param cardToPlace card we want to move
     * @param toPlaceOnto the place we are moving other card
     * @return whether the card was moved (or not)
     */
    boolean canPlace(Card cardToPlace, Card toPlaceOnto) {
        if (cardToPlace.number == (toPlaceOnto.number + 1) 
            && cardToPlace.color != toPlaceOnto.color) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //finds which card i'm caressing with my cursor
        for (int index = 0; index < cards.size(); index++) {
            //checks if it got the right card and if it's face up
            if (e.getSource() == cards.get(index).label 
                            && cards.get(index).isFaceUp) {
                if (foundations.addCardToFoundation(cards.get(index))) {
                    removeCardFromTableau(cards.get(index).row,
                         cards.get(index).col, index);
                } else if (addCardToTableau(cards.get(index))) {
                    System.out.println("column " + cards.get(index).col + "  and row " 
                        + cards.get(index).row);
                   //gotta remove it from the previous location
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


