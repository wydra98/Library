package io.file;

import exception.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;
import models.Library;

import java.io.FileNotFoundException;

public class FileManagerBuilder {
    DataReader dataReader;
    ConsolePrinter printer;

    public FileManagerBuilder(DataReader dataReader, ConsolePrinter printer) {
        this.dataReader = dataReader;
        this.printer = printer;
    }

    private void printFileOption(){
        printer.print("Podaj format zapisu danych: ");
        for(FileOption opt: FileOption.values()){
            printer.print(opt.toString());
        }
    }

    public FileManager build() throws FileNotFoundException {
        printFileOption();
        FileOption option = getOption();
        switch(option){
            case CSV:
                return new CsvFileManager();

            default:
                throw new NoSuchFileTypeException("Nieobsługiwany typ danych");
        }
    }

    private FileOption getOption(){
        boolean flag = false;
        FileOption option = null;
        do{
            String string = dataReader.readString().toUpperCase();
            try {
                option = FileOption.valueOf(string);
                flag = true;
            }
            catch(IllegalArgumentException e){
                printer.print("Nieobsługowany typ pliku! Podaj jeszcze raz!");
            }
        }
        while(!flag);
        return option;
    }
}
