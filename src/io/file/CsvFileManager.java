package io.file;

import exception.ProblemWithFileException;
import models.Book;
import models.Library;
import models.Magazine;
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
            for(Publication singlePublication : library.getPublicationRepository()){
                if(singlePublication!=null) {
                    bw.write(singlePublication.convertToCSV());
                    bw.newLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
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
            if(arr[0].toLowerCase().equals("książka")){
                int var1 = Integer.valueOf(arr[2]);
                int var2 = Integer.valueOf(arr[5]);
                return new Book(arr[1],var1,arr[3],arr[4],var2,arr[6]);
            }
            else if (arr[0].toLowerCase().equals("magazyn")){
                int year = Integer.valueOf(arr[2]);
                return new Magazine(arr[1],year,arr[3],arr[4],arr[5],arr[6]);
           }
            return null;
       }

//    }
}
