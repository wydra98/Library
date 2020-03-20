package app;

import exception.NoSuchIndexException;

import java.util.Optional;

public enum Option {
    ADD_BOOK(0,"Dodaj książkę do biblioteki."),
    ADD_MAGAZINE(1,"Dodaj magazyn do biblioteki."),
    REMOVE_BOOK(2,"Usuń magazyn z biblioteki."),
    REMOVE_MAGAZINE(3,"Usuń książkę z biblioteki."),
    PRINT_BOOK(4,"Wyświetl wszystkie książki w bibliotece."),
    PRINT_MAGAZINE(5,"Wyświetl wszystkie magazyny w bibliotece."),
    PRINT_ALL(6,"Wyświetl wszystkie publikacje w bibliotece."),
    ADD_USER(7,"Dodaj nowego użytkownika."),
    REMOVE_USER(8,"Usuń użytkownika."),
    ADD_SOMETHING_TO_USER(9,"Dodaj tytuł do użytkownika"),
    REMOVE_SOMETHING_FROM_USER(10,"Usuń tytuł do użytkownika"),
    PRINT_USER_BOOKS(11,"Wyświetl wszystkie tytuły danego użytkownika."),
    PRINT_USERS(12,"Wyświetl wszystkich użytkoników biblioteki."),
    EXIT(13,"Wyjdź z programu.");

    private int number;
    private String description;

    Option(int number, String description){
        this.number=number;
        this.description=description;
    }

    public static Option getOptionfromUserChoice(int choose) throws NoSuchIndexException {
        Option[] arr = values();
        if(choose>arr[arr.length-1].number){
            throw new NoSuchIndexException("Nie ma takiego indeksu! Spróbuj jeszcze raz!");
        }
        return values()[choose];
    }


    @Override
    public String toString() {
        return number+ " - "+description;
    }
}
