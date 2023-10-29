import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

class Tableau extends JPanel implements MouseInputListener {
    public Color backgroundColor = new Color(14, 120, 71);

    Foundations foundations;

    ArrayList<Card> cards;

    // All stacks of tableau will be in tabStacks
    ArrayList<ArrayList<Card>> tabStacks = new ArrayList<>();

    // Stores the columns
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

        this.setBackground(backgroundColor);
        this.setBounds(0, 150, 810, 550);

        this.setLayout(null);
        this.initializeTableuStack();
    }

    
    Tableau(ArrayList<Card> cards) {
        this.cards = cards;
        // Setting bounds for JLayeredPane
        this.tabLayeredPanes = new ArrayList<>();

        // Initialize JLayeredPane for each column
        for (int i = 0; i < 7; i++) {
            JLayeredPane tabLayeredPane = new JLayeredPane();
            tabLayeredPane.setBounds(0, 0, 810, 550);
            tabLayeredPanes.add(tabLayeredPane);
            this.add(tabLayeredPane); // Add each JLayeredPane to the Tableau panel
        }

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
     * Removes the card from previous location.
     * @param row row to be removed from
     * @param col column to be removed from
     * @param card card to be removed
     */
    void removeCardFromTableau(int row, int col, Card card) {
        this.tabLayeredPanes.get(col).remove(card.label);
        this.tabStacks.get(col).remove(row);

        this.tabLayeredPanes.get(col).repaint();
        this.tabLayeredPanes.get(col).revalidate();
    }

    /**
     * Method for turning face-down cards on the board face up.
     * @param col column 
     * @param row row where a card was removed
     */
    void faceUpPreviousCard(int col, int row) {
        // Index of previous card
        // int index = this.tabStacks.get(col).size();
        row -= 1;
        if (row > -1) {
            Card card = tabStacks.get(col).get(row);
            tabStacks.get(col).get(row).label.setIcon(card.getFaceUp());
            tabStacks.get(col).get(row).isFaceUp = true;
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) == tabStacks.get(col).get(row)) {
                    cards.get(i).isFaceUp = true;
                }
            }
        }
    }

    /**
     * Make the king move from waste to empty stack.
     * @param card card we want to move
     * @param col column to which the king will be moved
     * @return whether the king was moved
     */
    boolean kingToEmpty(Card card, int col) {
        // Check if the card is a king
        if (card.number == 13) {
            if ((this.tabStacks.get(col).size() == 0)) {
                addCardToTableau(card);
                return true;
            }
        }
        return false;
    }

    /**
     * Method moves the card on the tableau from waste (or the tableau NOT REALIZED).
     * @param card card that is moved
     * @return whether the card was moved
     */
    boolean addCardToTableau(Card card) {
        for (int col = 0; col < 7; col++) {
            // Position of last card on that column
            int last = this.tabStacks.get(col).size() - 1;

            if (last >= 0 && canPlace(tabStacks.get(col).get(last), card)) {
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
    boolean canPlace(Card toPlaceOnto, Card cardToPlace) {
        if (cardToPlace.number == (toPlaceOnto.number - 1) 
            && cardToPlace.color != toPlaceOnto.color) {
            return true;
        } else if (cardToPlace.number == 13 && toPlaceOnto.number == 0) {
            return true;
        }
        return false;
    }

    /**
     * Moves selected (stack of) card(-s).
     * @param col column
     * @param row row
     * @return whether the card was moved
     */
    public boolean moveFaceUpCards(int col, int row) {
        Card empty = new Card('e', 0, new ImageIcon("cards\\emptycard.png"));
        // Check if the clicked card is face-up and if there are cards below it
        if (tabStacks.get(col).size() > row  && tabStacks.get(col).get(row).isFaceUp) {
            // Create a list to store the cards to be moved
            ArrayList<Card> cardsToMove = new ArrayList<>();
            
            // Add the clicked card and all the cards below it to the list
            for (int i = row; i < tabStacks.get(col).size(); i++) {
                cardsToMove.add(tabStacks.get(col).get(i));
            }
    
            // Check if the cards can be placed in another column
            for (int targetCol = 0; targetCol < 7; targetCol++) {
                if (targetCol == col) {
                    continue; // Skip the current column
                }
                
                int last = tabStacks.get(targetCol).size() - 1;
    
                // Check if the cards can be placed in the target column
                if ((last >= 0 && canPlace(tabStacks.get(targetCol).get(last), cardsToMove.get(0)))
                        || (last == -1 && canPlace(empty, cardsToMove.get(0)))) {
                    // Move the cards to the target column
                    for (Card card : cardsToMove) {
                        // Update the card's position
                        card.col = targetCol;
                        card.row = tabStacks.get(targetCol).size();
                        
                        // Place the card on the tableau
                        setCardOnTableau(card, targetCol, card.row);
                        
                        // Add the card to the target column
                        tabStacks.get(targetCol).add(card);
                        
                        // Remove the card from the current column
                        tabStacks.get(col).remove(card);
                    }
                    return true; // Exit the loop once the cards are moved
                }
            }
        }
        return false;
    }

    /**
     * Method checks whether there is a card under the provided one.
     * @param col column
     * @param row row
     * @return whether there is a face up card under given card
     */
    boolean checkCardUnder(int col, int row) {
        try {
            if (this.tabStacks.get(col).get(row - 1).isFaceUp) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method checks whether there is a card on top of the provided one.
     * @param col column
     * @param row row
     * @return whether there is a face up card on top of the given card
     */
    boolean checkCardOver(int col, int row) {
        try {
            if (this.tabStacks.get(col).size() > row + 1) {
                if (this.tabStacks.get(col).get(row + 1).isFaceUp) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //finds which card i'm caressing with my cursor
        for (int index = 0; index < cards.size(); index++) {
            
            //checks if it got the right card and if it's face up
            if (e.getSource() == cards.get(index).label 
                            && cards.get(index).isFaceUp) {
                
                int currentCol = cards.get(index).col;
                int currentRow = cards.get(index).row;
                Card currentCard = cards.get(index);

                currentCard.label.addMouseListener(this);
                
                if (!checkCardOver(currentCol, currentRow) 
                        && foundations.addCardToFoundation(cards.get(index))) {
                    removeCardFromTableau(cards.get(index).row,
                         cards.get(index).col, currentCard);
                    if (!checkCardUnder(currentCol, currentRow)) {
                        faceUpPreviousCard(currentCol, currentRow);
                    }
                    break;

                } else if (moveFaceUpCards(currentCol, currentRow)) {
                    // Faces up previous card
                    faceUpPreviousCard(currentCol, currentRow);
                    
                }
                this.tabLayeredPanes.get(currentCol).repaint();
                this.tabLayeredPanes.get(currentCol).revalidate();
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
