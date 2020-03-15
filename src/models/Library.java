package models;

import exception.NoMoreSpaceException;

public class Library {
    private final static int MAX_PUBLICATION = 2000;
    private Publication[] publicationRepository = new Publication[MAX_PUBLICATION];
    private int booksNumber = 0;

    public Publication[] getPublicationRepository() {
        return publicationRepository;
    }

    public void addBook(Book b){
        if(booksNumber<MAX_PUBLICATION){
            publicationRepository[booksNumber] = b;
            booksNumber+=1;
        }
        else
            throw new NoMoreSpaceException("Brak miejsca!");
    }

    public void addMagazine(Magazine m){
        if(booksNumber<MAX_PUBLICATION){
            publicationRepository[booksNumber] = m;
            booksNumber+=1;
        }
        else
            throw new NoMoreSpaceException("Brak miejsca!");
    }

}
