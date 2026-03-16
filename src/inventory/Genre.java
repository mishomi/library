package inventory;

public class Genre {

    protected String name;
    private Inventory[] booksInThisGenre;
    private int pointer;

    public Genre(String name) {
        this.name = name;
        this.booksInThisGenre = new Inventory[20];
        this.pointer = 0;
    }

    public void addBook(Inventory inventory) {

        for (int i = 0; i < pointer; i++) {
            if (booksInThisGenre[i] == inventory) {
                System.out.println("book already accounted for");
                return;
            }
        }
        if (pointer >= booksInThisGenre.length) {
            Inventory[] temp = new Inventory[booksInThisGenre.length * 2];
            System.arraycopy(booksInThisGenre, 0, temp, 0, booksInThisGenre.length);
            booksInThisGenre = temp;
        }
        booksInThisGenre[pointer] = inventory;
        pointer++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory[] getBooksInThisGenre() {
        return booksInThisGenre;
    }

    public void setBooksInThisGenre(Inventory[] booksInThisGenre) {
        this.booksInThisGenre = booksInThisGenre;
    }
}
