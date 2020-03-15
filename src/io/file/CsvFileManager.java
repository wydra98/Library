package io.file;

import exception.ProblemWithFileException;
import models.Library;
import models.Publication;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFileManager implements FileManager {

    private final static String FILE_NAME = "Library.csv";

    public CsvFileManager() throws FileNotFoundException {
    }

    @Override
    public void exportData(Library library) {

        try(FileWriter fw = new FileWriter(new File(FILE_NAME));
            BufferedWriter bw = new BufferedWriter(fw)){


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Library importData() throws IOException {

        Library library = new Library();
        try (FileReader fr = new FileReader(new File(FILE_NAME));
            BufferedReader br = new BufferedReader(fr)){

            String napis = null;
            Publication obj = null;
            while ((napis = br.readLine()) != null) {
                obj = createPublicationObjectFromString(napis);
                library.add(obj);
                //System.out.println(2);
            }
            return library;
        }
        catch(FileNotFoundException e){
            throw new ProblemWithFileException("Nie znaleziono pliku do odczytu");
        } catch(IOException e){
            throw new ProblemWithFileException("Błąs odczytu");

        }
    }

       private Publication createPublicationObjectFromString(String napis){
            String[] arr = napis.split(";");
            return new Publication(arr[]);
       }

//    }
}
