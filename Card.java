import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;

class Card extends Stack {
    public int number;
    public char symbol;
    public boolean isFaceUp;
    public String directory = "cards\\";

    ImageIcon image;

    Card() { 
        this.createShuffledCardStack();
    }

    /**
     * Creates a card based on provided number and symbol.
     * 's' - spades
     * 'h' - hearts
     * 'c' - clubs
     * 'd' - diamonds
     * @param number numerical value of the card (1 - ace, 11 - jeff, 12 - queen, 13 - king)
     * @param symbol character that describes the type of card (see above)
     */
    Card(int number, char symbol) {
        this.number = number;
        this.symbol = symbol;
        this.directory = directory + ((number < 10) ? "0" + number + symbol + ".gif" 
            : number + symbol + ".gif");
        this.image = new ImageIcon(directory);
    }

    public void setIsFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
    }

    public boolean getIsFaceUp() {
        return isFaceUp;
    }

    public String getDirectory() {
        return directory;
    }

    public ImageIcon getFoundations() {
        ImageIcon fDownCard = new ImageIcon("cards\\fpBase0" + number + ".gif");
        return fDownCard;
    }

    public ImageIcon getCardBottom() {
        ImageIcon fBottomCard = new ImageIcon("cards\\bottom01.gif");
        return fBottomCard;
    }

    public void getFaceUp() {
        // Multiple vertical distance with lastCardNum to get pos of card and put it in col
        
        

    }
    
    public void getFaceDown(int col, int currentCardNum, Card cardList) {
        
    }
}
