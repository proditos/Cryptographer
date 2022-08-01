package main.encoder;

/**
 * @author Vladislav Konovalov
 */
public final class EncoderFactory {
    private EncoderFactory() {}

    public static Encoder getEncoder(EncoderType encoderType) {
        switch (encoderType) {
            case XOR:
                return new XorEncoder();
            default:
                throw new IllegalArgumentException("Unknown encoder type");
        }
    }
}
