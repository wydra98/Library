package app;

import exception.NoMoreSpaceException;
import exception.NoSuchIndexException;
import exception.ProblemWithFileException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import models.Book;
import models.Library;
import models.Magazine;
import models.Publication;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LibraryControll {

    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManagerBuilder fileManagerBuilder = new FileManagerBuilder(dataReader,printer);
    private Library library;
    private FileManager fileManager;

    LibraryControll() throws IOException {
        fileManager = fileManagerBuilder.build();
        try {
            library = fileManager.importData();
        }
        catch(ProblemWithFileException e){
            printer.print("Inicjalizuje nową bazę");
            library = new Library();
        }
    }

    public void loopControll(){
        Option option = null;
        do{
            printOption();
            option = getOption();
            switch(option){
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOK:
                    printBook();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_MAGAZINE:
                    printMagazine();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
            }
        }
        while(option!=Option.EXIT);

    }

    private void printOption() {
        for (Option opt: Option.values()) {
            printer.print(opt);
        }
    }

    private Option getOption() {
        boolean flag = false;
        Option option = null;
        printer.print("Podaj opcję:");
        do {
            try {
                option = Option.getOptionfromUserChoice(dataReader.readOption());
                flag = true;
            } catch (NoSuchIndexException e) {
                printer.print(e.getMessage());
            }catch(InputMismatchException e){
                printer.print("Tylko liczby! Spróbuj jeszcze raz!");
            }
        }
        while (!flag);
        return option;
    }

    private void addBook(){
        try{
            library.add(dataReader.readBook());
        }catch(NoMoreSpaceException e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void printBook(){
        Publication[] publicationRepository = library.getPublicationRepository();
        for(Publication singlePublication: publicationRepository){
            if(singlePublication instanceof Book)
            printer.print(singlePublication.toString());
        }
    }

    private void addMagazine(){
        try{
            library.add(dataReader.readMagazine());
        }catch(NoMoreSpaceException e){
            System.out.println(e.getMessage());
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void printMagazine(){
        Publication[] publicationRepository = library.getPublicationRepository();
        for(Publication singlePublication: publicationRepository){
            if(singlePublication instanceof Magazine)
                printer.print(singlePublication.toString());
        }
    }

    private void exit(){
        printer.print("Koniec! Jeszcze tylko eksportuje dane!");
       try{
           fileManager.exportData(library);
       }
       catch (ProblemWithFileException e){
           printer.print(e.getMessage());
       }
        dataReader.close();
    }
}
