package main.encoder;

import java.nio.charset.Charset;
import java.util.BitSet;

/**
 * @author Vladislav Konovalov
 */
final class XorEncoder implements Encoder {
    @Override
    public String encode(String text, String key) {
        if (text == null || "".equals(text) || key == null || "".equals(key))
            return "";

        key = fitKey(key, text.length());

        BitSet textBits = BitSet.valueOf(text.getBytes(Charset.forName("koi8")));
        BitSet keyBits = BitSet.valueOf(key.getBytes(Charset.forName("koi8")));
        textBits.xor(keyBits);

        return new String(textBits.toByteArray(), Charset.forName("koi8"));
    }

    @Override
    public String decode(String text, String key) {
        return encode(text, key);
    }

    private String fitKey(String initKey, int length) {
        StringBuilder repeatedKey = new StringBuilder(initKey);
        while (repeatedKey.length() < length)
            repeatedKey.append(initKey);
        String resultKey = repeatedKey.toString();
        if (resultKey.length() > length)
            resultKey = resultKey.substring(0, length);
        return resultKey;
    }
}
