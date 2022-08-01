package main;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Vladislav Konovalov
 */
public final class FileService {
    private static final String ENCRYPTED_FILE_NAME = "encrypted_file";
    private static final String ENCRYPTED_FILE_EXTENSION = "bin";
    private static final String ENCRYPTED_FILE_DESCRIPTION = "Binary file";

    private FileService() {}

    public static void writeToFile(byte[] data) {
        String fileName = ENCRYPTED_FILE_NAME + "." + ENCRYPTED_FILE_EXTENSION;
        try {
            Files.write(Paths.get(fileName), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFromFile(String fullFileName) {
        byte[] result = new byte[0];
        if (fullFileName == null)
            return result;
        try {
            result = Files.readAllBytes(Paths.get(fullFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static FileFilter getFileFilter() {
        return new FileNameExtensionFilter(ENCRYPTED_FILE_DESCRIPTION, ENCRYPTED_FILE_EXTENSION);
    }
}
