package models;

public class Publication {
    private String title;
    private String publisher;
    private int year;

    public Publication(String title, String publisher, int year) {
        this.title = title;
        this.publisher = publisher;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "title: " + title +
                ", publisher: " + publisher  +
                ", year: " + year;
    }
}
