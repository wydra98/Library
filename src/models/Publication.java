package models;

public abstract class Publication{
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

    public abstract String convertToCSV();

    @Override
    public String toString() {
        return "title: " + title +
                ", publisher: " + publisher  +
                ", year: " + year + " ";
    }

    protected String publicationConvertToCsv() {
        return title+';'+year+';'+publisher;
    }

}
