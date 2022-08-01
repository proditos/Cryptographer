package main.encoder;

/**
 * @author Vladislav Konovalov
 */
public interface Encoder {
    String encode(String text, String key);
    String decode(String text, String key);
}
