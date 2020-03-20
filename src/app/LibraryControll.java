package app;

import exception.*;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import models.*;

import java.io.IOException;
import java.util.*;

public class LibraryControll  {

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
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case REMOVE_BOOK:
                    removeBook();
                    break;
                case REMOVE_MAGAZINE:
                    removeMagazine();
                    break;
                case PRINT_BOOK:
                    printBook();
                    break;
                case PRINT_MAGAZINE:
                    printMagazine();
                    break;
                case PRINT_ALL:
                    printAll();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case REMOVE_USER:
                    removeUser();
                    break;
                case ADD_SOMETHING_TO_USER:
                    addSomethingToUser();
                    break;
                case REMOVE_SOMETHING_FROM_USER:
                    removeSomethingFromUser();
                    break;
                case PRINT_USER_BOOKS:
                    printUserBooks();
                    break;
                case PRINT_USERS:
                    printUsers();
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
        printer.print("\nPodaj opcję:");
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
            library.addPublication(dataReader.readBook());
            printer.print("\n");
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void addMagazine(){
        try{
            library.addPublication(dataReader.readMagazine());
            printer.print("\n");
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void removeBook() {
        try {
            library.removePublication(dataReader.readBook());
            printer.print("\n");
        } catch (InputMismatchException e) {
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void removeMagazine(){
        try{
            library.removePublication(dataReader.readMagazine());
            printer.print("\n");
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!");
        }
    }

    private void printBook(){
        ArrayList<Publication> publicationRepository = library.getAllPublicationInLibrary();
        Collections.sort(publicationRepository, Comparator.comparing(Publication::getTitle));
        for(Publication singlePublication: publicationRepository){
            if(singlePublication instanceof Book)
            printer.print(singlePublication.toString());
        }
        printer.print("\n");
    }

    private void printMagazine(){
        ArrayList<Publication> publicationRepository = library.getAllPublicationInLibrary();
        Collections.sort(publicationRepository, Comparator.comparing(Publication::getTitle));
        for(Publication singlePublication: publicationRepository){
            if(singlePublication instanceof Magazine)
                printer.print(singlePublication.toString());
        }
        printer.print("\n");
    }

    private void printAll(){
        ArrayList<Publication> publicationRepository = library.getAllPublicationInLibrary();
        Collections.sort(publicationRepository, Comparator.comparing(Publication::getTitle));
        for(Publication singlePublication: publicationRepository){
                printer.print(singlePublication.toString());
        }
        printer.print("\n");
    }

    private void addUser(){
        try{
            UserLibrary user = dataReader.readUser();
            if(user.getPesel().length() != 9){
                throw new WrongLengthException("");
            }
            Integer.parseInt(user.getPesel());
            library.checkIfPeselIsUnique(user.getPesel());
            library.addUser(user.getPesel(),user);
        }catch(InputMismatchException e){
            printer.print("Juz istnieje użytkownika o takim peselu w bazie!\n");
        }catch (NumberFormatException e) {
            printer.print("Nie podawaj liter w peselu!\n");
        }catch (WrongLengthException e) {
            printer.print("Zła długość peselu!\n");
        }
    }

    private void removeUser(){
        try{
            printer.print("Którego użytkownika usunąć, podaj pesel?");
            library.returnUsers().forEach((p)-> System.out.println("Imie: "+p.getName()+
                    ", nazwisko: "+p.getSurname()+", pesel:"+p.getPesel()));
            UserLibrary user = dataReader.readUser();
            library.removeUser(user.getPesel());
        }catch(InputMismatchException e){
            printer.print("Zły format danych! Spróbuj jeszcze raz!\n");
        }
    }

    private void addSomethingToUser(){
        try{
            printer.print("Którego użytkownika wybrać, podaj pesel?");
            library.returnUsers().forEach((p)-> System.out.println("Imie: "+p.getName()+
                    ", nazwisko: "+p.getSurname()+", pesel:"+p.getPesel()));
            String user = dataReader.readString();
            UserLibrary lib = library.showUserLibrary(user);

            printer.print("Którą ksiązkę dodać do użytkownika?");
            printAll();
            Book book = dataReader.readBook();
            lib.addPublication(book);
        }
        catch(NoBooksException e){
            printer.print(e.getMessage());
        }
        catch(IllegalArgumentException e){
            printer.print("Nie ma takiego peselu w bazie!\n");
        }
        catch (InputMismatchException e) {
            printer.print("Zły format danych! Spróbuj jeszcze raz!\n");
        }
    }

    private void removeSomethingFromUser(){
        try {
            printer.print("Którego użytkownika wybrać, podaj pesel?");
            library.returnUsers().forEach((p)-> System.out.println("Imie: "+p.getName()+
                    ", nazwisko: "+p.getSurname()+", pesel:"+p.getPesel()));
            String user = dataReader.readString();
            UserLibrary lib = library.showUserLibrary(user);

            printer.print("Którą ksiązkę usunąć od użytkownika?");
            printAll();
            Book book = dataReader.readBook();
            library.checkThisBookInRepo(book);
            lib.removePublication(book);
        }

        catch(NoBooksException e){
            printer.print(e.getMessage());
        }
        catch(IllegalArgumentException e){
            printer.print("Nie ma takiego peselu w bazie!\n");
        }
        catch (InputMismatchException e) {
            printer.print("Zły format danych! Spróbuj jeszcze raz!\n");
        }
    }

    private void printUserBooks(){
        try {
            printer.print("Którego użytkownika wybrać, podaj pesel?");
            library.returnUsers().forEach((p)-> System.out.println("Imie: "+p.getName()+
                                                ", nazwisko: "+p.getSurname()+", pesel:"+p.getPesel()));
            String user = dataReader.readString();
            UserLibrary lib = library.showUserLibrary(user);
            printer.print(lib.toString());
        }
        catch(IllegalArgumentException e){
            printer.print("Nie ma takiego peselu w bazie!\n");
        }
        catch(NullPointerException e){
            printer.print("Spróbuj jeszcze raz!\n");
        }
    }

    private void printUsers(){
        Map<String, UserLibrary> unsorted = library.getUsersBooks();
        Map<String, UserLibrary> sorted = new LinkedHashMap<>();

        unsorted.entrySet()
                .stream()
                .sorted(Map.Entry.<String, UserLibrary>comparingByValue())
                .forEachOrdered(x -> sorted.put(x.getKey(),x.getValue()));

        sorted.values().forEach(System.out::println);
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
