package exception;

public class HospedeAlreadyExistsException extends RuntimeException {
    public HospedeAlreadyExistsException(String message) {
        super(message);
    }
}
