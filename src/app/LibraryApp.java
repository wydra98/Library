package app;

import models.Library;

import java.io.IOException;

public class LibraryApp {
    public static void main(String[] args) throws IOException {
        LibraryControll lib = new LibraryControll();
        lib.loopControll();
    }
}
