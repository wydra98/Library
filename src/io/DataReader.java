package io;

import models.Book;
import models.Magazine;

import java.util.Scanner;

public class DataReader {

    private ConsolePrinter printer;
    private Scanner sc = new Scanner(System.in);

    public DataReader(ConsolePrinter printer){
        this.printer=printer;
    }

    public int readOption(){
        try{
            return sc.nextInt();
        }
        finally {
            sc.nextLine();
        }

    }

    public String readString(){
        return sc.nextLine();
    }

    public Book readBook(){

        printer.print("Podaj tytuł");
        String title = sc.nextLine();
        printer.print("Podaj autora");
        String author = sc.nextLine();
        printer.print("Podaj rok");
        int year = sc.nextInt();
        sc.nextLine();
        printer.print("Podaj ilość stron");
        int pages = sc.nextInt();
        sc.nextLine();
        printer.print("Podaj wydawcę");
        String publisher = sc.nextLine();
        printer.print("Podaj isbn");
        String isbn = sc.nextLine();

        return new Book(title, year, publisher, author,pages, isbn);
    }

    public Magazine readMagazine(){
        printer.print("Podaj tytuł");
        String title = sc.nextLine();
        printer.print("Podaj rok");
        int year = sc.nextInt();
        sc.nextLine();
        printer.print("Podaj wydawcę");
        String publisher = sc.nextLine();
        printer.print("Podaj język");
        String language = sc.nextLine();
        printer.print("Podaj miesiąc");
        String month = sc.nextLine();
        printer.print("Podaj dzień");
        String day = sc.nextLine();

        return new Magazine(title,year,publisher,month,day,language);
    }

    public void  close(){
        sc.close();
    }
}
