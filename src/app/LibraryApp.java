package app;

import models.Library;

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("Biblioteka.v1.1");
        LibraryControll lib = new LibraryControll();
        lib.loopControll();
    }
}
