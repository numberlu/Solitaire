import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.sound.midi.SysexMessage;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class Tableau extends JPanel implements MouseInputListener{
    public Color backgroundColor = new Color(14, 120, 71);

    Foundations foundations;

    ArrayList<Card> cards;

    // All stacks of tableau will be in tabStacks
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList<>();

    // Stores the 
    ArrayList<JLayeredPane> tabLayeredPanes;

    /**
     * Tableau board.
     * @param cards cards that are initialized on the board upon the start of the game.
     */
    public Tableau(ArrayList<Card> cards, Foundations foundations) {
        // Shuffled cards (28 cards in total)
        this.cards = cards;

        this.foundations = foundations;

        // Setting bounds for JLayeredPane
        this.tabLayeredPanes = new ArrayList<>();

        // Initialize JLayeredPane for each column
        for (int i = 0; i < 7; i++) {
            JLayeredPane tabLayeredPane = new JLayeredPane();
            tabLayeredPane.setBounds(0, 0, 810, 550);
            tabLayeredPanes.add(tabLayeredPane);
            this.add(tabLayeredPane); // Add each JLayeredPane to the Tableau panel
        }

        // this.initializeTableuStack();

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
        JLayeredPane tabLayeredPane = tabLayeredPanes.get(col);
        tabLayeredPane.add(card.label);
        tabLayeredPane.setLayer(card.label, tabLayeredPane.getComponentCount() - 1);

        // Adding layeredPane into JPanel for display
        this.add(tabLayeredPane);
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
    void removeCardFromTableau(int row, int col, Card card) {
        this.tabLayeredPanes.get(col).remove(card.label);

        this.tabLayeredPanes.get(col).repaint();
        this.tabLayeredPanes.get(col).revalidate();
    }

    void faceUpPreviousCard(int col,int row, int index) {
        // Index of previous card
        // int index = this.tabStacks.get(col).size();
        row -= 1;

        tabStacks.get(col).get(row).label.setIcon(cards.get(index - 1).getFaceUp());
        tabStacks.get(col).get(row).isFaceUp = true;
        cards.get(index - 1).isFaceUp = true;
        
    }

    // Make a method that checks the cards above a certain

    // Make king move to empty stack


    /**
     * Method moves the card on the tableau from waste (or the tableau NOT REALIZED).
     * @param card card that is moved
     * @return whether the card was moved
     */
    boolean addCardToTableau(Card card) {
        for (int col = 0; col < 7; col++) {
            // Position of last card on that column
            int last = this.tabStacks.get(col).size() - 1;
            

            // Checks if card can be placed
            if (canPlace(tabStacks.get(col).get(last), card)) {
                //updates card's position

                // New Column
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
            // System.out.println(tabStacks.get(1).size());
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
            System.out.println("True");
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
                
                
                int previousCol = cards.get(index).col;
                int previousRow = cards.get(index).row;
                Card previousCard = cards.get(index);

                if (foundations.addCardToFoundation(cards.get(index))) {
                    removeCardFromTableau(cards.get(index).row,
                         cards.get(index).col, previousCard);

                    faceUpPreviousCard(previousCol, previousRow, index);
                    break;

                } else if (addCardToTableau(cards.get(index))) {
                    // Faces up previous card
                    faceUpPreviousCard(previousCol, previousRow, index);
                    // setCardOnTableau(previousCard, previousCol, previousRow - 1);

                    System.out.println("column " + previousCol + " and row " 
                        + previousRow);
                    
                    break;

                        
                    // Gotta remove it from the previous location
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
