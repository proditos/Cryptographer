package main;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Vladislav Konovalov
 */
public final class Coder {
    private static final FileFilter FILTER = new FileNameExtensionFilter("Binary files", "bin");
    private static final String ENCRYPTED_FILE_NAME = "encrypted_file.bin";

    private Coder() {}

    public static void codeIntoFile(String text, String key) {
    }

    public static String decodeFromFile(String fullFileName, String key) {
        return null;
    }

    public static FileFilter getFilter() {
        return FILTER;
    }

    public static String getEncryptedFileName() {
        return ENCRYPTED_FILE_NAME;
    }
}
