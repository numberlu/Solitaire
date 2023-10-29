import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import org.junit.jupiter.api.BeforeEach;
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
        // Create a test card
        Card testCard = new Card('h', 1, testImage);
        testCard.isFaceUp = true; // Assume the card is face up

        Card card = new Card();
        // Initialize a list of cards for testing
       
        testCards = card.cardStack;

        // Initialize the tableau with no cards
        tableau = new Tableau(testCards);

        // Add the test card to the tableau
        boolean added = tableau.addCardToTableau(testCard);

        // Verify that the card was added to the tableau
        assertTrue(added, "Card should be added to the tableau");
        
        // Check if the card is in the tableau's tabStacks and has the correct position
        int col = testCard.col;
        int row = testCard.row;

        ArrayList<Card> columnStack = tableau.tabStacks.get(col);

        // Verify that the card is in the correct position and face-up
        assertEquals(1, columnStack.size(), "Expected one card in the column stack");
        assertEquals(testCard, columnStack.get(0), "Card not in the correct position");
        assertEquals(row, testCard.row, "Card row is incorrect");
        assertTrue(testCard.isFaceUp, "Card should be face up");
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
        boolean canPlace = tableau.canPlace(card1, card2);

        // Verify that card2 can be placed on top of card1
        assertTrue(canPlace, "Card2 should be able to be placed on top of card1");
        
        // Set card colors to be the same
        card1.color = 'r';
        card2.color = 'r';

        // Try placing card2 on top of card1 with the same color (not allowed by the rules)
        canPlace = tableau.canPlace(card1, card2);

        // Verify that card2 cannot be placed on top of card1
        assertFalse(canPlace, "Card2 should not be able to be placed on top " 
            + "of card1 with the same color");
        
        // Set card numbers to represent a king (13) and an ace (1)
        card1.number = 13;
        card2.number = 1;

        // Try placing card2 on top of card1 (ace on king, allowed by the rules)
        canPlace = tableau.canPlace(card1, card2);

        // Verify that card2 can be placed on top of card1
        assertTrue(canPlace, "Card2 (ace) should be able to be placed on top of card1 (king)");
    }

    @Test
    public void testMoveFaceUpCards() {
        // Create a tableau with test cards
       
        Card card1 = new Card('h', 1, testImage);
        Card card2 = new Card('c', 2, new ImageIcon("cards\\02c.gif"));
        Card card3 = new Card('d', 3, new ImageIcon("cards\\03d.gif"));
        
        // Assume the cards are face up
        card1.isFaceUp = true;
        card2.isFaceUp = true;
        card3.isFaceUp = true;
        
        card1.col = 0;
        card1.row = 0;
        card2.col = 0;
        card2.row = 1;
        card3.col = 0;
        card3.row = 2;
        
        testCards.add(card1);
        testCards.add(card2);
        testCards.add(card3);
        
        tableau = new Tableau(testCards);
        
        // Attempt to move the stack (card1, card2, card3) to another column (e.g., column 1)
        boolean moved = tableau.moveFaceUpCards(0, 0); // Moving from column 0, row 0
        
        // Verify that the cards were successfully moved
        assertTrue(moved, "Stack of cards should be movable to another column");
        
        // Check that the cards are now in column 1
        assertEquals(3, tableau.tabStacks.get(1).size(), "Column 1 should have 3 cards");
        assertEquals(0, tableau.tabStacks.get(0).size(), "Column 0 should have no cards");
        assertEquals(card1, tableau.tabStacks.get(1).get(0), "Card1 should be in column 1");
        assertEquals(card2, tableau.tabStacks.get(1).get(1), "Card2 should be in column 1");
        assertEquals(card3, tableau.tabStacks.get(1).get(2), "Card3 should be in column 1");
    }

    @Test
    public void testKingToEmpty() {
        // Create a tableau with an empty column (column 0) and a king card (card1)
       
        Card card1 = new Card('h', 13, new ImageIcon("cards\\13h.gif"));
        
        // Assume the king card is face up
        card1.isFaceUp = true;
        
        card1.col = 0;
        card1.row = 0;
        
        testCards.add(card1);
        
        tableau = new Tableau(testCards);
        
        // Attempt to move the king card to an empty column (e.g., column 1)
        boolean moved = tableau.kingToEmpty(card1, 1); // Moving to an empty column (column 1)
        
        // Verify that the king card was successfully moved to the empty column
        assertTrue(moved, "king card should be movable to an empty column");
        
        // Check that the king card is now in column 1
        assertEquals(1, tableau.tabStacks.get(1).size(), "Column 1 should have 1 card (king card)");
        assertEquals(0, tableau.tabStacks.get(0).size(), "Column 0 should have no cards");
        assertEquals(card1, tableau.tabStacks.get(1).get(0), "king card should be in column 1");
    }

    @Test
    public void testCheckCardUnder() {
        testCards.get(2).isFaceUp = true;
        testCards.get(8).isFaceUp = true;
        testCards.get(13).isFaceUp = true;

        tableau = new Tableau(testCards);
        
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
        testCards.get(2).isFaceUp = true;
        testCards.get(8).isFaceUp = true;
        testCards.get(13).isFaceUp = true;
        
        System.out.println("col = " + testCards.get(2).col + " , row = " + testCards.get(2).row);
        System.out.println("col = " + testCards.get(8).col + " , row = " + testCards.get(8).row);
        System.out.println("col = " + testCards.get(13).col + " , row = " + testCards.get(13).row);


        tableau = new Tableau(testCards);
        
        // Check if there's a face-up card on top of card2 (in column 0, row 1)
        boolean hasFaceUpCardOver = tableau.checkCardOver(0, 0);
        
        // Verify that there is a face-up card on top of card2
        assertTrue(hasFaceUpCardOver, 
            "There should be a face-up card on top of card2 in the same column (column 0)");
        
        // Check if there's a face-up card on top of card3 (in column 0, row 2)
        hasFaceUpCardOver = tableau.checkCardOver(0, 2);
        
        // Verify that there is no face-up card on top of card3 (it's the top card in the column)
        assertFalse(hasFaceUpCardOver, 
            "There should be no face-up card on top of card3 in the same column (column 0)");
    }
}
