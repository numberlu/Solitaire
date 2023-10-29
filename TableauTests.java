import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.Test;

/**
 * Class to test the class Tableau.
 */
public class TableauTests {

    private Tableau tableau;
    private ImageIcon testImage = new ImageIcon("cards\\01h.gif");
    private Card card = new Card();
    private ArrayList<Card> testCards = card.tabStack;

    @Test
    public void testInitializeTableauStack() {
        // Initialize the tableau using the test cards
        tableau = new Tableau(testCards);

        // Verify that the tableau stack is correctly initialized
        for (int col = 0; col < 7; col++) {
            ArrayList<Card> columnStack = tableau.tabStacks.get(col);
            for (int row = 0; row < columnStack.size(); row++) {
                Card card = columnStack.get(row);

                // Assert that the card's position and face-up/face-down state match the test data
                assertEquals(col, card.col, "Card column is incorrect");
                assertEquals(row, card.row, "Card row is incorrect");
                assertEquals(col == row, card.isFaceUp, "Card face-up state is incorrect");
            }
        }
    }


    @Test
    public void testAddCardToTableau() {
        Card card1 = new Card('h', 1, testImage);
        Card card2 = new Card('c', 2, new ImageIcon("cards\\02c.gif"));

        // Set card colors to be different
        card1.color = 'r';
        card2.color = 'b';

        testCards.add(0, card2);

        // Initialize the tableau with no cards
        tableau = new Tableau(testCards);

        // Add the test card to the tableau
        boolean added = tableau.addCardToTableau(card1);

        // Verify that the card was added to the tableau
        assertTrue(added, "Card should be added to the tableau");
        
        // Check if the card is in the tableau's tabStacks and has the correct position
        int col = tableau.tabStacks.get(0).get(1).col;
        int row = tableau.tabStacks.get(0).get(1).row;

        ArrayList<Card> columnStack = tableau.tabStacks.get(col);

        // Verify that the card is in the correct position and face-up
        assertEquals(2, columnStack.size(), "Expected one card in the column stack");
        assertEquals(card1, columnStack.get(1), "Card not in the correct position");
        assertEquals(row, tableau.tabStacks.get(0).get(1).row, "Card row is incorrect");
    }

    @Test
    public void testCanPlace() {
       
        Card card1 = new Card('h', 1, testImage);
        Card card2 = new Card('c', 2, new ImageIcon("cards\\02c.gif"));

        // Set card colors to be different
        card1.color = 'r';
        card2.color = 'b';

        // Initialize the tableau with no cards
        tableau = new Tableau(testCards);

        // Try placing card2 on top of card1 (card2 has a higher number)
        boolean canPlace = tableau.canPlace(card2, card1);

        // Verify that card2 can be placed on top of card1
        assertTrue(canPlace, "Card2 should be able to be placed on top of card1");
        
        // Set card colors to be the same
        card1.color = 'r';
        card2.color = 'r';

        // Try placing card2 on top of card1 with the same color (not allowed by the rules)
        canPlace = tableau.canPlace(card2, card1);

        // Verify that card2 cannot be placed on top of card1
        assertFalse(canPlace, "Card2 should not be able to be placed on top " 
            + "of card1 with the same color");
        
        // Set card numbers to represent a king (13) and an empty spot (0)
        card1.number = 0;
        card2.number = 13;

        canPlace = tableau.canPlace(card1, card2);

        // Verify that king can be placed on top of empty spot
        assertTrue(canPlace, "King should be able to be placed on top of an empty spot");
    }

    @Test
    public void testCheckCardUnder() {

        tableau = new Tableau(testCards);

        testCards.get(3).isFaceUp = true;
        testCards.get(4).isFaceUp = true;
        testCards.get(5).isFaceUp = true;
        
        // Check if there's a face-up card under card2 (in column 2, row 1)
        boolean hasFaceUpCardUnder = tableau.checkCardUnder(0, 1);
        
        // Verify that there is a face-up card under card2
        assertTrue(hasFaceUpCardUnder, 
            "There should be a face-up card under card2 in the same column (column 0)");
        
        // Check if there's a face-up card under card1 (in column 0, row 0)
        hasFaceUpCardUnder = tableau.checkCardUnder(0, 0);
        
        // Verify that there is no face-up card under card1 (it's the bottom card in the column)
        assertFalse(hasFaceUpCardUnder, 
            "There should be no face-up card under card1 in the same column (column 0)");
    }

    @Test
    public void testCheckCardOver() {
        
        
        tableau = new Tableau(testCards);

        testCards.get(3).isFaceUp = true;
        testCards.get(4).isFaceUp = true;
        testCards.get(5).isFaceUp = true;
        
        // Check if there's a face-up card on top of card2 (in column 0, row 1)
        boolean hasFaceUpCardOver = tableau.checkCardOver(2, 0);
        
        // Verify that there is a face-up card on top of card2
        assertTrue(hasFaceUpCardOver, 
            "There should be a face-up card on top of card2 in the same column (column 0)");
        
        // Check if there's a face-up card on top of card3 (in column 0, row 2)
        hasFaceUpCardOver = tableau.checkCardOver(2, 2);
        
        // Verify that there is no face-up card on top of card3 (it's the top card in the column)
        assertFalse(hasFaceUpCardOver, 
            "There should be no face-up card on top of card3 in the same column (column 0)");
    }
}
