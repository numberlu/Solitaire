import java.awt.Image;
import javax.swing.ImageIcon;

class Card {
    public int number;
    public char symbol;
    public boolean isFaceUp;
    public String directory;
    ImageIcon image;

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
    // asdasd

    public boolean getIsFaceUp() {
        return isFaceUp;
    }

    public String getDirectory() {
        return directory;
    }

    public ImageIcon getFaceDown() {
        ImageIcon fDownCard = new ImageIcon("cards\\fpBase0" + number + ".gif");
        return fDownCard;
    }

    void method() {

    }
}
