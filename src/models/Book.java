package models;

public class Book extends Publication {
    private String author;
    private int pages;
    private String isbn;

    public Book(String title, String author, int year, int pages,String publisher,String isbn ) {
        super(title,publisher,year);
        this.author = author;
        this.pages = pages;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Książka: " + super.toString() +
                "author: " + author +
                ", pages: " + pages +
                ", isbn: " + isbn+".";
    }
}
