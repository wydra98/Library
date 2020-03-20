package models;

import exception.NoBooksException;
import exception.NoMoreSpaceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Library {
    private ArrayList<Publication> allPublicationInLibrary = new ArrayList<>();
    private HashMap<String,UserLibrary> usersBooks = new HashMap<>();

    public HashMap<String, UserLibrary>getUsersBooks() {
        return usersBooks;
    }

    public UserLibrary showUserLibrary(String pesel){
        return usersBooks.get(pesel);
    }

    public Collection<UserLibrary> returnUsers(){
        return usersBooks.values();
    }

    public void checkThisBookInRepo(Book book){
        if(!allPublicationInLibrary.contains(book)){
            throw new NoBooksException("Nie ma takiej książki w repozytorium");
        }
    }

    public void checkIfPeselIsUnique(String pesel) {
        if(usersBooks.keySet().contains(pesel)){
            throw new InputMismatchException();
        }
    }

    public void addUser(String pesel, UserLibrary lib) {
        usersBooks.put(pesel, lib);
    }

    public void removeUser(String pesel) {
        usersBooks.remove(pesel);
    }

    public ArrayList<Publication> getAllPublicationInLibrary() {
        return allPublicationInLibrary;
    }

    public void addPublication(Publication pub) {
        allPublicationInLibrary.add(pub);
    }

    public void removePublication(Publication pub) {
        allPublicationInLibrary.remove(pub);
    }





}
