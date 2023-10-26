import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

class Card extends Stack {
    public int number;
    public char symbol;
    public boolean isFaceUp;
    public String directory = "cards\\";
    public JLabel label;
    public String position;
    public int col;
    public int row;
    public char color;

    ImageIcon image;

    Card() { 
        this.createShuffledCardStack();
    }

    /**
     * For FoundationsTests.java.
     * @param symbol type of card
     * @param number numerical value of the card (1 - ace, 11 - jeff, 12 - queen, 13 - king)
     * @param image front side of the card
     */
    Card(char symbol, int number, ImageIcon image) {
        this.symbol = symbol;
        this.number = number;
        this.image = image;
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
        if (symbol == 'h' || symbol == 'd') {
            this.color = 'r';
        } else {
            this.color = 'b';
        }
        this.directory = directory + ((number < 10) ? "0" + number + symbol + ".gif" 
            : "" + number + symbol + ".gif");
        this.image = new ImageIcon(directory);
        this.label = new JLabel(image);
    }

    public int getNumber() {
        return number;
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

    /**
     * gets empty card.
     */
    public ImageIcon getCardBottom() {
        ImageIcon fBottomCard = new ImageIcon("cards\\empty.png");
        return fBottomCard;
    }

    /**
     * Method returns the front of the card using directory.
     * @return image of the front of the card
     */
    public ImageIcon getFaceUp() {
        // Multiple vertical distance with lastCardNum to get pos of card and put it in col
        ImageIcon fUpCard = new ImageIcon(this.directory);
        return fUpCard;

    }
    
    /**
     * Method returns the back of the card (black) if the card is facedown.
     * @return back of the card.
     */
    public ImageIcon getFaceDown() {
        ImageIcon fDownCard = new ImageIcon(new ImageIcon("cards\\card back black.png")
            .getImage().getScaledInstance(73, 97, Image.SCALE_SMOOTH));
        return fDownCard;
    }   
}
