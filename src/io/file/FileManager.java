package io.file;

import models.Library;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileManager {
    public void exportData(Library library);
    public Library importData() throws IOException;
}
