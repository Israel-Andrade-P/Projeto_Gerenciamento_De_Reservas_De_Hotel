package exception;

public class HospedeNotFoundException extends RuntimeException {
    public HospedeNotFoundException(String message) {
        super(message);
    }
}
