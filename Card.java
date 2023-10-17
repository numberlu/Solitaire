import javax.swing.ImageIcon;

class Card {
    public int number;
    public char symbol;
    public boolean isFaceUp;
    public String directory = "cards\\";
    ImageIcon image;

    Card(int number, char symbol) {
        this.number = number;
        this.symbol = symbol;
        this.directory = directory + ((number < 10) 
        ? "0" + number + symbol + ".gif" : number + symbol + ".gif");
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
}
