package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Vladislav Konovalov
 */
public final class FileService {
    private static final String FILENAME = "encryption_result.bin";

    private FileService() {}

    public static void writeToFile(byte[] data) {
    }

    public static byte[] readFromFile(String fullFileName) {
        return new byte[0];
    }

    public static Image getImage(String path) {
        Image image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
