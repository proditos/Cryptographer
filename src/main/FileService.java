package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Vladislav Konovalov
 */
public final class FileService {
    private static final String ENCRYPTED_FILE_NAME = "encrypted_file";
    private static final String ENCRYPTED_FILE_EXTENSION = "bin";
    private static final String ENCRYPTED_FILE_DESCRIPTION = "Binary file";
    private static final String RESOURCES_ROOT = "src/main/resources/";
    private static final String FOLDER_ICON_PATH = RESOURCES_ROOT + "folder_icon.png";
    private static final String APP_ICON_PATH = RESOURCES_ROOT + "icon.png";

    private FileService() {}

    public static void writeToFile(byte[] data) {
    }

    public static byte[] readFromFile(String fullFileName) {
        return new byte[0];
    }

    public static Image getApplicationImage() {
        Image image = null;
        try {
            image = ImageIO.read(new File(APP_ICON_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Icon getFolderIcon() {
        return new ImageIcon(FOLDER_ICON_PATH);
    }

    public static FileFilter getFileFilter() {
        return new FileNameExtensionFilter(ENCRYPTED_FILE_DESCRIPTION, ENCRYPTED_FILE_EXTENSION);
    }

    public static String getEncryptedFileNameWithExtension() {
        return ENCRYPTED_FILE_NAME + "." + ENCRYPTED_FILE_EXTENSION;
    }
}
