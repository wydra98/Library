package models;

public class Magazine extends Publication{
    private String month;
    private String day;
    private String language;

    public Magazine(String title,int year,String publisher,String month, String day, String language) {
        super(title,publisher,year);
        this.month = month;
        this.day = day;
        this.language = language;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String convertToCSV() {
        return "Magazyn;" + super.publicationConvertToCsv() +
                ";"+month+";"+day+";"+language;
    }

    @Override
    public String toString() {
        return "Magazyn: " + super.toString() +
                "month: " + month +
                ", day: " + day +
                ", language: " + language+".";
    }
}
