import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for Stack class.
 */
public class StackTests {

    @Test
    public void testCreateCardStackSize() {
        Stack stack = new Stack();
        stack.createCardStack();
        assertEquals("The card stack should have 52 cards.", 52, stack.cardStack.size());
    }

    @Test
    public void testCreateCardStackContents() {
        Stack stack = new Stack();
        stack.createCardStack();
        
        // Check if there are exactly 4 cards of each rank (1-13) for each suit (s, c, h, d).
        for (int rank = 1; rank <= 13; rank++) {
            assertEquals("There should be 4 cards with rank " + rank, 
                4, countCardsWithRank(stack.cardStack, rank));
        }
    }

    @Test
    public void testCreateCardStackDistinctCards() {
        Stack stack = new Stack();
        stack.createCardStack();

        // Check if all cards in the card stack are distinct.
        for (int i = 0; i < stack.cardStack.size(); i++) {
            for (int j = i + 1; j < stack.cardStack.size(); j++) {
                assertNotEquals("Cards at positions " + i + " and " + j + " should be distinct.", 
                    stack.cardStack.get(i), stack.cardStack.get(j));
            }
        }
    }

    // Helper method to count the number of cards with a specific rank in a list of cards.
    private int countCardsWithRank(ArrayList<Card> cards, int rank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getNumber() == rank) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testCreateShuffledCardStack() {
        Stack stack = new Stack();
        stack.createShuffledCardStack();

        // Test the size of the card stack (52 cards)
        assertEquals("The card stack should have 52 cards.", 52, stack.cardStack.size());

        // Test the size of the tableau stack (28 cards)
        assertEquals("The tableau stack should have 28 cards.", 28, stack.tabStack.size());

        // Test the size of the deck stack (24 cards)
        assertEquals("The deck stack should have 24 cards.", 24, stack.deckStack.size());

        // Test that the card stack contains all unique cards
        assertTrue("The card stack should contain unique cards.", 
            containsUniqueCards(stack.cardStack));
    }

    // Helper method to check if a list of cards contains unique cards
    private boolean containsUniqueCards(ArrayList<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).equals(cards.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
