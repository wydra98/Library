package app;

import exception.NoSuchIndexException;

import java.util.Optional;

public enum Option {
    ADD_BOOK(0,"Dodaj książkę."),
    PRINT_BOOK(1,"Wyświetl wszystkie książki."),
    ADD_MAGAZINE(2,"Dodaj magazyn."),
    PRINT_MAGAZINE(3,"Wyświetl wszystkie magazyny."),
    EXIT(4,"Wyjdź z programu.");

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
