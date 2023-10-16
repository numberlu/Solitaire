
class Card {
    public int number;
    public char symbol;
    public boolean isFaceUp;
    public String directory;

    Card(int number, char symbol) {
        this.number = number;
        this.symbol = symbol;
        this.directory = number < 10 ? "0" + number + symbol + ".gif" : number + symbol + ".gif";
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
