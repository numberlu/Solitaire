import static org.junit.Assert.*;

import javax.swing.ImageIcon;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for class Foundations.
 * Tests whether the logic for adding cards to foundations is 
 * correct - void testAddCardToFoundation(), whether it is possible to add the same
 * card to foundations twice - void testAddCardToFoundationWithInvalidCard(), and
 * whether the images match up with expected - void testUpdateFoundations().
 */
public class FoundationsTests {

    private Foundations foundations;
    
    @Before
    public void setUp() {
        foundations = new Foundations();
    }

    @Test
    public void testAddCardToFoundation() {
        Card spadesCard = new Card('s', 1, new ImageIcon("cards\\spades01.gif"));
        assertTrue(foundations.addCardToFoundation(spadesCard));
        assertEquals(1, foundations.spades);
        assertTrue(foundations.cardsInFoundation.contains(spadesCard));
        
        Card heartsCard = new Card('h', 1, new ImageIcon("cards\\hearts01.gif"));
        assertTrue(foundations.addCardToFoundation(heartsCard));
        assertEquals(1, foundations.hearts);
        assertTrue(foundations.cardsInFoundation.contains(heartsCard));
        
        Card invalidCard = new Card('c', 2, new ImageIcon("cards\\clubs02.gif"));
        assertFalse(foundations.addCardToFoundation(invalidCard));
        assertEquals(0, foundations.clubs);
        assertFalse(foundations.cardsInFoundation.contains(invalidCard));
        
        Card diamondsCard = new Card('d', 1, new ImageIcon("cards\\diamonds01.gif"));
        assertTrue(foundations.addCardToFoundation(diamondsCard));
        assertEquals(1, foundations.diamonds);
        assertTrue(foundations.cardsInFoundation.contains(diamondsCard));
        
        assertEquals(3, foundations.cardsInFoundation.size());
    }

    @Test
    public void testAddCardToFoundationWithInvalidCard() {
        Card spadesCard = new Card('s', 1, new ImageIcon("cards\\spades01.gif"));
        assertTrue(foundations.addCardToFoundation(spadesCard));
        
        Card duplSpadesCard = new Card('s', 1, new ImageIcon("cards\\spades02.gif"));
        assertFalse(foundations.addCardToFoundation(duplSpadesCard));
        assertEquals(1, foundations.spades);
        assertFalse(foundations.cardsInFoundation.contains(duplSpadesCard));
    }

    @Test
    public void testUpdateFoundations() {
        Card spadesCard = new Card('s', 1, new ImageIcon("cards\\spades01.gif"));
        assertTrue(foundations.addCardToFoundation(spadesCard));
        assertEquals(1, foundations.spades);
        
        Card heartsCard = new Card('h', 1, new ImageIcon("cards\\hearts01.gif"));
        assertTrue(foundations.addCardToFoundation(heartsCard));
        assertEquals(1, foundations.hearts);
        
        Card clubsCard = new Card('c', 1, new ImageIcon("cards\\clubs01.gif"));
        assertTrue(foundations.addCardToFoundation(clubsCard));
        assertEquals(1, foundations.clubs);
        
        Card diamondsCard = new Card('d', 1, new ImageIcon("cards\\diamonds01.gif"));
        assertTrue(foundations.addCardToFoundation(diamondsCard));
        assertEquals(1, foundations.diamonds);

        // Check that the foundation labels have been updated with the card images
        assertEquals(spadesCard.image, foundations.spadesLabel.getIcon());
        assertEquals(heartsCard.image, foundations.heartsLabel.getIcon());
        assertEquals(clubsCard.image, foundations.clubsLabel.getIcon());
        assertEquals(diamondsCard.image, foundations.diamondsLabel.getIcon());
    }
}
