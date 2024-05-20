package BookClass;

public class BookClass {
    private String bookTitle, bookAuthor;

    public BookClass(String bookTitle, String bookAuthor) {
        this.bookAuthor = bookAuthor;
        this.bookTitle = bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}
