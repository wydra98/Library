package io.file;

import exception.ProblemWithFileException;
import models.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {

    private static final String FILE_NAME = "Library.obj";
    @Override
    public void exportData(Library library) {
        try(FileOutputStream fis = new FileOutputStream(FILE_NAME);
            ObjectOutputStream ois = new ObjectOutputStream(fis)){
            ois.writeObject(library);
        }
          catch (FileNotFoundException e) {
              throw new ProblemWithFileException("Nie znaleziono pliku "+ FILE_NAME);
        } catch (IOException e) {
              throw new ProblemWithFileException("Błąd zapisu danych do pliku "+ FILE_NAME);
        } {}
    }

    @Override
    public Library importData() {
        try(
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ){
           return (Library) ois.readObject();
        }
         catch (FileNotFoundException e) {
            throw new ProblemWithFileException("Nie znaleziono pliku "+ FILE_NAME);
        } catch (IOException e) {
            throw new ProblemWithFileException("Błąd odczytu pliku "+ FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new ProblemWithFileException("Niezgodny typ danych w pliku "+ FILE_NAME);
        }

    }
}
