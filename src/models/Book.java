package models;

public class Book extends Publication {
    private String author;
    private int pages;
    private String isbn;

    public Book(String title,int year,String publisher,String author,int pages,String isbn ) {
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
    public String convertToCSV() {
        return "Książka;" + super.publicationConvertToCsv() +
                ";"+author+";"+pages+";"+isbn;
    }

    @Override
    public String toString() {
        return "Książka: " + super.toString() +
                "author: " + author +
                ", pages: " + pages +
                ", isbn: " + isbn+".";
    }
}
